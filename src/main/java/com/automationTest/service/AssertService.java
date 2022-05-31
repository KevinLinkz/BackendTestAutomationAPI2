package com.automationTest.service;

import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import org.testng.Assert;
import io.restassured.module.jsv.JsonSchemaValidator;

import java.net.URI;

public class AssertService {
    private static volatile AssertService instance = null;
    private AssertService() {

    }

    public static AssertService getInit() {
        if (instance == null) {
            synchronized (AssertService.class) {
                if (instance == null) {
                    instance = new AssertService();
                }
            }
        }
        return instance;
    }

    public void assertEquals(Object objResult,Object objExpected,String strDescription){
        try{
            Assert.assertEquals(objResult,objExpected);
            LogsService.getInit().appendLog(Status.PASS.toString(), "Compare "+strDescription+" is same Expected<br/>" + strDescription + " Expected:<br/>" + objExpected +"<br/> Result:<br/>" + objResult,"");
        }catch (AssertionError e){
            e.printStackTrace();
            LogsService.getInit().appendLog(Status.FAIL.toString(), "Compare "+ strDescription+" is not same Expected<br/>" + strDescription + " Expected:<br/>" + objExpected +"<br/> Result:<br/>" + objResult,"");
            throw new AssertionError("Compare result is not same");
        }
    }
    public void assertSchemaJSON(Object objResult,String strFileJsonSchema){
        try{
            URI uriPath = IOService.getInit().getURIFromResource(strFileJsonSchema);
            Response response = ((Response)objResult);

            response.then().body(JsonSchemaValidator.matchesJsonSchema(uriPath));
            LogsService.getInit().appendLog(Status.PASS.toString(), "Compare schema is same Expected<br/>Schema Expected<br/>","");
        }catch (AssertionError e){
            LogsService.getInit().appendLog(Status.FAIL.toString(), "Compare schema is not same Expected<br/>Schema Expected<br/>","");
            throw new AssertionError("Compare result is not same");
        }

    }
}
