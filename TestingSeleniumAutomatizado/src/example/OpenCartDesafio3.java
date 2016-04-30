package example;


import static org.junit.Assert.*;
import static org.testng.AssertJUnit.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

public class OpenCartDesafio3 {
	
    FirefoxDriver wd;
    private static  WebDriver driver;
	private static String baseUrl;

	

	public static void writeFileCSV(StringBuilder striBuilder) {
		try {
			FileWriter fw = new FileWriter("TestOpenCartAll_NombrePrecio.csv");
			fw.append("nombre,precio");
			fw.append("\n");
			fw.append(striBuilder.toString());
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    @BeforeClass
    public static void setUp() throws Exception {

        FirefoxProfile fp = new FirefoxProfile();
		driver = new FirefoxDriver(fp);
		baseUrl = "http://demo.opencart.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		GetAllProducts();  
    }
    
    public static  void GetAllProducts() {
        driver.get(baseUrl + "/");
        StringBuilder str = new StringBuilder();
        driver.get("http://demo.opencart.com/index.php?route=product/search&search=&limit=25");
        int i=1;
        while(i<21){
            if(i==5){
                i=6;
            }
            try {
                String prod_name = String.format("//div[@id='content']/div[4]/div[%1$d]/div/div[2]/h4/a",i);                              
                String prod_price = String.format("//*[@id='content']/div[4]/div[%1$d]/div/div[2]/p[2]",i);        
                String name = driver.findElement(By.xpath(prod_name)).getText();
                String price = driver.findElement(By.xpath(prod_price)).getText();
               
               // if(price.contains("$")){
                String[] prices= price.split("\n");
               if(prices.length==2){
            	   String[] pricesFinal = prices[0].split(" ");
            	   price=pricesFinal[0];
               }else{
            	    price=prices[0];
               }
                str.append(name+";"+price+"\n");
            } catch (NoSuchElementException ex) {                
            } 
            i++;
        }
        writeFileCSV(str); 
        
    }
    
  @Test
  public void OpenCartJunitTestVerification() {
  
  }
 
   
    
    @After
    public void tearDown() {
        driver.quit();
    }

    }
    

