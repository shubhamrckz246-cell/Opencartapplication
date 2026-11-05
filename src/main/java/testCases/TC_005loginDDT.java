package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.Login;
import pageObjects.Myaacountpage;
import utils.Dataproviderclass;

public class TC_005loginDDT extends Basepage {
	
	

	@Test(
		    dataProvider = "LoginData",
		    dataProviderClass = Dataproviderclass.class,
		    groups = {"sanity", "regression", "master"}
		)
	


	void verify_LoginDDT(String email, String psw, String exp) {

	logger.info("***** Starting TC_005loginDDT ****");

	logger.debug("Test data -> email:" + email + ", expected:"  + exp);

	try {

	Homepage hp = new Homepage (driver);

	logger.info("Clicking 'My Account' link"); 
	
	hp.Myaccountlnk();

	logger.info("Clicking 'Login' link");

	hp.clickLoginlnk();

	 Login lg= new Login(driver); 
	
	lg.setemail(email);

	lg.setpassword(psw);

	lg.clickbtLogin();
	
	Myaacountpage mc = new Myaacountpage (driver);

	boolean targetpage = mc.verifypage();

	logger.info("My Account page displayed: " + targetpage);

	/*

	* Valid data + login success -> pass (logout)

	* Valid data + login failed -> fail

	+ login success -> fail (logout) * Invalid data

	* Invalid data login failed -> pass

	*/

	if (exp.equalsIgnoreCase ("Valid")) {

	if (targetpage) {

	logger.info("Valid credentials, login successful -> PASS");

	mc.clickmyaccount();
	mc.clicklogout();

	logger.info("Logged out");

	Assert.assertTrue(true);

	} else {

	logger.error("Valid credentials, but login failed > FAIL");

	Assert.fail("Valid credentials but login failed");

	}

	

	} else if (exp.equalsIgnoreCase("Invalid")) {

	if (targetpage) {

	logger.error("Invalid credentials, but login succeeded -> FAIL");

	mc.clickmyaccount();

	mc.clicklogout();

	logger.info("Logged out");

	Assert.fail("Invalid credentials but login succeeded");
	
	} else {

		logger.info("Invalid credentials, login rejected -> PASS");

		Assert.assertTrue(true);

		}

		} else {

		logger.warn("Unknown expected value in data:" + exp);

		}

		} catch (Exception e) {

		logger.error("Exception during test execution:" + e.getMessage(),e);
				
		Assert.fail("Exception during test: "+e.getMessage());

		} finally {

		logger.info("***** Finished TC_0051oginDDT *****");

		}

		}
	
}
