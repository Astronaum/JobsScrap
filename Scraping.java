
package uit.ensak.scraping;

import java.io.IOException;
import java.sql.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 *
 * @author Fighter
 */
public class Scraping {

     public void vmain() throws IOException, ClassNotFoundException, SQLException {
               
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
         //Site 2 : Rekrute
         
      //étape 1: charger la classe driver
      Class.forName("org.postgresql.Driver");
      
      //étape 2: créer l'objet de connexion
      Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/scraping", "postgres", "admin");
       
    PreparedStatement pst = null;
      //étape 3: créer l'objet statement 
      Statement stmt = conn.createStatement();
         int i=1;
         for(int j=1;j<=37;j++){
         System.out.println("--------------- PAGE : "+j+" --------------------------------------------------------------------------------------------------------------------");
        Document doc1 = Jsoup.connect("https://www.rekrute.com/offres.html?s=3&p="+j+"&o=1").get();
//        Document doc1 = Jsoup.connect("https:/www.rekrute.com/offres.html?s=1&p="+j+"&o=1").get();
       
       String site=doc1.title();
       System.out.println("Nom du site :  "+ site);
         
        Elements offreHeadlines = doc1.select(".job-list .post-id .section h2 a ");
        Elements datesPub =doc1.select(".job-list .post-id .section div div em  ");
        for (Element offre : offreHeadlines) {
         
           String SiteWebEntrepris="NA"; //NA dans ce site 
           String langue ="NA";
           String NiveauLangue="NA";
           String salaire="NA";//NA dans ce site;         
           
           String softSkills="NA";
           String CompetencesRecommandees="NA";
           String AvantagesSociaux="NA";
           
           //System.out.println(doc1.select(".job-list .post-id .section div ul a").first().text());
           //System.out.println(doc1.getElementsContainingText("Secteur d'activité").first().text());
           
            
            System.out.println("**********************************************************************");
            System.out.println("**********************************************************************");
            System.out.println(" Offre numero : "+ i);
             i++;
             
            String intitulPost = offre.text();
           // System.out.println(" Intitule de poste --> "+ intitulPost);
           
            String url = offre.absUrl("href"); // A inserer
            System.out.println("URL d'annonce : "+url);
            
            Document sousdoc = Jsoup.connect(offre.absUrl("href")).get();
       
            System.out.println(" Detail intitule ----------------------->"+sousdoc.title());
            System.out.println(" Secteur ------------------------------->"+sousdoc.getElementsByClass("h2italic").text()); 
            System.out.println(" Experience requise -------------------->"+sousdoc.getElementsByAttributeValue("title","Expérience requise").text());
            System.out.println(" Region -------------------------------->"+sousdoc.getElementsByAttributeValue("title","Région").text());
            System.out.println(" Nobre de postes ----------------------->"+sousdoc.getElementsByTag("b" ).first().text());
            System.out.println(" Niveau d'étude et formation ----------->"+sousdoc.getElementsByAttributeValue("title","Niveau d'étude et formation").first().text());
            System.out.println(" Competence ---------------------------->"+sousdoc.getElementsByClass("tagSkills").text()); 
            System.out.println(" Type de contrat ----------------------->"+sousdoc.getElementsByAttributeValue("title","Type de contrat").first().text());
            System.out.println(" Télétravail --------------------------->"+sousdoc.getElementsByAttributeValue("title","Télétravail").text());
            System.out.println(" Date limite pour postuler ------------->"+sousdoc.select(" span > b").text());
            System.out.println(" addresse ------------------------------>"+sousdoc.select("span#address").text());

            String DescriptionEntreprise=sousdoc.getElementById("recruiterDescription").text();  // A inserer
            System.out.println(" Detail emtreprise ----------------------->"+DescriptionEntreprise);
            
           String profil;
            
            try{
            profil=sousdoc.select("div h2:contains(Profil recherché :) ").parents().first().text(); // A inserer
            System.out.println(" Profil recherche ----------------------->"+profil);
            }catch (Exception e) {
             profil ="NA";   
            }
            
            String Datepublication;
            
            try{
            Datepublication=sousdoc.select("span:contains(Publiée) ").text(); // A inserer
            int index4=Datepublication.indexOf("Publiée");
            int index5=Datepublication.indexOf("sur ReKrute.com");
            Datepublication=Datepublication.substring(index4+8,index5-1);
            System.out.println(" Date de publication ----------------------->"+Datepublication);
            }catch (Exception e) {
             Datepublication ="NA";   
            }
            
            String traits;
            try {
                           
            traits=sousdoc.select("div h2:contains(Traits de personnalité souhaités :) ").parents().first().text(); // A inserer
            System.out.println(" Traits de personnalite ----------------------->"+traits);
            } catch (Exception e) {
             traits ="NA";   
            }
            
            String metier=sousdoc.getElementsByClass("h2italic").text();
            int index=metier.indexOf("Secteur");
            metier=metier.substring(index); //A inserer
            System.out.println("metier ----------------------->"+metier);
            
            
            String secteurActivite=sousdoc.getElementsByClass("h2italic").text().substring(0,index-2);//A inserer
            System.out.println("Secteur ----------------------->"+secteurActivite);
            
            String DescriptionPoste;
            try{
            DescriptionPoste=sousdoc.select("div h2:contains(Poste :) ").parents().first().text(); // A inserer
            System.out.println("Description poste ----------------------->"+DescriptionPoste);}catch (Exception e) {
             DescriptionPoste ="NA";   
            }
            
            
            
            String NiveauEtude=sousdoc.getElementsByAttributeValue("title","Niveau d'étude et formation").first().text();
            int index1=0;
            try{
            index1=NiveauEtude.indexOf('-');
            NiveauEtude=NiveauEtude.substring(0, index1);
            
            }catch (Exception e) {
               
            }
            System.out.println("Niveau d'etude ----------------------->"+NiveauEtude); // A inserer
            
           
            String SpecialiteDiplome=sousdoc.getElementsByAttributeValue("title","Niveau d'étude et formation").first().text();
            
            try{
            SpecialiteDiplome=SpecialiteDiplome.substring(index1+1);
            
            }catch(Exception e) {
               SpecialiteDiplome="NA";
            }
           System.out.println("Specialite du diplome ----------------------->"+SpecialiteDiplome); 
            
           String nomEntreprise; // A inserer
            try {
                
            
            nomEntreprise =sousdoc.getElementsByClass("foruloffemp col-md-12 blc").text();
            int index2=nomEntreprise.indexOf('«');
            int index3=nomEntreprise.indexOf('»');
            nomEntreprise=nomEntreprise.substring(index2+1,index3); //A inserer
            
            } catch (Exception e) {
                nomEntreprise="NA";
            }
           
           System.out.println("nomEntreprise ----------------------->"+nomEntreprise);
           
           
           
            String dintitulePost=sousdoc.title();
            //System.out.println(" Detail intitule ----------------------->"+dintitulePost);
            
            String secteur = sousdoc.getElementsByClass("h2italic").text(); //A remplacer
            //System.out.println(" Secteur ------------------------------->"+secteur)
            
            String expReq = sousdoc.getElementsByAttributeValue("title","Expérience requise").text();
           // System.out.println(" Experience requise -------------------->"+expReq);
            
            String region=sousdoc.getElementsByAttributeValue("title","Région").text();
           // System.out.println(" Region -------------------------------->"+region);
            
            String nbrPost=sousdoc.getElementsByTag("b" ).first().text();
           // System.out.println(" Nobre de postes ----------------------->"+nbrPost);
            
            String nivForm=sousdoc.getElementsByAttributeValue("title","Niveau d'étude et formation").first().text();
           // System.out.println(" Niveau d'étude et formation ----------->"+nivForm);
            
            String comptence=sousdoc.getElementsByClass("tagSkills").text();
           // System.out.println(" Competence ---------------------------->"+comptence); 
            
            String typCntrat = sousdoc.getElementsByAttributeValue("title","Type de contrat").first().text();
           // System.out.println(" Type de contrat ----------------------->"+typCntrat);
            
            String teletravail=sousdoc.getElementsByAttributeValue("title","Télétravail").text();
           // System.out.println(" Télétravail --------------------------->"+teletravail);
            
            String datLimt=sousdoc.select(" span > b").text();
           // System.out.println(" Date limite pour postuler ------------->"+datLimt);
            
            String adr =sousdoc.select("span#address").text();
           // System.out.println(" addresse ------------------------------>"+adr);
       
         //System.out.println(" detail de : "+ doffreHeadlines.text());
            
           //Insertion dans la base de donnees
   
           //étape 4: exécuter la requête
     // System.out.println("Insertion...");
    
      //String sql = "INSERT INTO jsoup (site, intitulpost, dete_intit, secteur, exp_req, region, nbr_post, niv_etud, competence, contrat, teletravail, date_limit, addresse) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
     // stmt.executeUpdate("INSERT INTO jsoup (site, intitulpost, dete_intit, secteur, exp_req, region, nbr_post, niv_etud, competence, contrat, teletravail, date_limit, addresse) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
     //String stm = "INSERT INTO jsoup (site, intitulpost, dete_intit, secteur, exp_req, region, nbr_post, niv_etud, competence, contrat, teletravail, date_limit, addresse) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
     String stm = "INSERT INTO public.jsoup1(site, intitulpost, dete_intit, exp_req, region, nbr_post, niv_etud, competence, contrat, teletravail, date_limit, addresse, sitewebentr, langue, nivlang, salaire, softskills, comprecom, avtgsoc, urlannoance, descriptionentreprise, profil, datepublication, traits, metier, secteuractivite, descriptionposte, niveauetude, specialitediplome, nomentreprise)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
     pst = conn.prepareStatement(stm);
        pst.setString(1, site); //site 
        pst.setString(2, intitulPost);//intitulpost 
        pst.setString(3, dintitulePost);//dete_intit
        //pst.setString(4, secteur);
        pst.setString(4, expReq);//exp_req
        pst.setString(5, region);//region
        pst.setString(6,nbrPost );//nbr_post
        pst.setString(7, nivForm);//niv_etud
        pst.setString(8,comptence);//competence
        pst.setString(9, typCntrat);//contrat
        pst.setString(10, teletravail);//teletravail
        pst.setString(11, datLimt);//date_limit
        pst.setString(12, adr);//addresse
        pst.setString(13, SiteWebEntrepris);
        pst.setString(14, langue);
        pst.setString(15, NiveauLangue);
        pst.setString(16, salaire);
        pst.setString(17, comptence);
        pst.setString(18, comptence);
        pst.setString(19, AvantagesSociaux);
        pst.setString(20, url);
        pst.setString(21, DescriptionEntreprise);
        pst.setString(22, profil);
        pst.setString(23, Datepublication);
        pst.setString(24, traits);
        pst.setString(25, metier);
        pst.setString(26, secteurActivite);
        pst.setString(27, DescriptionPoste);
        pst.setString(28, NiveauEtude);
        pst.setString(29, SpecialiteDiplome);
        pst.setString(30, nomEntreprise);
        
        
        
       
        
        pst.executeUpdate();

       
      
    
      System.out.println("Données insérés dans la table...");
      //étape 5: fermez l'objet de connexion
     
            
             
            
        }  
     }
         
         conn.close();
         
    }

}
