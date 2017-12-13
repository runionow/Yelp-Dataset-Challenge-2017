package com.search;

import org.apache.lucene.document.Document;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.document.TextField;

import java.io.IOException;
import java.nio.file.Paths;

public class GenerateIndex {

	private static final String INDEXDIR =  "/Users/janani/Desktop/myProjIndex";
	private Analyzer anaylzer;
	private IndexWriterConfig config;
	private IndexWriter Indexer;

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
	
    // These are the values that are to be indexed.
	// The idea is to create an object and map these values accordingly. 
	public void addToIndex() throws IOException {
		
		Document document = new Document();
		document.add(new TextField("business_id", "Hello", Field.Store.YES));
		document.add(new TextField("name", "Hello", Field.Store.YES));
		document.add(new TextField("city", "Hello", Field.Store.YES));
		document.add(new TextField("state", "Hello", Field.Store.YES));
		document.add(new TextField("stars", "Hello", Field.Store.YES));
		document.add(new TextField("price", "Hello", Field.Store.YES));
		document.add(new TextField("categories", "Hello", Field.Store.YES));
		putIndexWriter(document);
	}
	
	public void part2DocCreation() throws Exception {
		System.out.println("Creating Index ");
		for (ReviewItem r: CSVUtils.returnDocList()) {
			Document document = new Document();
			document.add(new TextField("business_id", r.getBusinessId().replaceAll("-", "_"), Field.Store.YES));
//			document.add(new TextField("user_id", r.getUserId(), Field.Store.YES));
			document.add(new TextField("ratings", r.getRatingStars(), Field.Store.YES));
			document.add(new TextField("review_text", r.getReviewText(), Field.Store.YES));
			putIndexWriter(document);
		}
		System.out.println("Done with indexing");
	}
		
    private void putIndexWriter(Document document) throws IOException {
    	this.Indexer.addDocument(document);
	}

	public void close() throws IOException {
    	this.Indexer.close();
    }
	
}
