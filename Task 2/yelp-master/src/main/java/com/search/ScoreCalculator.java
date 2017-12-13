package com.search;
//
//import java.io.IOException;
//import java.nio.file.Paths;
//import java.util.List;
//
//import org.apache.lucene.index.DirectoryReader;
//import org.apache.lucene.index.IndexReader;
//import org.apache.lucene.index.LeafReaderContext;
//import org.apache.lucene.store.FSDirectory;
//
//public class ScoreCalculator {
//	
//	public String dishesList = "Enchillada";
//	public String indexPath = "/Users/janani/Desktop/myProjIndex";
//	
//	public void calculateScores(String indexPath) throws IOException {
//	IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(indexPath)));
//	
//	List<LeafReaderContext> leafContexts = reader.getContext().reader()
//			.leaves();
//
//	for (LeafReaderContext leafContext : leafContexts) {
//		
//	}
//	
//	
//	}
//
//	private void calculateTFIDF() {
//		
//		
//	}
//	
//}
