package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login extends Baseclass {
	public WebDriver driver;
	
	 public Login(WebDriver driver) {
	        
		 super(driver);

}
	 
	 
	 @FindBy(xpath="//input[@id='input-email']")
	 WebElement txtemaillnk;
	 
	 @FindBy(xpath="//input[@id='input-password']")
	 WebElement txtpasswordlnk;
	
	 @FindBy(xpath="//input[@value='Login']")
	 WebElement btLoginlnk;
	 
	 
	 public void setemail(String email) {
		 
		 txtemaillnk.sendKeys(email);
	 }
	 public void setpassword(String password) {
		 
		txtpasswordlnk.sendKeys(password);
	 }
	 
	 public void clickbtLogin() {
		 btLoginlnk.click();
	 }
	 
}