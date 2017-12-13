package com.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Evaluation {
	public static void calculateRecall() throws IOException{
		float recall=0;
		HashMap<String, ArrayList<String>> groundTruthMap =CSVUtils.readGroundTruth();
		HashMap<String, ArrayList<String>> outputMap =CSVUtils.readOutput();
		for(String business_id: groundTruthMap.keySet()){
			recall=0;
			List<String> gtValues=groundTruthMap.get(business_id);
			if(outputMap.containsKey(business_id)) {
				List<String> opValues=outputMap.get(business_id);
			
				for(String word:gtValues){
//					System.out.println("Word: "+word);
					if(opValues.contains(word))
						recall+=1;
				}
				System.out.println("Count match:"+recall);

				recall=(recall*100)/opValues.size();
			}
			System.out.println("Business Id:"+business_id);
			System.out.println("Recall:"+recall);
		}
	}
	public static void calculateMAP() throws IOException{
		float ap=0;
		float counter=0;
		HashMap<String, ArrayList<String>> groundTruthMap =CSVUtils.readGroundTruth();
		HashMap<String, ArrayList<String>> outputMap =CSVUtils.readOutput();
//		for(int groundTruthCounter=0;groundTruthCounter<)
//		System.out.println("Ground Keyset");
		float sum_ap=0;
		for(String business_id: outputMap.keySet()){
			ap=0;
			counter=0;
			List<String> gtValues=groundTruthMap.get(business_id);
			if(outputMap.containsKey(business_id)) {
//				counter÷÷+=1;
				List<String> opValues=outputMap.get(business_id);
				counter+=1;
				for(String word:opValues){
//					System.out.println("Word: "+word);
					if(gtValues.contains(word))
						ap+=1.0/counter;
				}
//				System.out.println("Count match:"+ap);

//				=(recall*100)/gtValues.size();
				ap=ap/opValues.size();
			}
			System.out.println("Business Id:"+business_id);
			System.out.println("AP :"+ap*100);
			sum_ap+=ap;
		}
		System.out.println("MAP:"+(sum_ap*100/groundTruthMap.keySet().size()));
	}
}
