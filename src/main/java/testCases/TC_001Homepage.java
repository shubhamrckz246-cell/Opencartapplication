package testCases;


import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import pageObjects.Homepage;

public class TC_001Homepage extends Basepage {

    

    @Test(groups = {"sanity", "regression", "master"})
    public void Hoempagetest() {

       
        logger.info("***Test Execution Started***"); 
        Homepage hp = new Homepage(driver);
         
        // Click My Account
        hp.Myaccountlnk();
        logger.warn("Give prover value");
        logger.info("***Account Clicked***");

        // Click Register
        hp.registerlink();
        logger.info("***Register Cliced***");
        logger.debug("Clicked"+ driver.getCurrentUrl());

     
    }
}

