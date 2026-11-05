
package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class Basepage {

    protected WebDriver driver;
    public Logger logger;
    public Properties p;

    // Browser launch
    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void browserlaunch(@Optional("chrome") String br) throws IOException {

        switch (br.toLowerCase()) {

        case "chrome":
            driver = new ChromeDriver();
            break;

        case "edge":
            driver = new EdgeDriver();
            break;

        case "firefox":
            driver = new FirefoxDriver();
            break;

        default:
            throw new IllegalArgumentException("Invalid browser: " + br);
        }

        // Initialize logger
        logger = LogManager.getLogger(this.getClass());

        // Load config.properties
        p = new Properties();

        String configPath = System.getProperty("user.dir")
                + "/src/main/resources/config.properties";

        try (FileReader fr = new FileReader(configPath)) {
            p.load(fr);
        }

        // Browser settings
        driver.manage().window().maximize();

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        // Open application
        driver.get(p.getProperty("appurl"));

        logger.info("Application launched successfully");
    }

    // Screenshot method for Extent Report
    public String captureScreen(String testName) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date());

        TakesScreenshot ts = (TakesScreenshot) driver;

        File source = ts.getScreenshotAs(OutputType.FILE);

        String targetPath = System.getProperty("user.dir")
                + "/screenshots/" + testName + "_" + timeStamp + ".png";

        File targetFile = new File(targetPath);

        targetFile.getParentFile().mkdirs();

        Files.copy(source.toPath(), targetFile.toPath());

        logger.info("Screenshot captured: " + targetPath);

        return targetPath;
    }

    // Close browser
    @AfterClass(alwaysRun = true)
    public void closeBrowser() {

        if (driver != null) {
            driver.quit();
            logger.info("Browser closed successfully");
        }
    }
}
