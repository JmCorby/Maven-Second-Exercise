package com.exist;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Properties;

public class PracticeTwoMethod {

	Scanner _sc;
	
	public PracticeTwoMethod(Scanner sc) {
		_sc = sc;
	}

	public void openFile(ArrayList<LinkedHashMap<String, String>> dataRows) {
		
		File myObj = new File("");
		File defaultFile = new File("corbypop.txt");
		String enterFile = new String("");

		while (true) {
			System.out.println("Choose file to open: \n1.Default \n2.Another file");

			int response = _sc.nextInt();
			System.out.print("\nOpening: ");

			if (response == 1) {
				System.out.print("Default file");
			} else if (response == 2) {
				System.out.print("Enter file name: ");
				_sc.nextLine();
				enterFile = _sc.nextLine();
			}

			switch (response) {
			case 1:
				myObj = defaultFile;
				break;
			case 2:
				File secondFile = new File(enterFile);
				myObj = secondFile;
				break;
			default:
				break;
			}

			try {
			Scanner myReader = new Scanner(myObj);
				while (myReader.hasNextLine()) {
					String data = myReader.nextLine();
					
					LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
					String key = "";
					String value = "";
					
					while (data.indexOf("(") != -1) {
						int openPar = data.indexOf("(");
						int closePar = data.indexOf(")");
					
						if (openPar >= 0) {
							String pair = data.substring(openPar + 1, closePar);
							
							key = pair.substring(0, pair.indexOf(","));
							
							value = pair.substring(pair.indexOf(",") + 2);
							
						}
						data = data.substring(closePar + 1);
						map.put(key, value);
					}
					dataRows.add(map);
				}
					
			myReader.close();
			} catch (FileNotFoundException e) {
			System.out.println("File does not exist.");
			}
			break;
		}
		
	}
	
    public void print(ArrayList<LinkedHashMap<String, String>> dataRows) {
        int rowIndex = 1;
        
        dataRows.stream().forEach(dataRow ->  {
			System.out.println("");
            dataRow.entrySet().stream().forEach(cell -> {
                String key = cell.getKey();
                String value = cell.getValue();
                System.out.printf("( %s , %s )" , key, value);
            });
            
        });
    }
	
    public void search(ArrayList<LinkedHashMap<String, String>> dataRows) {
        System.out.println();
        
        _sc.nextLine();

        System.out.print("Enter what do you want search: ");
        String arg = _sc.nextLine();
		
		AtomicInteger rowIndex = new AtomicInteger(1);
		dataRows.stream().forEach(dataRow -> {
			System.out.println("");
			AtomicInteger hashMapIndex = new AtomicInteger(1);
			dataRow.entrySet().forEach(cell -> {
				if (cell.getValue().contains(arg)) {
                    System.out.println("It is found at cell: (" + rowIndex + "," + hashMapIndex + ")" + " , Value");
                }
				if (cell.getKey().contains(arg)) {
                    System.out.println("It is found at cell: (" + rowIndex + "," + hashMapIndex + ")" + " , Key");
                }
				
				hashMapIndex.getAndIncrement();
			});
			
			rowIndex.getAndIncrement();
		});
    }
	
    public void edit(ArrayList<LinkedHashMap<String, String>> dataRows) {
        System.out.println();
        
        _sc.nextLine();
        System.out.println("Enter row to edit: ");
        int row = _sc.nextInt();
        
        LinkedHashMap<String, String> map = dataRows.get(row - 1);
        
        System.out.println("Enter column to edit: ");
        int col = _sc.nextInt();
        
        _sc.nextLine();
        System.out.println("Enter new key: ");
        String newKey = _sc.nextLine();
        System.out.println("Enter new value: ");
        String newValue = _sc.nextLine();
        
        AtomicInteger hashMapIndex = new AtomicInteger(1);
		
		//remove old hashmap entry
		LinkedHashMap<String, String> workMap = new LinkedHashMap<String, String>();
        map.entrySet().forEach(item -> {
            workMap.put(item.getKey(), item.getValue());
        });
		
        workMap.entrySet().forEach(item -> {
            if (hashMapIndex.get() == col){
                map.remove(item.getKey());
            }
            
            hashMapIndex.getAndIncrement();
        });
        
        AtomicInteger hashMapIndex1 = new AtomicInteger(1);
        
        // reinsert everything on a new hashMapIndex
        LinkedHashMap<String, String> newMap = new LinkedHashMap<String, String>();
        map.entrySet().forEach(item -> {
            if (hashMapIndex1.get() == col){
                newMap.put(newKey, newValue);
            }
            
            newMap.put(item.getKey(), item.getValue());
            
            hashMapIndex1.getAndIncrement();
        });
        
        dataRows.set(row - 1, newMap);
        
        print(dataRows);
    }
	
	public ArrayList<LinkedHashMap<String, String>> reset(ArrayList<LinkedHashMap<String, String>>  dataRows) {
		System.out.println();

		System.out.print("Enter number of rows: ");  
        int rows = _sc.nextInt(); 
		
        System.out.print("\nEnter number of elements: ");  
        int elements = _sc.nextInt();

        System.out.print("\nEnter number of characters in a key: ");  
        int keyChar = _sc.nextInt(); 
		
        System.out.print("\nEnter number of characters in a value: ");  
        int valueChar = _sc.nextInt();

        System.out.println("\nRows: " + rows);
        System.out.println("Elements: " + elements);
        System.out.println("Key characters: " + keyChar);
        System.out.println("Value characters: " + valueChar);

       	for (int i = 0; i < rows; i++) {
       		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
       		String key = "";
       		String value = "";
       		for (int j = 0; j < elements; j++) {
       			char[] forKey = new char[keyChar];
       			for (int k = 0; k < keyChar; k++) {
       				forKey[k] = (char) ((int) Math.round(Math.random()*93+33));
       				String str = new String();
       				key = str.valueOf(forKey);
       				char[] forValue = new char[valueChar];
       				for (int h = 0; h < valueChar; h++) {
	       				forValue[h] = (char) ((int) Math.round(Math.random()*93+33));
	       				value = str.valueOf(forValue);
       				}
       			}
       			map.put(key, value);
       		}
        dataRows.add(map);
       }

       try {
            FileWriter writer = new FileWriter("dataRows.txt");
            for (LinkedHashMap<String, String> map : dataRows) {
                for (String key : map.keySet()) {
                    writer.write(key + ":" + map.get(key) + ",");
                }
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

		return dataRows;
	}
	
}
