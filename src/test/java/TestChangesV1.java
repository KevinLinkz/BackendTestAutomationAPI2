import com.automationTest.listener.TestListener;
import com.automationTest.service.AssertService;
import com.automationTest.service.JSONService;
import com.automationTest.service.LogsService;
import com.automationTest.utilities.DataProviderTesting;
import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;

@Listeners(TestListener.class)
public class TestChangesV1 {


    @Test(description = "GET - InquiryUserUser should be able to get detail quest",
            dataProvider = "ParamForInquiryUsers",
            dataProviderClass = DataProviderTesting.class)
    public void testExampleAPIGet(String strUrl, int intResponseCode, String strStatusLine, int intID, String strPathSchema,Map<String,Object> mapParam) {
        LogsService.getInit().appendLog(Status.INFO.toString(), strUrl,"");
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header("Content-Type", "application/json");
        Response response = requestSpecification.get(strUrl+"/" +intID);

        JSONObject requestResponse =  new JSONObject(response.getBody().asPrettyString());
        JSONObject requestExpectedResponse = new JSONObject(mapParam);

        String strResponseJSON = "Response Body Result : <br/>" + response.getBody().asPrettyString().replaceAll("\n","<br/>");
        LogsService.getInit().appendLog(Status.INFO.toString(), strResponseJSON,"");

        String strResponseJSONExpected = "Response Body Expected : <br/>" +JSONService.getInit().prettyJSON(requestExpectedResponse).replaceAll("\n","<br/>");
        LogsService.getInit().appendLog(Status.INFO.toString(), strResponseJSONExpected,"");

        AssertService.getInit().assertEquals(response.getStatusCode(),intResponseCode,"Response Code");
        AssertService.getInit().assertEquals(response.getStatusLine(),strStatusLine,"Status Line");
        AssertService.getInit().assertEquals(JSONService.getInit().prettyJSON(requestResponse),JSONService.getInit().prettyJSON(requestExpectedResponse),"Response Body");
        AssertService.getInit().assertSchemaJSON(response,strPathSchema);

    }

    @Test(description = "POST - Register must give token",
            dataProvider = "ParamForRegister",
            dataProviderClass = DataProviderTesting.class)
    public void testExampleAPIPost(String strUrl,int intResponseCode, String strStatusLine,String strPathSchema,Map<String,Object> mapRequestBody,Map<String,Object> mapExpectedBody){
        LogsService.getInit().appendLog(Status.INFO.toString(), strUrl,"");
        JSONObject requestParams = new JSONObject(mapRequestBody);
        JSONObject requestExpectedResponse = new JSONObject(mapExpectedBody);

        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header("Content-Type", "application/json");
        requestSpecification.body(JSONService.getInit().prettyJSON(requestParams));
        Response response = requestSpecification.when().post(strUrl);
        JSONObject requestResponse =  new JSONObject(response.getBody().asPrettyString());

        String strParamJSON = "Response Parameters : <br/>" + JSONService.getInit().prettyJSON(requestParams).replaceAll("\n","<br/>");
        LogsService.getInit().appendLog(Status.INFO.toString(), strParamJSON,"");

        String strResponseJSON = "Response Body Result : <br/>" + response.getBody().asPrettyString().replaceAll("\n","<br/>");
        LogsService.getInit().appendLog(Status.INFO.toString(), strResponseJSON,"");

        String strResponseJSONExpected = "Response Body Expected : <br/>" +JSONService.getInit().prettyJSON(requestExpectedResponse).replaceAll("\n","<br/>");
        LogsService.getInit().appendLog(Status.INFO.toString(), strResponseJSONExpected,"");

        AssertService.getInit().assertEquals(response.getStatusCode(),intResponseCode,"Response Code");
        AssertService.getInit().assertEquals(response.getStatusLine(),strStatusLine,"Status Line");
        AssertService.getInit().assertEquals(JSONService.getInit().prettyJSON(requestResponse),JSONService.getInit().prettyJSON(requestExpectedResponse),"Response Body");
        AssertService.getInit().assertSchemaJSON(response,strPathSchema);

    }







}
