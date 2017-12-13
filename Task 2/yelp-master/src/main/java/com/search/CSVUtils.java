package com.search;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class CSVUtils {

    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE = '"';

    public static void main(String[] args) throws Exception {
    	returnDocList();
    }
    public static List<ReviewItem> returnDocList() throws Exception {

        String csvFile = "/Users/janani/PycharmProjects/SEARCH/sentiment_analysis.csv";
        int counter=0;
        Scanner scanner = new Scanner(new File(csvFile));
        List<ReviewItem> listReviewItem = new ArrayList<ReviewItem>();
        while (scanner.hasNext()) {
        	try{	
        	List<String> line = parseLine(scanner.nextLine());
//    		System.out.println("kk line:"+line);
    		String modifiedReviewText=line.get(3).toLowerCase().replaceAll("\"", "");
//    		System.out.println("modifiedReviewText:"+modifiedReviewText);
//       		System.out.println("kk:"+line.get(4));	
        	if(counter>0 && Float.parseFloat(line.get(4)) >= 0) {
        		ReviewItem	tempReviewItem= new ReviewItem(line.get(0),line.get(1),line.get(2),modifiedReviewText);
//        		System.out.println("User id= " + line.get(0) + ", Business Id= " + line.get(1) + " , Rating=" + line.get(2) + " , Review Text=" + line.get(3));
        		listReviewItem.add(tempReviewItem);
        	}
        	}catch(Exception e) {

        	}
       	    
        	counter++;

        }
        System.out.println("Unfiltered Counter="+counter);

        counter=0;

//        for (ReviewItem r: listReviewItem) {

//        	System.out.println("Counter="+counter);
//        	System.out.println("getBusinessId"+r.getBusinessId());
//        	System.out.println("getUserId"+r.getUserId());
//        	System.out.println("getRatingStars"+r.getRatingStars());
//        	System.out.println("getReviewText"+r.getReviewText());
//        	counter++;
//        }
        System.out.println("Counter="+listReviewItem.size());
        scanner.close();
        return listReviewItem;
    }

    public static List<String> parseLine(String cvsLine) {
        return parseLine(cvsLine, DEFAULT_SEPARATOR, DEFAULT_QUOTE);
    }

    public static List<String> parseLine(String cvsLine, char separators) {
        return parseLine(cvsLine, separators, DEFAULT_QUOTE);
    }

    public static List<String> parseLine(String cvsLine, char separators, char customQuote) {

        List<String> result = new ArrayList<String>();

        //if empty, return!
        if (cvsLine == null && cvsLine.isEmpty()) {
            return result;
        }

        if (customQuote == ' ') {
            customQuote = DEFAULT_QUOTE;
        }

        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuffer curVal = new StringBuffer();
        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;

        char[] chars = cvsLine.toCharArray();

        for (char ch : chars) {

            if (inQuotes) {
                startCollectChar = true;
                if (ch == customQuote) {
                    inQuotes = false;
                    doubleQuotesInColumn = false;
                } else {

                    //Fixed : allow "" in custom quote enclosed
                    if (ch == '\"') {
                        if (!doubleQuotesInColumn) {
                            curVal.append(ch);
                            doubleQuotesInColumn = true;
                        }
                    } else {
                        curVal.append(ch);
                    }

                }
            } else {
                if (ch == customQuote) {

                    inQuotes = true;

                    //Fixed : allow "" in empty quote enclosed
                    if (chars[0] != '"' && customQuote == '\"') {
                        curVal.append('"');
                    }

                    //double quotes in column will hit this!
                    if (startCollectChar) {
                        curVal.append('"');
                    }

                } else if (ch == separators) {

                    result.add(curVal.toString());

                    curVal = new StringBuffer();
                    startCollectChar = false;

                } else if (ch == '\r') {
                    //ignore LF characters
                    continue;
                } else if (ch == '\n') {
                    //the end, break!
                    break;
                } else {
                    curVal.append(ch);
                }
            }

        }

        result.add(curVal.toString());

        return result;
    }
    
    public static List<String> readDishList() throws IOException {

        String csvFile = "/Users/janani/Downloads/mexican_dish list - Sheet.csv";
        int counter=0;
        Scanner scanner = new Scanner(new File(csvFile));
        List<String> listReviewItem = new ArrayList<String>();
        while (scanner.hasNext()) {
        	List<String> line = parseLine(scanner.nextLine());
        		String tempReviewItem= new String(line.get(0));
        		if(!listReviewItem.contains(tempReviewItem.toLowerCase()))
        			listReviewItem.add(tempReviewItem.toLowerCase());
        	counter++;
        }
//        System.out.print("Counter="+counter);
        scanner.close();
        return listReviewItem;
    }
    
    // Reading the ground truth
    public static HashMap<String, ArrayList<String>> readGroundTruth() throws IOException {
        HashMap<String, ArrayList<String>> groundTruthHashmap = 
                new HashMap<String, ArrayList<String>>();

        String csvFile = "/Users/janani/Downloads/ground_truth7.csv";
        int counter=0;
        Scanner scanner = new Scanner(new File(csvFile));
        while (scanner.hasNext()) {
        	List<String> line = parseLine(scanner.nextLine());
        		String business_id= new String(line.get(0)).replaceAll("-", "_").toLowerCase();
//        		System.out.println("file exist");
        		ArrayList<String> groundTruthDishList= new ArrayList<String>();
        		for (int i=1;i<=9;i++) {
        			try {
        			groundTruthDishList.add(i-1,line.get(i));
//    				System.out.print(i+"= "+line.get(i));
        			}catch(Exception e) {
//        				System.out.println(i+" not found");
        			}        			
//    				System.out.println();

        		}
        		groundTruthHashmap.put(business_id, groundTruthDishList);
        	counter++;
        }
//        System.out.print("Counter="+counter);
        scanner.close();
        
        return groundTruthHashmap;
    }
    // Reading the ground truth
    public static HashMap<String, ArrayList<String>> readOutput() throws IOException {
        HashMap<String, ArrayList<String>> groundTruthHashmap = 
                new HashMap<String, ArrayList<String>>();

//        String csvFile = "/Users/janani/Downloads/yelp-master/PartialMatch.csv";
        String csvFile = "/Users/janani/Downloads/yelp-master/FinalOutput.csv";
        int counter=0;
        Scanner scanner = new Scanner(new File(csvFile));
        scanner.nextLine();
        while (scanner.hasNext()) {
        	List<String> line = parseLine(scanner.nextLine());
        		String business_id= new String(line.get(0)).replaceAll("-", "_").toLowerCase();
//        		System.out.println("file exist");
        		ArrayList<String> groundTruthDishList= new ArrayList<String>();
        		for (int i=1;i<=9;i++) {
        			try {
        			groundTruthDishList.add(i-1,line.get(i));
//    				System.out.print(i+"= "+line.get(i));
        			}catch(Exception e) {
//        				System.out.println(i+" not found");
        			}        			
//    				System.out.println();

        		}
        		groundTruthHashmap.put(business_id, groundTruthDishList);
        	counter++;
        }
//        System.out.print("Counter="+counter);
        scanner.close();
        
        return groundTruthHashmap;
    }

}