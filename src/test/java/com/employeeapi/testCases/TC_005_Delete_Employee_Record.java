package com.employeeapi.testCases;

import com.employeeapi.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_005_Delete_Employee_Record extends TestBase {
    RequestSpecification httpRequest;
    Response response;

    @BeforeClass
    void updateEmployee() {
        logger.info("************ Started TC005_DELETE_Employee_Record *************");
        RestAssured.baseURI = "https://reqres.in";
        httpRequest = RestAssured.given();
        response = httpRequest.request("DELETE", "/api/users/" + empID);
    }

    @Test
    public void test_checkStatusCode() {
        int statusCode = checkStatusCode(response);
        Assert.assertEquals(statusCode, 204);
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
        Assert.assertEquals(statusLine, "HTTP/1.1 204 No Content");
    }

    @Test
    void test_ServerType() {
        String serverType = checkServerType(response);
        logger.info("Server Type is ==> " + serverType);
        Assert.assertEquals(serverType, "cloudflare");
    }

    @AfterClass
    void tearDown() {
        logger.info("************* Finished TC_005_Delete_Single_Employee_Record ***************");
    }
}
