package com.employeeapi.testCases;

import Utils.RestUtils;
import com.employeeapi.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_004_Put_Employee_Record extends TestBase {

    RequestSpecification httpRequest;
    Response response;

    @BeforeClass
    void updateEmployee() {
        logger.info("************ Started TC004_PUT_Employee_Record *************");
        RestAssured.baseURI = "https://reqres.in";
        httpRequest = RestAssured.given();

        JSONObject reqParams = new JSONObject();
        reqParams.put("name", RestUtils.getName());
        reqParams.put("job", RestUtils.getJob());

        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(reqParams.toJSONString());

        response = httpRequest.request("PUT", "/api/users/" + empID);
    }

    @Test
    public void test_checkResponseBody() {
        String responseBody = checkResponseBody(response);
        logger.info("Response Body ==>" + responseBody);
        Assert.assertTrue(responseBody.contains(empID));
    }

    @Test
    public void test_checkStatusCode() {
        int statusCode = checkStatusCode(response);
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void test_checkResponseTime() {
        long responseTime = checkResponseTime(response);
        logger.info("Response Time is  ==> " + responseTime);
        if (responseTime > 2000) {
            logger.warn("Response Time is greater than 2000ms");
        }
        Assert.assertTrue(responseTime < 15000);
    }

    @Test
    void test_checkStatusLine() {
        String statusLine = checkStatusLine(response);
        logger.info("Status Line is ==> " + statusLine);
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
    }

    @Test
    void test_ContentType() {
        String contentType = checkContentType(response);
        logger.info("Content Type is ==> " + contentType);
        Assert.assertEquals(contentType, "application/json; charset=utf-8");
    }

    @Test
    void test_ServerType() {
        String serverType = checkServerType(response);
        logger.info("Server Type is ==> " + serverType);
        Assert.assertEquals(serverType, "cloudflare");
    }

    @Test
    void test_ContentEncoding() {
        String contentEncoding = checkContentEncoding(response);
        logger.info("Content Encoding is ==> " + contentEncoding);
        Assert.assertEquals(contentEncoding, "gzip");
    }

    @AfterClass
    void tearDown() {
        logger.info("************* Finished TC_004_Update_Single_Employee_Record ***************");
    }
}
