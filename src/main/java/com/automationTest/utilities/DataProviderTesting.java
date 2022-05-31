package com.automationTest.utilities;

import org.testng.annotations.DataProvider;

import java.util.HashMap;
import java.util.Map;

public class DataProviderTesting {
    //I can use microsoft excel(use poi library) for data provider to get data and change it to Object[][]
    //But if you want high performance, better not use it. But it's a choice
    //If use Microsoft Excel, we can maintain easily
    //There is always a cost for every choose.

    @DataProvider(name = "ParamForTest1",parallel = true)
    public Object[][] objApiPostTest1(){
        long unixTime = System.currentTimeMillis() / 1000L;

        Map<String,Object> mapRequestBody = new HashMap<>();
        mapRequestBody.put("order_id","xxxxxx");
        mapRequestBody.put("order_description","description");
        mapRequestBody.put("last_updated_timestamp",unixTime);
        mapRequestBody.put("special_order","FALSE");

        Map<String,Object> mapExpectedBody = new HashMap<>();
        mapExpectedBody.put("error","");



        return new Object[][]{
                {"https://testPedia.com/v2/procesOrder",200,"HTTP/1.1 200 OK","JSONSchema/LoginSuccessSchema.json",mapRequestBody,mapExpectedBody}
        };
    }


    @DataProvider(name = "ParamForInquiryUsers",parallel = true)
    public Object[][] objApiGetSpecificDataUsers(){
        Map<String,Object> mapExpectedBody = new HashMap<>();
        mapExpectedBody.put("id",1591);
        mapExpectedBody.put("name","Deependra Iyengar");
        mapExpectedBody.put("email","iyengar_deependra@hintz.org");
        mapExpectedBody.put("status","inactive");
        mapExpectedBody.put("gender","male");

        return new Object[][]{
                {"https://gorest.co.in/public/v2/users",200,"HTTP/1.1 200 OK",1591, "JSONSchema/InquiryUsersSchema.json",mapExpectedBody},
                {"https://gorest.co.in/public/v2/users",333,"HTTP/1.1 200 OK",1592, "JSONSchema/InquiryUsersSchema.json",mapExpectedBody}

        };
    }

    @DataProvider(name = "ParamForRegister",parallel = true)
    public Object[][] objApiPostRegister(){

        Map<String,Object> mapRequestBody = new HashMap<>();
        mapRequestBody.put("email","eve.holt@reqres.in");
        mapRequestBody.put("password","pistol");

        Map<String,Object> mapExpectedBody = new HashMap<>();
        mapExpectedBody.put("id",4);
        mapExpectedBody.put("token","QpwL5tke4Pnpja7X4");


        Map<String,Object> mapRequestBody2 = new HashMap<>();
        mapRequestBody2.put("email","xxxx");
        mapRequestBody2.put("password","pistols");

        Map<String,Object> mapExpectedBody2 = new HashMap<>();
        mapExpectedBody2.put("error","Note: Only defined users succeed registration");


        return new Object[][]{
                {"https://reqres.in/api/register",200,"HTTP/1.1 200 OK","JSONSchema/RegisterSuccessSchema.json",mapRequestBody,mapExpectedBody},
                {"https://reqres.in/api/register",400,"HTTP/1.1 400 Bad Request","JSONSchema/ResgisterUnsuccessSchema.json",mapRequestBody2,mapExpectedBody2}
        };
    }

    @DataProvider(name = "ParamForLogin",parallel = true)
    public Object[][] objApiPostLogin(){

        Map<String,Object> mapRequestBody = new HashMap<>();
        mapRequestBody.put("email","eve.holt@reqres.in");
        mapRequestBody.put("password","cityslicka");

        Map<String,Object> mapExpectedBody = new HashMap<>();
        mapExpectedBody.put("token","QpwL5tke4Pnpja7X4");


        Map<String,Object> mapRequestBody2 = new HashMap<>();
        mapRequestBody2.put("email","");
        mapRequestBody2.put("password","");

        Map<String,Object> mapExpectedBody2 = new HashMap<>();
        mapExpectedBody2.put("error","Missing email or username");


        return new Object[][]{
                {"https://reqres.in/api/login",200,"HTTP/1.1 200 OK","JSONSchema/LoginSuccessSchema.json",mapRequestBody,mapExpectedBody},
                {"https://reqres.in/api/login",400,"HTTP/1.1 400 Bad Request","JSONSchema/LoginUnsuccessSchema.json",mapRequestBody2,mapExpectedBody2}
        };
    }

}
