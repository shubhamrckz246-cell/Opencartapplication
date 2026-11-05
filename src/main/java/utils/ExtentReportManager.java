
package utils;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.Basepage;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    String repName;

    @Override
    public void onStart(ITestContext testContext) {

        System.out.println("ExtentReportManager onStart() called");

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
                .format(new Date());

        repName = "Test-Report-" + timeStamp + ".html";

        // Create reports folder
        String reportFolderPath = System.getProperty("user.dir")
                + File.separator + "reports";

        File reportFolder = new File(reportFolderPath);

        if (!reportFolder.exists()) {
            reportFolder.mkdirs();
        }

        // Generate report path
        String reportPath = reportFolderPath
                + File.separator + repName;

        System.out.println("Report path: " + reportPath);

        sparkReporter = new ExtentSparkReporter(reportPath);

        sparkReporter.config().setDocumentTitle("OpenCart Automation Report");
        sparkReporter.config().setReportName("OpenCart Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Application", "OpenCart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customers");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");

        String os = testContext.getCurrentXmlTest().getParameter("os");
        if (os != null) {
            extent.setSystemInfo("Operating System", os);
        }

        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        if (browser != null) {
            extent.setSystemInfo("Browser", browser);
        }

        List<String> includedGroups =
                testContext.getCurrentXmlTest().getIncludedGroups();

        if (!includedGroups.isEmpty()) {
            extent.setSystemInfo("Groups", includedGroups.toString());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test = extent.createTest(
                result.getTestClass().getName() + " - " + result.getName());

        test.assignCategory(result.getMethod().getGroups());

        test.log(Status.PASS,
                result.getName() + " got successfully executed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test = extent.createTest(
                result.getTestClass().getName() + " - " + result.getName());

        test.assignCategory(result.getMethod().getGroups());

        test.log(Status.FAIL, result.getName() + " got failed");

        test.log(Status.INFO, String.valueOf(result.getThrowable()));

        try {
            Basepage base = (Basepage) result.getInstance();

            String imgPath = base.captureScreen(result.getName());

            test.addScreenCaptureFromPath(imgPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        test = extent.createTest(
                result.getTestClass().getName() + " - " + result.getName());

        test.assignCategory(result.getMethod().getGroups());

        test.log(Status.SKIP, result.getName() + " got skipped");

        test.log(Status.INFO, String.valueOf(result.getThrowable()));
    }

    @Override
    public void onFinish(ITestContext testContext) {

        System.out.println("ExtentReportManager onFinish() called");

        if (extent != null) {

            extent.flush();

            System.out.println("Extent report generated successfully");
        }

        String reportPath = System.getProperty("user.dir")
                + File.separator + "reports"
                + File.separator + repName;

        File extentReport = new File(reportPath);

        System.out.println("Report exists: " + extentReport.exists());
        System.out.println("Final report location: " + reportPath);

        try {
            if (Desktop.isDesktopSupported() && extentReport.exists()) {
                Desktop.getDesktop().browse(extentReport.toURI());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
