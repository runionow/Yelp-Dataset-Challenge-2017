package com.search;

import java.util.ArrayList;
import java.util.HashMap;

public class App {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		System.out.println("Arun-Random");
//		MongoDBConnector mdc = new MongoDBConnector();
//		HashMap<String, ArrayList<String>> groundTruthMap =CSVUtils.readOutput();	
		GenerateIndex index = new GenerateIndex();
		index.part2DocCreation();
		index.close();
		dummy dm = new dummy();
		dummy.main(args);
		Evaluation.calculateRecall();
		Evaluation.calculateMAP();
	}
}
