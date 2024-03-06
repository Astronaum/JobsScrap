package uit.ensak.scraping;
import java.awt.BasicStroke;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;

public class BarChart extends JPanel {


    public CategoryDataset createDataset() throws ClassNotFoundException, SQLException {

        var dataset = new DefaultCategoryDataset();
        
      Class.forName("org.postgresql.Driver");
      Connection connect = DriverManager.getConnection("jdbc:postgresql://localhost/scraping", "postgres", "admin");
      Statement statement = connect.createStatement( );
      ResultSet resultSet = statement.executeQuery("SELECT  count(nbr_post) as nombre, region FROM public.jsoup1 group by region order by nombre desc;" );
     
      
      while( resultSet.next( ) ) {
         dataset.setValue( 
         Double.parseDouble( resultSet.getString( "nombre" )), 
         "Nbr postes/region",
         resultSet.getString( "region" ) 
         );
      }
        
        
        return dataset;
    }
     
    public void barchart() throws ClassNotFoundException, SQLException{
      CategoryDataset dataset = this.createDataset();

        JFreeChart barChart = ChartFactory.createBarChart("Nombre de postes par region","","Nombre de postes",dataset,PlotOrientation.VERTICAL,
                true, true, false);
        
       barChart.setTitle(new TextTitle("Repartition geographique des postes",new Font("Serif", java.awt.Font.BOLD, 18)));
       ChartPanel chartpanel= new ChartPanel(barChart);
       

       // add values to bars
       CategoryPlot plot = (CategoryPlot) barChart.getPlot();
       plot.setBackgroundPaint(Color.white); 
         plot.getRenderer().setSeriesPaint(0, new Color(7, 43, 97));  
    
  plot.setDomainGridlinesVisible(false);
  
       
        
      // chartpanel.setSize(500,300);
       
        this.add(chartpanel);
       
        this.setVisible(true);
       this.setBounds(0, 0,1000 , 600);
        
        
       
    } 
      
       

    
}