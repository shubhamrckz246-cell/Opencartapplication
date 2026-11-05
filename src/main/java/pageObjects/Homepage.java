package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Homepage extends Baseclass {
	
	public WebDriver driver;
	
	 public Homepage(WebDriver driver) {
	        
		 super(driver);
	      
	    }
	 
	 
	 @FindBy(xpath="//span[text()='My Account']")
	 WebElement Myaccountlnk;
	 
	 @FindBy(xpath="//a[text()='Register']")
	 WebElement registerlnk;
	
	 @FindBy(xpath="//a[text()='Login']")
	 WebElement Loginlnk;
	
	/*
	By Myaccountlnk=By.xpath("//span[text()='My Account']"); 
	By registerlnk=By.xpath("//a[text()='Register']");
	*/
	 
	
	 // Action methods
    public void Myaccountlnk() {
        Myaccountlnk.click();
    }

    public void registerlink() {
       registerlnk.click();
    }
	
	    	
     public void clickLoginlnk() {
    	 
    	 Loginlnk.click();
     }
     
     
     
     
	 
}

