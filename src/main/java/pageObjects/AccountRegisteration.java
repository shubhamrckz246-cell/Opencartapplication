package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegisteration extends Baseclass {
	
	public WebDriver driver;
	
	public AccountRegisteration(WebDriver driver) {
	        super(driver);
	    }
	 
	    @FindBy(xpath="//input[@id='input-firstname']")
	    WebElement txtFirstname;
	    
	    @FindBy(xpath="//input[@id='input-lastname']")
	    WebElement txtLastname;
	    @FindBy(xpath="//input[@id='input-email']")
	    WebElement txtEmail;
	    @FindBy(xpath="//input[@id='input-telephone']")
	    WebElement txtTelephone;		
	    @FindBy(xpath="//input[@name='password']")
	    WebElement txtPassword;
	    @FindBy(xpath="//input[@name='confirm']")
	    WebElement txtConfirm;
	    @FindBy(xpath="//input[@name='newsletter' and @value='1']")
	    WebElement yesRadio;
	    @FindBy(xpath="//input[@name='agree']")
	    WebElement agreechck;
	    @FindBy(xpath="//input[@value='Continue']")
	    WebElement btcontinue;
	    
	    @FindBy(xpath="//h1[text()='Your Account Has Been Created!']")
	    WebElement registermsg;
	    
	    
	    public void setFirstname (String firstname) {

	    	txtFirstname.sendKeys(firstname);

	    	}

	    	public void setlastname (String lastname) {

	    	txtLastname.sendKeys (lastname);

	    	}

	    	public void setEmail(String Email) {

	    	txtEmail.sendKeys(Email);

	    	}

	    	public void settelephone (String Phone) {

	    	txtTelephone.sendKeys (Phone);

	    	}
	    	
	    

	    	public void setpsw (String Password) {

	    	txtPassword.sendKeys (Password);

	    	}

	    	public void setconfirmpsw (String confirm) {

	    	txtConfirm.sendKeys(confirm);
	    	}

	    	public void clickcheckbox() {

	    	agreechck.click();

	    	}

	    	public void clickyes() {

	    	yesRadio.click();

	    	}
	    
	    	public void clickcontinue() {
	    		btcontinue.click();
	    	}
	    	
	    	public String confirmmessage() {
	    		try{

	    			String text=registermsg.getText();

	    			return text;

	    			}

	    			catch(Exception e) {

	    			return(e.getMessage());

	    			}

	    			}
	    	}
	


