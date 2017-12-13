package com.search;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.DoublePoint;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.MultiPhraseQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.store.FSDirectory;

//User profile Query Generation.
public class QueryGenerate {

	private final static String INDEXPATH = "E:\\index";
	public HashMap<String, UserCategories> clist;
	public List<String> allCat;
	private final IndexReader reader;
	private IndexSearcher searcher;
	private QueryParser parser;
	private StandardAnalyzer analyzer;
	private Evaluation ex;

	public QueryGenerate(String userID, List<String> business) throws IOException, ParseException {
		// TODO Auto-generated constructor stub
		this.reader = DirectoryReader.open(FSDirectory.open(Paths.get(INDEXPATH)));
		this.searcher = new IndexSearcher(this.reader);
		this.clist = new HashMap<String, UserCategories>();
		this.allCat = new ArrayList<String>();
		this.analyzer = new StandardAnalyzer();
		this.ex = new Evaluation();
		// this.parser = new QueryParser(f, a);

		// Dividing for test and training data set
		int length = business.size();
		int divide = (int) (length * (0.9));
		int counter = 1;

		for (String i : business) {
			MongoDBConnector mdc = new MongoDBConnector();
			CategoryRequest cat = mdc.categoryRequest(i);
			mdc.close();
			// only training dataset is processed.
			if (counter++ < divide) {
				List<String> categories = cat.getCategories();
				for (String j : categories) {
					if (!allCat.contains(j)) {
						double priceRange = cat.getRestaurantsPriceRange2();
						allCat.add(j);
						UserCategories tempUC = new UserCategories();
						tempUC.setBusinessIds(i);
						tempUC.setCPrange(priceRange);
						clist.put(j, tempUC);
					} else {
						double priceRange = cat.getRestaurantsPriceRange2();
						UserCategories uc = clist.get(j);
						uc.updateBusinessId(i);
						uc.updateCPrange(priceRange);
					}
				}
			}

			else {
				// only training dataset is processed.
				ex.addgroundtruth(i);
			}
		}

		for (String k : clist.keySet()) {
			System.out.println("=======================================================");
			System.out.println("Category :" + k);
			System.out.println("Business ID :" + clist.get(k).getBusinessIds().toString());
			System.out.println("Count :" + clist.get(k).getCount());
			System.out.println("Range :" + clist.get(k).getCPrange());

			// System.out.println("MultiPhraseQuery: " + search_bids);
			TermQuery search_c = new TermQuery(new Term("cat", k));
			Query search_pr = DoublePoint.newExactQuery("prange", clist.get(k).getCPrange());
			// Query search_rate =DoublePoint.newRangeQuery("stars", 4.0 , 5.0);
			Query search_rate = DoublePoint.newExactQuery("stars", 5.0);
			Query search_rate1 = DoublePoint.newExactQuery("stars", 4.5);
			Query search_rate2 = DoublePoint.newExactQuery("stars", 4.0);

			BooleanQuery.Builder mainquery = new BooleanQuery.Builder();
			mainquery.add(search_c, BooleanClause.Occur.MUST);
			mainquery.add(search_pr, BooleanClause.Occur.MUST);
			mainquery.add(search_rate, BooleanClause.Occur.MUST);
			mainquery.add(search_rate1, BooleanClause.Occur.MUST);
			mainquery.add(search_rate2, BooleanClause.Occur.MUST);

			for (int l = 0; l < clist.get(k).getBusinessIds().size(); l++) {
				TermQuery mp = new TermQuery(
						new Term("bid", clist.get(k).getBusinessIds().get(l).replaceAll("-", "_")));
				mainquery.add(mp, BooleanClause.Occur.MUST_NOT);
			}

			BooleanQuery query = mainquery.build();

			System.out.println("Query: " + query.toString());
			// searcher.setSimilarity(new BM25Similarity());

			TopDocs tp = searcher.search(search_c, 5);

			// Calculate Hits
			// int hits = tp.totalHits;
			// System.out.println("Hits :" +hits);
			// ScoreDoc[] docs =tp.scoreDocs;

			for (ScoreDoc scoreDoc : tp.scoreDocs) {
				Document doc1 = searcher.doc(scoreDoc.doc);
				ex.addrecommended(doc1.get("bid"));
				System.out.println(doc1.toString());
			}
			// break;

		}
		System.out.println("calling AP for userid " + userID);
		ex.calAP();
		reader.close();
	}
}
