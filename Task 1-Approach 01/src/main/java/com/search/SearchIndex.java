package com.search;

import org.apache.lucene.index.Term;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.index.MultiFields;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.util.BytesRef;

import java.io.IOException;
import java.nio.file.Paths;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.similarities.BM25Similarity;


public class SearchIndex {
	
		// TODO Auto-generated constructor stub
		private final IndexReader reader;
		private final IndexSearcher searcher;
		private final Analyzer analyzer;
		private final QueryParser parser;
		private final Query query;
		private final BM25Similarity dSimi;
		private  int noOfTerms;

		// Constructor
		public SearchIndex(String queryString) throws IOException, ParseException {
			this.reader = DirectoryReader.open(FSDirectory.open(Paths.get("E:\\index")));
			this.searcher = new IndexSearcher(reader);
			this.analyzer = new StandardAnalyzer();
			this.parser = new QueryParser("TEXT", analyzer);
			this.query = parser.parse(QueryParser.escape(queryString));
			this.dSimi = new BM25Similarity();
		}

	}

