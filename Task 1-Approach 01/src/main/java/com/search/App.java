package com.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;

public class App {

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		System.out.println("Task 1");
		System.out.println("======\n");

		// Indexing.
//		GenerateIndex gi = new GenerateIndex();
//		gi.crawlTask1Data();
//		gi.close();

		// Query Generate.
		CsvWriter cw = new CsvWriter("E:\\userBid.csv");
		

		// String[] business = new String[] {"C0Gy3NUOKCh0ffMzaXhXlQ",
		// "806kkDGaRCJ4lZLRcEf-iw", "fz8wgxbKnwa_fdk_W5M4Ow", "m4PW-GMc8JOU5nmMYncu-Q",
		// "Jl2MIN70I2DGw8KhVp6g7Q", "3f0-EXRF0oy6NDsFh9TJKA"};
		// String[] business1 = new String[] {"nFR7dDedxRuBeZz_6Cdalg",
		// "ueoRWPGrSoZizl1ngBghqg", "HTlsBAd9j1wlWDHxnYczBw", "HxWDCU-kKido3ErM31msGQ",
		// "a2ZBDIXmwBm7IWnXR6LK-g", "C2BR0TjNacoNzItPLUHTAA", "-MuatiMmslPOvk9kOMyjkA",
		// "Ly2ShApiomYZwKehwb7eRA", "YSYDJTCt5kzY2kKnLNGRiA", "LUDX--wfStrKavGyitk4nA",
		// "Er2XAYN8l1BadlYFwiPb9g", "2uRM8Et0uJVl8u1jSnmuKw",
		// "7yIHC8KsR5OaE_7MgEBVhw"};
		// List<String> bList = new ArrayList<String>();
		// List<String> bList1 = new ArrayList<String>();
		// Collections.addAll(bList, business);
		// Collections.addAll(bList1, business1);
		//
		// QueryGenerate qg = new QueryGenerate("fzCFYwjrTyvCKoecPuq3Bg",bList);
		// QueryGenerate qg1 = new QueryGenerate("DKPqjzE-I6a0vsvYI7GDrQ",bList1);

	}
}
