package testing;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChar_AWT {
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stubarg1
		
		File file1 = new File("result1.txt");
		HashMap<String,Integer> dict1 = TestMain.read(file1);
		Set<String> dictKeys = dict1.keySet();
		Iterator<String> it = dictKeys.iterator();
		
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		//Uncomment this segment if you want to create a report with every word
		/*
		while (it.hasNext()) {
			String st = it.next();
			System.out.println(st); 
			System.out.println(dict1.get(st));			
			
			dataset.addValue(dict1.get(st), st, "Number of times used");			
		}*/
		
		while (it.hasNext()) {
			String st = it.next();
			if( st.length() > 2 && dict1.get(st) > 1) {
				System.out.println(st); 
				System.out.println(dict1.get(st));
				dataset.addValue(dict1.get(st), st, "");
			}
		}
		
		JFreeChart barChart = ChartFactory.createBarChart("Map Reduce Wourd Counter", "Words", "Number of times used",
				dataset, PlotOrientation.VERTICAL, true, true, false);
		
		int width = 640;
		int height = 480;
		File BarChart = new File("BarChart.jpeg");
		ChartUtilities.saveChartAsJPEG(BarChart, barChart, width, height);
	}

}
