package project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Splitter {	
	
	final static int THREADS = 10;
	
	public void Split(List<String> list, int num, boolean thread, Map m) throws InterruptedException {		
		
		if(thread) {
			parallelRead(THREADS,list.size(),m,list);
		} else {
			 seqRead(list,m);
		}  
		/*
		Shuffler sh= new Shuffler();
		HashMap<String,List<Integer>> suffledDict = sh.shuffle(Map.getDictList());
		Reducer red = new Reducer();
		HashMap<String,Integer> finalDict = red.reduce(suffledDict);
		
		
		sortedPrint(finalDict,num);*/
	}
	//Creates the number of Threads for the multithreaded read and starts them
	private void parallelRead(final int threads, int listSize, Map m, List<String> list) throws InterruptedException {	
		
		long init_time;
		long finish_time;
		long jointTime = 0;
		List<Thread> thr = new ArrayList<Thread>();		
		for (int i = 1; i<=threads; i++) {
			//thr.add(new Thread(read(i,listSize, m,list),""+i));	
			thr.add(new Thread(read(i,listSize, m,list),""+i));			
			thr.get(i-1).start();
			init_time = System.nanoTime();
			thr.get(i-1).join();
			finish_time = System.nanoTime();			
			jointTime += (finish_time-init_time);
		}
		System.out.println(" The time spent joining is : " + jointTime/1e9);
	}
	
	private Runnable read(final int num,int listSize, Map m, List<String> list) {
		return new Runnable() {
			@Override
			public void run() {
				//Calculates the number of lines assigned to every thread
				//int work = listSize/THREADS;
				int work = listSize/THREADS;
				int specialWork = work;
				
				//Calculates the number of lines the last thread has to do
				if( num == THREADS ) {
					//specialWork = list.size() - (work * (THREADS - 1));
					specialWork = listSize - (work * (THREADS - 1));					
				}				
				List<String> li = list;		
				int x = (num - 1) * work;
				for(int i = (num-1) * work; i < (x + specialWork) ; i++) {										
					m.Mapp(li.get(i));
				}			
			}
		};
		
	}
	/*
	private List<String> workPerThread( List<String> list,) {
		
	}
	*/
	//Reads the words of a file without multithreading
	private void seqRead(List<String> list, Map m) {
		
		
		Iterator<String> it = (Iterator<String>) list.iterator();
		int i = 0;
		while (it.hasNext()) {
			String aux = it.next();				
			m.Mapp(aux);			
			//System.out.println(aux);			
			i++;
		}
		it.remove();
	}
	
	//Prints the words count into a file
	public void sortedPrint( HashMap<String,Integer> dictionary, int number) {
					
			try {
				
				PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("result" + number + ".txt")));
				out.println("result" + number + ".txt");
				List<String> listVal = new ArrayList<String>(dictionary.keySet());
				
				for (int i = 0; i < listVal.size(); i++) {				
					out.println(listVal.get(i) + " : " + dictionary.get(listVal.get(i)));
				}				
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
		}	
}
