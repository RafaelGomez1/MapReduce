package project;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class Map extends Thread{
	
	
	private static HashMap<String,Integer> dictionary;
	private static List<HashMap<String,Integer>> list;
	
	Map() {
		dictionary = new HashMap<String,Integer>();	
		list = new ArrayList<HashMap<String,Integer>>();
	}
	//Separates the text line into words
	synchronized void Mapp (String line) {
		
		String [] word = line.split(" ");
		dictCreator(word);		
	}
	//Creates the Pair [key,value] for each word of the line
	synchronized void dictCreator (String[] word) {
		
		for (String s : word) {
			dictionary.put(s, 1);
			list.add(dictionary);
			//showDictList();
			//System.out.println("");
			dictionary = new HashMap<String,Integer>();			
		}
	}
	
	private void showDictList() {
				
		Set keys = dictionary.keySet();
		
		for (Iterator it = keys.iterator(); it.hasNext();) {
			
			String key = (String) it.next();
	        String value = String.valueOf(dictionary.get(key));
	        System.out.print("key=" + key + ", value=" + value);
			
		}
	}
	
	public static HashMap<String,Integer> getDictionary() {
		return dictionary;
	}	
	
	public static List<HashMap<String,Integer>> getDictList() {
		return list;
	}
	synchronized void merge(HashMap<String,Integer> dictionary, String[] word) {		
		for (String s : word) {
			dictionary.merge(s, 1, Integer::sum);
		}		
	}
}
	