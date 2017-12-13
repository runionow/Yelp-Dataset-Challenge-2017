package com.search;

import java.util.ArrayList;
import java.util.List;

public class Evaluation {

	public Evaluation() {
		// TODO Auto-generated constructor stub
	}
	
	private  List<String> groundtruth =new ArrayList<String>();
	private List<String>recommended= new ArrayList<String>();
	private double precision;
	private double recall;
    public static double MAP;	 
	
	public void addgroundtruth(String s)
	{
		groundtruth.add(s);
	}
	public void addrecommended(String e)
	{
		recommended.add(e);
	}
	
	public void calAP()
	{
		int cr=0,cm=0;
		float AP=0;
		
		System.out.println("Inside AP");
		System.out.println("Ground truth" + groundtruth.toString());
		System.out.println("Recommended "+ recommended.size() +" "+ recommended.toString());
		for(String i: recommended)
		{
			cr++;
			for(String k:groundtruth)
			{
				if(i.matches(k)||i.equals(k))
				{
					 cm++;
					 AP= AP+(cm/cr);
					 //preval.add((float) (cm/cr));
				}	
			}
		}
		
	    AP=AP/groundtruth.size();
		
		precision= cm/recommended.size();
		recall =cm/groundtruth.size();
		
		MAP =MAP+AP;
		System.out.println("Matched" + cm);
		System.out.println("PRECISON: "+ precision);
		System.out.println("RECALL: "+ recall);
		System.out.println("AVERAGE PRECISON: "+ AP);
	}
}
