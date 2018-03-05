package project;

import java.util.HashMap;
import java.util.List;

public class Reducer {

	
	private HashMap<String,Integer> dict;
	
	Reducer() {
		
		dict = new HashMap<String,Integer>();
	}
	//Forms the final HashMap with the count of keys and their exact value
	public HashMap<String,Integer> reduce (HashMap<String,List<Integer>> list) {
		
		for (String key : list.keySet()) {
			dict.put(key, list.get(key).size());	
		}	
		
		return dict;		
	}
	
	
	
	
}
