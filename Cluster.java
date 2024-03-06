/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uit.ensak.scraping;


 
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
 
public class Cluster {
 
	public static BufferedReader readDataFile(String filename) {
		BufferedReader inputReader = null;
 
		try {
			inputReader = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException ex) {
			System.err.println("File not found: " + filename);
		}
 
		return inputReader;
	}
 
	public static void mainML() throws Exception {
            
		SimpleKMeans kmeans = new SimpleKMeans();
 
		kmeans.setSeed(10);
 
		//important parameter to set
		kmeans.setPreserveInstancesOrder(true);
		kmeans.setNumClusters(5);
 
		BufferedReader datafile = readDataFile("C:\\Users\\Fighter\\Desktop\\jsoup1_version.arff"); 
		Instances data = new Instances(datafile);
 
 
		kmeans.buildClusterer(data);
 
		
		int[] assignments = kmeans.getAssignments();
 
		int i=0;
		for(int clusterNum : assignments) {
		    System.out.printf("Instance %d -> Cluster %d \n", i, clusterNum);
		    i++;
		}
	}
}