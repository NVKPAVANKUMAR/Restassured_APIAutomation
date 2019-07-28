package com.employeeapi.testCases;

import Utils.ConfigParser;
import com.employeeapi.base.TestBase;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_002_Get_Single_Employee_Record extends TestBase {
    @BeforeClass
    void getAllEmployees() {
        logger.info("********** Started TC002_Get_Single_Employee_Record *********");
        RestAssured.baseURI = ConfigParser.parser("reqres_api_baseurl");
        httpRequest = RestAssured.given();
        response = httpRequest.request("GET", "users/" + empID);
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
        Assert.assertTrue(responseTime < 10000);
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
        logger.info("************* Finished TC_002_Get_Single_Employee_Record ***************");
    }
}
