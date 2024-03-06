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
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;

public class PieContrat extends JPanel{

    public PieContrat() throws ClassNotFoundException, SQLException {

//////////////////////
      DefaultPieDataset dataset = new DefaultPieDataset();
      Class.forName("org.postgresql.Driver");
      Connection connect = DriverManager.getConnection("jdbc:postgresql://localhost/scraping", "postgres", "admin");
      Statement statement = connect.createStatement( );
      ResultSet resultSet = statement.executeQuery("SELECT contrat ,count(contrat) as nombre FROM public.jsoup1 group by contrat" );
     
      
      while( resultSet.next( ) ) {
         dataset.setValue( 
         resultSet.getString( "contrat" ) ,
         Double.parseDouble( resultSet.getString( "nombre" )));
      }

       JFreeChart chart = ChartFactory.createPieChart3D("Repartition des Offres/Type de contrat", dataset, true, true, false);
       PiePlot3D plot = (PiePlot3D) chart.getPlot();
       
        plot.setForegroundAlpha(0.6f);
        
        plot.setLabelFont(new Font("MV Boli",Font.BOLD,13));
        chart.setTitle(new TextTitle("Repartition des Offres/Type de contrat",new Font("Serif", java.awt.Font.BOLD, 18)));
        plot.setCircular(true);  
        plot.setBackgroundPaint(Color.white);
        this.add(new ChartPanel(chart));
        this.setVisible(true);
        this.setBounds(5, 5, 500, 400);
        
/////////////////////////////
    }
}