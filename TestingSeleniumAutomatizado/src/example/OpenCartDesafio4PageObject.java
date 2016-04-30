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
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

public class OpenCartDesafio4PageObject {
	
	private static PageObjectPattern page;
	private StringBuffer verificationErrors = new StringBuffer();
	static String[][] allProducts; 
	
    @BeforeClass
    public static void setUp() throws Exception {
    	page = PageFactory.initElements(new FirefoxDriver(), PageObjectPattern.class);
    	page.open(); 
   
    
    	
        allProducts = testOpenCartAllProducts_FileCSV();
    }
    @After
    public void closeTheBrowser() {
    page.close();
    }
     
    
    @Test
    public void pageObjectPatternSearch() {
    	int i=0;
        
    	while(i<=allProducts.length-1){
    		page.clearSerach();
    		page.inputSearch(allProducts[i][0]);
    		page.sendSearch();
    		page.findLink(allProducts[i][0]);
    		assertEquals(allProducts[i][0], page.findProductName());
    		assertTrue(page.findProductPrice().contains(allProducts[i][1])); 
    		i++;
    	}
    }
    
    
	public static String[][] testOpenCartAllProducts_FileCSV() throws Exception {
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader("TestOpenCartAll_NombrePrecio.csv"));
		@SuppressWarnings("resource")
		BufferedReader br2 = new BufferedReader(new FileReader("TestOpenCartAll_NombrePrecio.csv"));
		String[][] lines = new String[((int) br2.lines().count()) - 1][2];
		String line;
		br.readLine();
		int contador = 0;
		while (contador<=lines.length-1) {
			line=br.readLine();
			String[] tokens = line.split(";");
			lines[contador][0] = tokens[0];
			lines[contador][1] = tokens[1];
			contador ++;
		}
		return lines;
	} 
}
    

