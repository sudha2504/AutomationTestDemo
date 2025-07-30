package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReporter {


    public static ExtentReports getExtentReport()
    {
        String Reportpath = System.getProperty("user.dir") + "/reports";
        ExtentSparkReporter spark = new ExtentSparkReporter(Reportpath);
        spark.config().setReportName("Test Results");
        spark.config().setDocumentTitle("Automation Results");

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(spark);
        return extentReports;

    }
}
