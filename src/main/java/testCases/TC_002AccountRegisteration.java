
package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegisteration;
import pageObjects.Homepage;

public class TC_002AccountRegisteration extends Basepage {

    @Test(groups = {"sanity", "regression", "master"})
    public void Homepagetest() {

        logger.info("Starting Account Registration Test");

        Homepage hp = new Homepage(driver);

        // Click My Account
        hp.Myaccountlnk();

        // Click Register
        hp.registerlink();

        AccountRegisteration ar = new AccountRegisteration(driver);

        // Enter registration details
        ar.setFirstname("Shubham");
        ar.setlastname("Tripathi");

        // Generate unique email for each browser execution
        String email = "shubham" + System.currentTimeMillis() + "@gmail.com";
        ar.setEmail(email);

        ar.settelephone("9042569778");
        ar.setpsw("Shubham007");
        ar.setconfirmpsw("Shubham007");

        // Select newsletter and privacy checkbox
        ar.clickcheckbox();
        ar.clickyes();

        // Submit registration
        ar.clickcontinue();

        // Verify account registration
        String actualMessage = ar.confirmmessage();

        Assert.assertEquals(actualMessage, "Your Account Has Been Created!");
        

        logger.info("Account Registration Test Passed");
    }
}
