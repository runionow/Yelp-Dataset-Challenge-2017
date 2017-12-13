package com.search;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Fields;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.MultiFields;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.PhraseQuery.Builder;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.search.BooleanClause;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class dummy {

	public static void main(String[] args) throws IOException 
	{

		List<String> dishList = CSVUtils.readDishList();
//		System.out.print("INNNNN ");
		IndexReader reader=DirectoryReader.open(FSDirectory.open(Paths.get("/Users/janani/Desktop/myProjIndex")));

		IndexSearcher searcher = new IndexSearcher(reader);

		QueryParser parser = new QueryParser("review_text" ,new StandardAnalyzer());
		List<String> businessList = new ArrayList<String>();
		//IDF Calculation
		try 
		{	   
			Fields fields = MultiFields.getFields(reader);
			Terms terms = fields.terms("business_id");
			TermsEnum iterator = terms.iterator();
			BytesRef byteRef = null;
			while((byteRef = iterator.next()) != null) {
				businessList.add(byteRef.utf8ToString());
			}
			HashMap<String, Integer> business_FamousDishMap = new HashMap<String, Integer>();
			ArrayList<TopItem> topItemsList = new ArrayList<TopItem>();
			for (int i=0;i<businessList.size();i++){
//				if(i>20)
//					break;
				HashMap<String, ArrayList<String>> groundTruthMap =CSVUtils.readGroundTruth();

				HashMap<String, Integer> dishesScore = new HashMap<String, Integer>();
				List<String> testBusinessId = new ArrayList<String>(groundTruthMap.keySet());
				for(int a =0;a<testBusinessId.size();a++){
					
				if(businessList.get(i).equalsIgnoreCase(testBusinessId.get(a)))
				{
					System.out.println("*****");
					System.out.println(businessList.get(i));
					QueryParser parser2 = new QueryParser("business_id" ,new StandardAnalyzer());
					String s=businessList.get(i);
					Query query = parser2.parse(s);
					TopDocs docs= searcher.search(query,50000);
					System.out.println("Count:"+docs.scoreDocs.length);

					for (ScoreDoc scoreDoc : docs.scoreDocs) {
						Document document = searcher.doc(scoreDoc.doc);		
						for(String dish: dishList) {
							Query query2 = null;
							Builder phraseQueryBuilder = new PhraseQuery.Builder(); 
							if(dish.contains(" ")) {
								String[] words = dish.split(" "); 
								for (String word : words) 
								{ 
									phraseQueryBuilder.add(new Term("review_text", word)); 
								} 
								query2=phraseQueryBuilder.build();
							}
							else{
								// USE this for partial Match
//								query2 = parser.parse(dish+"*");
								// USE this for Exact Match
								query2 = parser.parse(dish);
							}
							//						booleanQuery.add(query, BooleanClause.Occur.MUST);
							BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();
							booleanQuery.add(query, BooleanClause.Occur.MUST);
							booleanQuery.add(query2, BooleanClause.Occur.MUST);
							//						System.out.println(booleanQuery.build());
							TopDocs docs2= searcher.search(booleanQuery.build(),50000);
							if(docs2.scoreDocs.length>0) {								
								dishesScore.put(dish, docs2.scoreDocs.length);
								for (ScoreDoc scoreDoc2 : docs2.scoreDocs) {
									Document document2 = searcher.doc(scoreDoc2.doc);
									String id = document2.getField("review_text").stringValue();
								}
							}
						}
					}
				}
			}
			Set<Entry<String, Integer>> set = dishesScore.entrySet();
            List<Entry<String, Integer>> sortedList = new ArrayList<Entry<String, Integer>>(set);
            Collections.sort( sortedList, new Comparator<Map.Entry<String, Integer>>()
            {
    			public int compare( Map.Entry<String, Integer> object1, Map.Entry<String, Integer> object2) {
    				return (object2.getValue()).compareTo( object1.getValue() );
    			}
            } );
            
            int counter = 0;
            for(Map.Entry<String, Integer> entry : sortedList) {
            	if(entry.getValue() > 1) {
            		counter ++;
            	}
            }
            int final_count=5;
            List<String> topDishString= new ArrayList<String>();
            List<Integer> topDishCount= new ArrayList<Integer>();            
            final_count = (counter > 7) ? 7: counter;
            for(int i1 =0;i1<final_count;i1++){
//            for(Map.Entry<String, Integer> entry : sortedList) {
            	Entry<String, Integer> entry=sortedList.get(i1);
//            		System.out.println((i1+1)+". "+entry.getKey()+" = "+entry.getValue());
            		topDishString.add(i1, entry.getKey());
            		topDishCount.add(i1, entry.getValue());
//                	System.out.println(entry.getValue());
//					business_FamousDishMap.put(entry.getKey(), entry.getValue());

            }
            if(topDishString.size()>0) {
            String[] array = topDishString.toArray(new String[0]);
            TopItem topItem = new TopItem(businessList.get(i),array);
    		topItemsList.add(topItem);
            }

            //        	System.out.println("TOP DISHES");
//            for(Map.Entry<String, Integer> entry : business_FamousDishMap.entrySet()) {
//            	System.out.println(entry.getKey());
//            	System.out.println(entry.getValue());
//			}
			}
			CsvFileWriter.writeCsvFile(topItemsList);

		}

		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
