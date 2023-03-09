package com.exist;

//import com.exist.PracticeTwoMethod;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class PracticeTwoMain {

	public static void main(String[] args) {
		String[][] table = {};
		ArrayList<int[]> indexes = new ArrayList<int[]>();
		ArrayList<LinkedHashMap<String, String>> dataRows = new ArrayList<LinkedHashMap<String, String>>();
		
		Scanner sc = new Scanner(System.in);
		PracticeTwoMethod method = new PracticeTwoMethod(sc);
		
		method.openFile(dataRows);

		if (dataRows.isEmpty()) {
			System.exit(0);
		} else {

			while (true) {
			
			System.out.println("\nMenu: \n1. Search \n2. Edit \n3. Print \n4. Reset \n5. Add Rows \n6. Exit \n\nEnter menu option:");
			
			int response = sc.nextInt();
			System.out.print("\nYour response: ");
			
			if (response == 1) {
				System.out.print("Search");
			} else if (response == 2) {
				System.out.print("Edit");
			} else if (response == 3) {
				System.out.print("Print");
			} else if (response == 4) {
				System.out.print("Reset");
			} else if (response == 5) {
				System.out.print("Add Rows");
			} else if (response == 6) {
				System.out.print("Exit");
			} else {
				System.out.println("Not valid response");
			}
			
			System.out.println();

			switch (response) {
				case 1:
					method.search(dataRows);
					break;
				case 2:
					method.edit(dataRows);
					break;
				case 3:
					method.print(dataRows);
					break;
				case 4:
					dataRows.clear();
					method.reset(dataRows);
					break;
				case 5:
					method.reset(dataRows);
					break;
				case 6:
					System.exit(0);
					break;
				default:
					break;
				}
			}
		}
	}
}