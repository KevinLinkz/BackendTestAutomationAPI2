package com.automationTest.report;

import com.automationTest.utilities.globaldata.MyConfig;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportsSetup {
    private static ExtentReports extentReports = new ExtentReports();

    public static ExtentReports setupExtentReport(String strFileNameReport){
        ExtentSparkReporter spark = new ExtentSparkReporter(MyConfig.strPathFolderResultTesting + "\\" + strFileNameReport + ".html");
        extentReports.attachReporter(spark);

        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle(strFileNameReport);
        spark.config().setReportName(strFileNameReport);

        extentReports.setSystemInfo("Execute By ","Kevin Hertanto");
        extentReports.setSystemInfo("Execute on OS","API Platform");


        return  extentReports;

    }
    //Create New Test
    public static ExtentTest createNewTest(String strTestName, String strAuthor, String strCategory, String strDevice) {
        return extentReports.createTest(strTestName)
                .assignAuthor(strAuthor)
                .assignCategory(strCategory)
                .assignDevice(strDevice);

    }

}



