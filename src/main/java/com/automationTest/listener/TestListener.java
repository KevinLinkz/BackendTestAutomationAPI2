package com.automationTest.listener;

import com.automationTest.report.ExtentReportsSetup;
import com.automationTest.report.ExtentTestFactory;
import com.automationTest.service.IOService;
import com.automationTest.service.LogsService;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    static ExtentReports extentReports;
    ExtentTest extentTest;

    @Override
    public void onTestStart(ITestResult result) {
        extentTest = ExtentReportsSetup.createNewTest(result.getMethod().getDescription(),"Kevin",result.getInstanceName(),"API");
        ExtentTestFactory.getInit().setExtentTest(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogsService.getInit().appendLog(Status.PASS.toString(),"Pass  "+result.getMethod().getMethodName(),"");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogsService.getInit().appendLog(Status.FAIL.toString(),"Fail  "+result.getMethod().getMethodName(),"");

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogsService.getInit().appendLog(Status.SKIP.toString(),"Skip  "+result.getMethod().getMethodName(),"");

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
        IOService.getInit().initFolder();
        extentReports = ExtentReportsSetup.setupExtentReport("ApiAutomationTesting");
        extentTest =  ExtentTestFactory.getInit().getExtentTest();
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentTestFactory.getInit().removeExtentTest();
        extentReports.flush();
    }
}
