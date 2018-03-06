package project;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		
		long startTime = System.nanoTime();
		
		//Load of the running parameters for the file1
		
		boolean threads = isThread(args);
		File file;
		
		//Execution of the MapReduce algorithm on the designated file
		if (isFileParameter(args)) {
			file = getFile(args);
			System.out.println("file is : " + file);
			List<String> lines = readFile(file);
			Splitter sp = new Splitter();
			Map m = new Map();
			Shuffler shuf = new Shuffler();
			sp.Split(lines,1,threads,m);
			HashMap<String,List<Integer>> suffledDict = shuf.shuffle(m.getDictList());
			Reducer red = new Reducer();
			HashMap<String,Integer> finalDict = red.reduce(suffledDict);
			sp.sortedPrint(finalDict,1);
		}
		
		//Execution of the MapReduce algorithm on the designated file
		if (isFileParameter(args)) {
			file = getFile(args);
			List<String> lines2 = readFile(file);
			Splitter sp1 = new Splitter();
			Map ma = new Map();
			Shuffler sh = new Shuffler();
			sp1.Split(lines2,2,threads,ma);			
			HashMap<String,List<Integer>> suffledDict = sh.shuffle(ma.getDictList());
			Reducer red = new Reducer();
			HashMap<String,Integer> finalDict = red.reduce(suffledDict);
			sp1.sortedPrint(finalDict,2);
		}
		
		//Executing the word counter for both files		
				
		long finishTime = System.nanoTime();
		
		System.out.print("The elapsed time : ");
		System.out.println((finishTime - startTime)/1e9 + " s");
	}
	
	//Reads a file, filters its content and returns it as an array
	private static List<String> readFile(File file) throws IOException {
		
		List<String> list = new ArrayList<String>();		
		try {
			
			BufferedReader reader =	new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				if(line.length() > 0 ) {
					line = line.toLowerCase().replaceFirst("l.l", "l l");
					line = line.replaceAll("[,|.|...|;|?|!]", "");					
					list.add(line);					
				}
			}
			reader.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("hi this is the list size : " + list.size());
		return list;
	}
	//Checks if the user wanted to use multithreading
	private static boolean isThread(String[] parameters) {		
		
		for (int i = 0; i < parameters.length; i++) {			
			if(parameters[i].equals("-t")) {
				parameters[i] = "";
				return true;
			}
		}		
		return false;		
	}
	private static boolean isFileParameter (String[] args) {
		
		String[] str;
		
		for (int i = 1; i <= args.length; i++) {
			if(args[i-1].length() > 0) {
				str= args[i-1].split("[.|/]");
				if(str[str.length-1].equals("txt")) {
					return true;
				}			
			}		
		} return false;
	}	
	//Gets the file name from the parameters passed by the user
	private static File getFile(String[] args) {
		
		String[] names = new String[args.length];
		String name;
		for (int i = 0; i <args.length; i++) {
			if(args[i].length() > 0) {
				names= args[i].split("[.|/]");
				if(names[names.length-1].equals("txt")) {
					args[i] = "";
					return new File(names[names.length-2] + "." + names[names.length-1]);
				}
			}
		}		
		throw new NullPointerException("File not found");
	}
}


