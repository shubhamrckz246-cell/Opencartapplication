
package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.Login;
import pageObjects.Myaacountpage;

public class TC_003Login extends Basepage {

    @Test(groups = {"sanity", "regression", "master"})
    public void VerifyLogin() {

        logger.info("Starting login test");

        Homepage hp = new Homepage(driver);

        // Click My Account
        hp.Myaccountlnk();

        // Click Login
        hp.clickLoginlnk();

        // Login page
        Login lg = new Login(driver);

        lg.setemail(p.getProperty("username"));
        lg.setpassword(p.getProperty("password"));

        lg.clickbtLogin();

        // Verify My Account page
        Myaacountpage mc = new Myaacountpage(driver);

        boolean targetpage = mc.verifypage();

        Assert.assertTrue(targetpage,
                "Login failed - My Account page not displayed");

        logger.info("Login test passed");
    }
}
