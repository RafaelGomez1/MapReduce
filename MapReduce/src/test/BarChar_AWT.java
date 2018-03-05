package test;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChar_AWT {
	
	
	private void createDataset() {
		
		
		
			
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stubarg1
		
		File file1 = new File("result.txt");
		HashMap<String,Integer> dict1 = TestMain.read(file1);
		Set<String> dictKeys = dict1.keySet();
		Iterator<String> it = dictKeys.iterator();
		
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		while (it.hasNext()) {
			String st = it.next();
			dataset.addValue(dict1.get(it), "Words", "Number of times used");			
		}		
		
		JFreeChart barChart = ChartFactory.createBarChart(charTitle, "Map Reduce Word Counter", "Most common words",
				dataset, PlotOrientation.VERTICAL, true, true, false);
		
		int width = 640;
		int height = 480;
		File BarChart = new File("BarChart.jpeg");
		ChartUtilities.saveChartAsJPEG(BarChart, barChart, width, height);
	}

}
