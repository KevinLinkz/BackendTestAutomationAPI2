package com.automationTest.report;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Locale;

public class ExtentTestFactory {
    private static volatile ExtentTestFactory instance =null;
    private ExtentTestFactory() {

    }

    public static ExtentTestFactory getInit() {
        if (instance == null){
            synchronized (ExtentTestFactory.class){
                if(instance == null){
                    instance = new ExtentTestFactory();
                }
            }
        }
        return instance;
    }

    ThreadLocal<ExtentTest> trdExtentTest = new ThreadLocal<>();

    public ExtentTest getExtentTest() {
        return trdExtentTest.get();
    }

    public void setExtentTest(ExtentTest extentTest) {
        trdExtentTest.set(extentTest);
    }

    public void removeExtentTest() {
        trdExtentTest.remove();
    }




    public void setStatusTest(String strStatusTest, String strLog, String strPathScreenShot) {
        Status stsLog = null;
        switch (strStatusTest.toUpperCase(Locale.ROOT)) {
            case "PASS":
                stsLog = Status.PASS;
                break;
            case "FAIL":
                stsLog = Status.FAIL;
                break;
            case "SKIP":
                stsLog = Status.SKIP;
                break;
            case "INFO":
                stsLog = Status.INFO;
                break;
        }

        if (strPathScreenShot != "") {
            String encodedString = changeImageToBase64(strPathScreenShot);
            getExtentTest().log(stsLog, strLog, MediaEntityBuilder.createScreenCaptureFromBase64String(encodedString).build());

        } else {
            getExtentTest().log(stsLog, strLog);
        }
    }

    //Change To Base64
    private static String changeImageToBase64(String strPathScreenShot) {
        String encodedString = "";
        try {
            byte[] fileContent = FileUtils.readFileToByteArray(new File(strPathScreenShot));
            encodedString = Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encodedString;
    }

//


}
