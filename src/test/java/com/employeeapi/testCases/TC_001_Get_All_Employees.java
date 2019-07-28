package com.employeeapi.testCases;

import Utils.ConfigParser;
import com.employeeapi.base.TestBase;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_001_Get_All_Employees extends TestBase {

    @BeforeClass
    void getAllEmployees() {
        logger.info("********** Started TC001_Get_All_Employees *********");
        RestAssured.baseURI = ConfigParser.parser("employee_api_baseurl");
        httpRequest = RestAssured.given();
        response = httpRequest.request("GET", "employees");
    }

    @Test
    public void test_ResponseBody() {
        String responseBody = checkResponseBody(response);
        Assert.assertNotNull(responseBody);
    }

    @Test
    public void test_StatusCode() {
        int statusCode = checkStatusCode(response);
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void test_ResponseTime() {
        long responseTime = checkResponseTime(response);
        logger.info("Response Time is  ==> " + responseTime);
        if (responseTime > 2000) {
            logger.warn("Response Time is greater than 2000ms");
        }
        Assert.assertTrue(responseTime < 15000);
    }

    @Test
    void test_StatusLine() {
        String statusLine = checkStatusLine(response);
        logger.info("Status Line is ==> " + statusLine);
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
    }

    @Test
    void test_ContentType() {
        String contentType = checkContentType(response);
        logger.info("Content Type is ==> " + contentType);
        Assert.assertEquals(contentType, "text/html; charset=UTF-8");
    }

    @Test
    void test_ServerType() {
        String serverType = checkServerType(response);
        logger.info("Server Type is ==> " + serverType);
        Assert.assertEquals(serverType, "Apache");
    }

    @Test
    void test_ContentEncoding() {
        String contentEncoding = checkContentEncoding(response);
        logger.info("Content Encoding is ==> " + contentEncoding);
        Assert.assertEquals(contentEncoding, "gzip");
    }

    @Test
    void test_Cookies() {
        String cookie = checkCookies(response, "PHPSESSID");
        logger.info("Cookie is ==> " + cookie);
        Assert.assertNotNull(cookie);
    }

    @AfterClass
    void tearDown() {
        logger.info("************* Finished TC_001_Get_All_Employees ***************");
    }
}
