package example;

import org.junit.*;
import org.openqa.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
public class PageObjectPattern {
	protected WebDriver driver;
	private WebElement q;
	private WebElement btn;
	
	public PageObjectPattern(WebDriver driver) {
		this.driver = driver;
		}
		public void open() {
	 
		driver.get("http://demo.opencart.com");
		}
		public void close() {
		driver.quit();
		}
		public String getTitle() {
		return driver.getTitle();
		}
		
		public void clearSerach(){
			driver.findElement(By.name("search")).clear();
		}
		
		public void inputSearch(String product){
			driver.findElement(By.name("search")).sendKeys(product);
		}
		
		public void sendSearch(){
		driver.findElement(By.xpath("//span[@class='input-group-btn']/button")).click();
		}
		
		public void findLink(String product){
		driver.findElement(By.linkText(product)).click();
		}
		
		public String findProductName(){
			 return driver.findElement(By.cssSelector("h1")).getText();
		}
		
		public String findProductPrice(){
			 return driver.findElement(By.tagName("html")).getText();
		}
		
	
}
	

