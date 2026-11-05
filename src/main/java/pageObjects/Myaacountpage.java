package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Myaacountpage extends Baseclass{
	public WebDriver driver;

	public  Myaacountpage(WebDriver driver){

	

	super(driver);
	
	}

	@FindBy(xpath="//h2[normalize-space()='My Account']")

	WebElement logingheader;

	@FindBy(xpath="//span[text()='My Account']")

	WebElement Myaccount;

	@FindBy(xpath="//a[text()='Logout']")

	WebElement Lnklogout;
	
	
    public void clickmyaccount(){
    	Myaccount.click();
    }
    	
    	public void clicklogout() {
    		Lnklogout.click();
    		
    		
    		
    	}
    	
    
	public boolean verifypage() {

	try {

	return (logingheader.isDisplayed());

	}

	catch(Exception e) {

	return false;

	}

}
}
