package uit.ensak.scraping;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class PieNivEtud extends JPanel{

    public PieNivEtud() throws ClassNotFoundException, SQLException {

//////////////////////
      DefaultPieDataset dataset = new DefaultPieDataset();
      Class.forName("org.postgresql.Driver");
      Connection connect = DriverManager.getConnection("jdbc:postgresql://localhost/scraping", "postgres", "admin");
      Statement statement = connect.createStatement( );
      //ResultSet resultSet = statement.executeQuery("SELECT  count(nbr_post) as nombre,substr(niv_etud,1,6)as niv FROM public.jsoup1 group by substr(niv_etud,1,6)" );
     ResultSet resultSet = statement.executeQuery("SELECT  count(nbr_post) as nombre,trim(niveauetude)as niv FROM public.jsoup1 group by trim(niveauetude)" );
      
      while( resultSet.next( ) ) {
         dataset.setValue( 
                 
         resultSet.getString( "niv" ) ,
         Double.parseDouble( resultSet.getString( "nombre" )));
      }

      // JFreeChart chart = ChartFactory.createBarChart("Repartition des Offres/Type de niveau d'edude", "X", "Y", (CategoryDataset) dataset, PlotOrientation.HORIZONTAL, true, true, true);
               
       JFreeChart chart = ChartFactory.createPieChart3D("Repartition des Offres/Niveau d'edude", dataset, true, true, false);
       
       
       PiePlot3D plot = (PiePlot3D) chart.getPlot();
       
        plot.setForegroundAlpha(0.6f);
        
        plot.setLabelFont(new Font("MV Boli",Font.BOLD,13));
        plot.setCircular(true);
       plot.setBackgroundPaint(Color.white);
       chart.setTitle(new TextTitle("Repartition des Offres/Niveau d'edude",new Font("Serif", java.awt.Font.BOLD, 18)));
        this.add(new ChartPanel(chart));
        this.setVisible(true);
        this.setBounds(5, 5, 500, 400);
        
/////////////////////////////
    }
}