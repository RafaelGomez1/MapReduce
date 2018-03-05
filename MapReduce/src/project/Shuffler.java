package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Shuffler {
	
	private HashMap<String,List<Integer>> shuffledDict;
	private List<Integer> values;
	private List<String> text;
	
	Shuffler(){
		
		values = new ArrayList<Integer>();
		text = new ArrayList<String>();
		shuffledDict = new HashMap<String,List<Integer>>();
	}
	//Groups pairs of the some key and forms a list of their values
	public HashMap<String,List<Integer>> shuffle (List<HashMap<String,Integer>> list) {
		//Initial value of the keys
		values.add(1);
		
		//Creation of the main map of the shuffler
		
		for (int x = 0; x < list.size(); x++) {
			text.add((String) (list.get(x).keySet().toArray()[0]));
			shuffledDict.put(text.get(x), values);			
		}
		
		for (String key : shuffledDict.keySet()) {
			values = new ArrayList<Integer>();
			for (String c : text) {
				if (key.equals(c)) {					
					values.add(1);
					shuffledDict.put(key, values);					
				}
			}			
		}		
		return shuffledDict;		
	}
	

}
