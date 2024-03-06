package com.mycompany.scrapp;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Scrapper {
    
    //information à scrapper:
    
    String titre,date_publication,url,metier,ville,nbr_postes,secteur_act,
	type_contrat,region,niveau_experience,nom_entreprise,niveau_etude,
	competences_requises,description_entreprise,description_post,
	profil_recherche,langue1,langue2,niveau_langue1,niveau_langue2;
	
    final String site ="emploi.ma",specialitediplome="NA",Trait_personnalité="NA",comprecom="NA",avtgsoc="NA"
                ,Softskils="NA",Salaire="NA",date_postuler="NA",teletravail = "NA",Adresse="NA";
	
	

    public void scrapper() throws FileNotFoundException, IOException{
        
        //liste por stocker les lien de chaque pages:
        
	List<WebElement> Lien_pas_pages;
	
        //les options des drivers :
        
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--headless");
	FirefoxOptions option1 = new FirefoxOptions();
	option1.addArguments("--headless");
	
        // declaration des drivers :
        
	System.setProperty("webdriver.chrome.driver","C:\\Users\\Mkh\\Desktop\\S7_2022\\JAVA\\Projet_JAVA\\selinium_web_drivers\\chromedriver.exe");
	WebDriver driver = new ChromeDriver(options);
	WebDriver driver2 = new ChromeDriver(options);

	System.setProperty("webdriver.gecko.driver","C:\\Users\\Mkh\\Desktop\\S7_2022\\JAVA\\Projet_JAVA\\selinium_web_drivers\\geckodriver.exe");
	WebDriver driver1 = new FirefoxDriver(option1);
	
        //scrapper le nbre de pages 
	driver2.get("https://www.emploi.ma/recherche-jobs-maroc");
        WebElement nbr_page = driver2.findElement(By.xpath("/html/body/div/div/section/div[2]/div[3]/div/div/div[2]/div/div[1]/table/tbody/tr/td[2]/div/div/ul/li[5]/a"));
        int nbrpage =Integer.valueOf(nbr_page.getText()); 			
	
        int num_offre = 1;
	
        //declaration de la feuille EXCEL :
        
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("offres");
        
        //double boucle de scrappe :
        int rowNum = 0;
	for(int i=1;i<= nbrpage ;i++) {
            driver2.get("https://www.emploi.ma/recherche-jobs-maroc?page="+i);
            Lien_pas_pages = driver2.findElements(By.xpath("//div[@class='job-description-wrapper']"));	
            
            for(int j =0;j<Lien_pas_pages.size();j++) {

                System.out.println("offre num :"+num_offre);
                driver.get(Lien_pas_pages.get(j).getAttribute("data-href"));


                //description entreprise
                try {
                    String lien1;
                    lien1 = driver.findElement(By.xpath("//div[@class='job-ad-company-description']/a")).getAttribute("href");
                    driver1.get(lien1);
                    description_entreprise = driver1.findElement(By.xpath("//div[@class='company-profile-description']")).getText();
                }
                catch(Exception e){
                    description_entreprise = "NA";
                }

                //1--titre de l'offre

                try {
                    titre = driver.findElement(By.xpath("//*[@id='main-content']/div[1]/div[1]/h1")).getText();
                }
                catch(Exception e){
                    titre = "NA";
                }

                //2--url de entreprise


                try {
                    url = driver.findElement(By.xpath("//td[@class='website-url']/a")).getAttribute("href");
                }
                catch(Exception e){
                    url = "NA";
                }


                //3--site scrappé : emploi.ma

                //4--date de publication


                try {
                    date_publication = driver.findElement(By.className("job-ad-publication-date")).getText();
                }

                catch(Exception e){
                    date_publication = "NA";
                }


                //5--date pour postuler : NA

                //6--addresse entreprise :NA

                //7--nom entreprise


                try {
                    nom_entreprise = driver.findElement(By.
                    xpath("//div[@class='company-title']/a")).getText();
                }
                catch(Exception e){
                    nom_entreprise = "NA";
                }

                //9--description du poste

                try {
                    description_post = driver.findElement(By.xpath("//span[@class='ad-ss-title']")).getText();
                }
                catch(Exception e){
                    description_post = "NA";
                }

                //10--Region 

                try {
                    region = driver.findElement(By.xpath("//div[@class='field field-name-field-offre-region field-type-taxonomy-term-reference field-label-hidden']")).getText();
                }
                catch(Exception e){
                    region = "NA";
                }

                //11--ville

                try {
                    ville = driver.findElement(By.xpath("//table[@class='job-ad-criteria']/tbody/tr[5]/td[2]")).getText();
                }
                catch(Exception e){
                    ville = "NA";
                }

                //12--secteur d'activité

                try {
                    secteur_act = driver.findElement(By.xpath("//div[@class='field-item even']")).getText();
                }
                catch(Exception e){
                    secteur_act = "NA";
                }

                //13--metier

                try {
                    metier = driver.findElement(By.xpath("//div[@class='field field-name-field-offre-metiers field-type-taxonomy-term-reference field-label-hidden']")).getText();
                }
                catch(Exception e){
                    metier = "NA";
                }


                //14--type de contrat


                try {
                    type_contrat = driver.findElement(By.xpath("//div[@class='field field-name-field-offre-contrat-type field-type-taxonomy-term-reference field-label-hidden']")).getText();
                }
                catch(Exception e){
                    type_contrat = "NA";
                }

                //15--niveau d'etude

                try {
                    niveau_etude = driver.findElement(By.xpath("//div[@class='field field-name-field-offre-niveau-etude field-type-taxonomy-term-reference field-label-hidden']")).getText();
                }
                catch(Exception e){
                    niveau_etude = "NA";
                }

                //16--Spécialité/diplome:NA
                //18--Expérience:

                try {
                    niveau_experience = driver.findElement(By.xpath("//div[@class='field field-name-field-offre-niveau-experience field-type-taxonomy-term-reference field-label-hidden']")).getText();
                }
                catch(Exception e){
                    niveau_experience = "NA";
                }

                //19--profil recherché


                try {
                    profil_recherche = driver.findElement(By.xpath("//div[@class='content clearfix']/div[3]")).getText();
                }
                catch(Exception e){
                    profil_recherche = "NA";
                }

                //20--trait de personnalité:NA

                //21--compétences requises

                try {
                    competences_requises = driver.findElement(By.
                    xpath("//div[@class='field field-name-field-offre-tags field-type-taxonomy-term-reference field-label-hidden']")).getText();
                    competences_requises = competences_requises.replace("\n", "/");
                }
                catch(Exception e){
                    competences_requises = "NA";

                }

                //22--soft skils:NA

                //23--Compétences recommandées:NA

                //24--Langue1

                try {
                    langue1 = driver.findElement(By.
                    xpath("//span[@class='lineage-item lineage-item-level-0']")).getText();
                }
                catch(Exception e) {
                    langue1 = "NA";
                }

                //25--Niveau de langue1

                try {
                    niveau_langue1 = driver.findElement(By.
                    xpath("//span[@class='lineage-item lineage-item-level-1']")).getText();
                }
                catch(Exception e) {
                    niveau_langue1 = "NA";
                }

                //26--langue 2:

                try {
                    langue2 = driver.findElement(By.
                              xpath("//div[@class='field-item odd']/span[1]")).getText();	
                }
                catch(Exception e) {
                    langue2 = "NA";
                }

                //--niveau de langue2:

                try {
                    niveau_langue2 = driver.findElement(By.
                    xpath("//div[@class='field-item odd']/span[3]")).getText();		
                        }
                catch(Exception e) {			
                    niveau_langue2 = "NA";
                        }		

                //28--Salaire:NA

                //29--Avantages sociaux:NA

                //30--télétravail:NA

                //31--nombre de poste

                try {
                    nbr_postes = driver.findElement(By.xpath("//table[@class='job-ad-criteria']/tbody/tr[10]/td[2]")).getText();
                }
                catch(Exception e) {
                    nbr_postes = "NA";
                }

                //replissage de la feuille EXCEL:
                
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(site);        
                row.createCell(1).setCellValue(site);
                row.createCell(2).setCellValue(titre);
                row.createCell(3).setCellValue(description_post);
                row.createCell(4).setCellValue(niveau_experience);
                row.createCell(5).setCellValue(region);
                row.createCell(6).setCellValue(nbr_postes);
                row.createCell(7).setCellValue(niveau_etude);
                row.createCell(8).setCellValue(competences_requises);
                row.createCell(9).setCellValue(type_contrat);
                row.createCell(10).setCellValue(teletravail);
                row.createCell(11).setCellValue(date_postuler);
                row.createCell(12).setCellValue(Adresse);
                row.createCell(13).setCellValue(url);
                row.createCell(14).setCellValue(langue1);
                row.createCell(15).setCellValue(niveau_langue1);
                row.createCell(16).setCellValue(Salaire);
                row.createCell(17).setCellValue(Softskils);
                row.createCell(18).setCellValue(comprecom);
                row.createCell(19).setCellValue(avtgsoc);
                row.createCell(20).setCellValue(url);
                row.createCell(21).setCellValue(description_entreprise);
                row.createCell(22).setCellValue(profil_recherche);
                row.createCell(23).setCellValue(date_publication);
                row.createCell(24).setCellValue(Trait_personnalité);
                row.createCell(25).setCellValue(metier);
                row.createCell(26).setCellValue(secteur_act);
                row.createCell(27).setCellValue(description_post);
                row.createCell(28).setCellValue(niveau_etude);
                row.createCell(29).setCellValue(specialitediplome);
                row.createCell(30).setCellValue(nom_entreprise);
                row.createCell(31).setCellValue(langue2);
                row.createCell(32).setCellValue(niveau_langue2);
                row.createCell(33).setCellValue(ville);


                num_offre++;

                    }

                }
        
            //resizing des colonnes
            for (int i = 0; i < 21; i++) {
                sheet.autoSizeColumn(i);
            }
            
            //ecriture sur la feuille:
            
            
            FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Mkh\\Desktop\\offres.xlsx");
            
            workbook.write(fileOut);
            
            fileOut.close();
            
            //fin du scrapp:
            num_offre -=1;
            System.out.println("fin de scrapping , nombre d'offres obtenue est : "+ num_offre);

		
}}
