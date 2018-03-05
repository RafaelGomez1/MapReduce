package testing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TestMain {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File file1 = new File("ArcTecSw_2018_BigData_Practica_Part1_Sample_Result.txt");
		File file2 = new File("result1.txt");
		HashMap<String,Integer> dict1 = read(file1);
		HashMap<String,Integer> dict2 = read(file2);
				
		System.out.println(dict1.keySet() + "/n");
		System.out.println(dict2.keySet() + "/n"); 	
		
	}
	
	public static HashMap<String,Integer> read(File file) throws NumberFormatException, IOException {
		
		List<String> list = new ArrayList<String>();
		HashMap<String,Integer> dict1 = new HashMap<String,Integer>();
		try {
			
			BufferedReader reader =	new BufferedReader(new FileReader(file));
			String line;
			String[] words = new String[2];			
			while ((line = reader.readLine()) != null) {
				if(line.length() > 0 ) {
					line = line.toLowerCase();
					words = line.split(" : ");
					//System.out.println("Key : " + words[0] + " Value : " + words[1]);
					dict1.put(words[0], Integer.parseInt(words[1]));
					
				}
			}
			reader.close();			
		//System.out.println(dict1);			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("hi this is the list size : " + list.size());
		return dict1;
	}
	
}
