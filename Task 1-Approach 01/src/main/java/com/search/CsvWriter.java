package com.search;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class CsvWriter {
	
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";

	public CsvWriter(String fileName) throws IOException {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Arun");
		MongoDBConnector mdc = new MongoDBConnector();
		HashMap<String, List<String>> ub = new HashMap<String, List<String>>();
		ub = mdc.userBusinnessData();
		// System.out.println(String.valueOf(mdc.getBusinessRating("jYqOPpSmtKbKzf0Z_g-Oyg")));
		mdc.close();
		
		
		for (String user : ub.keySet()) {
			List<String> temp = ub.get(user);
			for (String s : temp) {
				MongoDBConnector mdc1 = new MongoDBConnector();
				fileWriter.append(String.valueOf(user));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(s));
				fileWriter.append(COMMA_DELIMITER);
				// System.out.println(s.replaceAll("\"", ""));
				fileWriter.append(String.valueOf(mdc1.getBusinessRating(s.replaceAll("\"", ""))));
				fileWriter.append(NEW_LINE_SEPARATOR);
				mdc1.close();
			}
		}
		fileWriter.close();
	}
}
