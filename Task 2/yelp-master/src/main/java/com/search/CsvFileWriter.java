package com.search;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvFileWriter {
	
	//Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	
	//CSV file header
	private static final String FILE_HEADER = "business_id,top_item1,top_item2,top_item3,top_item4,top_item5";

	public static void writeCsvFile(List<TopItem> itemList) {
		
		//Create new students objects
/*		Student student1 = new Student(1, "Ahmed", "Mohamed", "M", 25);
		Student student2 = new Student(2, "Sara", "Said", "F", 23);
		Student student3 = new Student(3, "Ali", "Hassan", "M", 24);
		Student student4 = new Student(4, "Sama", "Karim", "F", 20);
		Student student5 = new Student(5, "Khaled", "Mohamed", "M", 22);
		Student student6 = new Student(6, "Ghada", "Sarhan", "F", 21);
		
		//Create a new list of student objects
		List students = new ArrayList();
		students.add(student1);
		students.add(student2);
		students.add(student3);
		students.add(student4);
		students.add(student5);
		students.add(student6);
		
*/		FileWriter fileWriter = null;
				
		try {
			fileWriter = new FileWriter("FinalOutput.csv");
//			fileWriter = new FileWriter("PartialMatch.csv");
			//Write the CSV file header
			fileWriter.append(FILE_HEADER.toString());
			
			//Add a new line separator after the header
			fileWriter.append(NEW_LINE_SEPARATOR);
			
			//Write a new student object list to the CSV file
			for (TopItem item : itemList) {
				fileWriter.append(item.getBusiness_id());
				fileWriter.append(COMMA_DELIMITER);
				if(item.getBusiness_name() != null) {
				fileWriter.append(item.getBusiness_name());
				fileWriter.append(COMMA_DELIMITER);
				}
				for (int i=0;i<item.getTopItems().length;i++){
					fileWriter.append(item.getTopItems()[i]);
					if(i!=item.getTopItems().length-1)
						fileWriter.append(COMMA_DELIMITER);					
				}
				fileWriter.append(NEW_LINE_SEPARATOR);

				
/*				fileWriter.append(item.getTopItem_1());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(item.getTopItem_1_Count()));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(item.getTopItem_2());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(item.getTopItem_2_Count()));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(item.getTopItem_3());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(item.getTopItem_3_Count()));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(item.getTopItem_4());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(item.getTopItem_4_Count()));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(item.getTopItem_5());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(item.getTopItem_5_Count()));
				fileWriter.append(NEW_LINE_SEPARATOR);
*/			}

			
			
			System.out.println("CSV file was created successfully !!!");
			
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
			}
			
		}
	}
}