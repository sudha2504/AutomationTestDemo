package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import pages.BaseTest;

import java.io.IOException;


public class Listeners extends BaseTest implements ITestListener {
    ExtentReports extentReports = ExtentReporter.getExtentReport();
    ExtentTest extentTest;
    ThreadLocal<ExtentTest> test = new ThreadLocal<>();



    public void onTestStart(ITestResult result) {
        extentTest = extentReports.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed " + result.getMethod().getMethodName());
        test.get().log(Status.PASS, "Test Passed");

    }

    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed " + result.getMethod().getMethodName());
        test.get().log(Status.FAIL, "Test Failed");

        String filepath;
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
            filepath = takeScreenshot(result.getMethod().getMethodName(), driver);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        test.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());

    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped " + result.getMethod().getMethodName());
        test.get().log(Status.SKIP, "Test Skipped");
    }

    public void onStart(ITestContext context) {
        System.out.println("Test Started " + context.getName());
    }

    public void onFinish(ITestContext context) {
        extentReports.flush();
    }
    
}
