package Utils;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Listeners extends TestListenerAdapter {

    private ExtentHtmlReporter htmlReporter;
    private ExtentReports extentReports;
    public ExtentTest test;

    public void onStart(ITestContext testContext){
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/APIAutomationReport" + new SimpleDateFormat("dd-M-yyyy'T'hh-mm-ss a").format(new Date()) + ".html");
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        extentReports.setSystemInfo("Host Name", "localhost");
        extentReports.setSystemInfo("Environment", "QA");
        extentReports.setSystemInfo("user", "PAVAN");
    }

    public void onTestSuccess(ITestResult result){
        test = extentReports.createTest(result.getName());
        test.log(Status.PASS, "Test Case PASSED is " + result.getName());
    }

    public void onTestFailure(ITestResult result){
        test = extentReports.createTest(result.getName());
        test.log(Status.FAIL, "Test Case FAILED is " + result.getName());
        test.log(Status.FAIL, "Test Case FAILED is " + result.getThrowable());
    }

    public void onTestSkipped(ITestResult result){
        test = extentReports.createTest(result.getName());
        test.log(Status.SKIP, "Test Case SKIPPED is " + result.getName());
    }

    public void onFinish(ITestContext context){
        extentReports.flush();
    }
}
