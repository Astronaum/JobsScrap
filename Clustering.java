/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package uit.ensak.scraping;

import java.util.logging.Level;
import java.util.logging.Logger;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.Instances;
import weka.clusterers.SimpleKMeans;
/**
 *
 * @author Fighter
 */
public class Clustering {

    /**
     * @param args the command line arguments
     */
    public static void mainCluster() {
        try {
            // TODO code application logic here
            DataSource src = new DataSource("C:\\Users\\Fighter\\Desktop\\jsoup1_version.arff");
            Instances dt = src.getDataSet();
            SimpleKMeans model = new SimpleKMeans();
            model.setNumClusters(3);
            model.buildClusterer(dt);
            System.out.println(model);
        } catch (Exception ex) {
            Logger.getLogger(Clustering.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
