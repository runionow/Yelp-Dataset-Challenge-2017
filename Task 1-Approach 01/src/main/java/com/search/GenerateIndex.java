package com.search;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;


import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class GenerateIndex {

	private static final String INDEXDIR =  "E:\\index";
	private Analyzer anaylzer;
	private IndexWriterConfig config;
	private IndexWriter Indexer;
	private BusinessData[] bd; 
	private HashMap<Integer,BusinessData> businessData;
	private HashMap<String, BusinessData> bdata;

	GenerateIndex() throws IOException{
		FSDirectory directory = FSDirectory.open(Paths.get(INDEXDIR));
		this.anaylzer = new StandardAnalyzer();
		this.config = new IndexWriterConfig(this.anaylzer);
		this.config.setOpenMode(OpenMode.CREATE);
		this.Indexer = new IndexWriter(directory, this.config);
	}

	GenerateIndex(String indexDir) throws IOException{
		FSDirectory directory = FSDirectory.open(Paths.get(indexDir));
		this.anaylzer = new StandardAnalyzer();
		this.config = new IndexWriterConfig(this.anaylzer);
		this.config.setOpenMode(OpenMode.CREATE);
		this.Indexer = new IndexWriter(directory, this.config);
	}

	private void addToIndexTask1(BusinessData bd) throws IOException {
		List<String> arr = bd.getCategories();
		Document document = new Document();

		document.add(new TextField("bid", bd.getBusinessId().replaceAll("-", "_"), Field.Store.YES));
		document.add(new TextField("name",bd.getName(),Field.Store.YES));
		document.add(new TextField("city",bd.getCity(),Field.Store.YES));
		document.add(new TextField("state",bd.getState(),Field.Store.YES));

		for (int i=0; i<arr.size(); i++){
			Field field = new StringField("cat", arr.get(i), Field.Store.YES);
			// System.out.println("Catalog Item :" +  arr.get(i));
			document.add(field);
		}
		System.out.println();
		document.add(new DoublePoint("prange",bd.getRestaurantsPriceRange2()));
		document.add(new DoublePoint("stars",bd.getStars()));
		this.Indexer.addDocument(document);
	}

	public void crawlTask1Data() throws IOException {
		MongoDBConnector mdc = new MongoDBConnector();
		bdata = mdc.businessData();

		for(String key :bdata.keySet()) {
			BusinessData data = bdata.get(key);
			System.out.println("Crawled :" + key);
			this.addToIndexTask1(data);
		}
	}
	
//	public void crawlTask2Data() throws IOException {
//		MongoDBConnector mdc = new MongoDBConnector();
//		bdata = mdc.businessData();
//
//		for(String key :bdata.keySet()) {
//			BusinessData data = bdata.get(key);
//			System.out.println("Crawled :" + key);
//			this.addToIndexTask1(data);
//		}
//	}

	public void close() throws IOException {
		this.Indexer.close();
	}
}
