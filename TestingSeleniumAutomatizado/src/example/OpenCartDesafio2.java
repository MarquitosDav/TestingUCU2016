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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

public class OpenCartDesafio2 {
	
    FirefoxDriver wd;
    private static  WebDriver driver;
	private static String baseUrl;
	static String[][] products; 

    
	
	public static String[][] testOpenCartProducts_FileCSV() throws Exception {
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


	
    @BeforeClass
    public static void setUp() throws Exception {

        FirefoxProfile fp = new FirefoxProfile();
		driver = new FirefoxDriver(fp);
		baseUrl = "http://demo.opencart.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        
        products = testOpenCartProducts_FileCSV();
        
    }
    

        
    @Test
    public void OpenCartJunitTestVerification() {
    	int i=0;
    
    	while(i<=products.length-1){
    		
		driver.get(baseUrl + "/");
		driver.findElement(By.name("search")).clear();
		driver.findElement(By.name("search")).sendKeys(products[i][0]);
		driver.findElement(By.xpath("//span[@class='input-group-btn']/button")).click();
	    driver.findElement(By.linkText(products[i][0])).click();
	    
		assertEquals(products[i][0], driver.findElement(By.cssSelector("h1")).getText());
		assertTrue(driver.findElement(By.tagName("html")).getText().contains(products[i][1]));
	    
	    i++;
    	}
    
    }

	
    @After
    public void tearDown() {
        driver.quit();
    }
    


  
    }
    

