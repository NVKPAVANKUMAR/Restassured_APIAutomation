package com.employeeapi.testCases;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_008_Autherization {
    @Test
    public void Test_Basic_Auth() {
        RestAssured.baseURI = "http://restapi.demoqa.com/authentication/CheckForAuthentication";

        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("ToolsQA");
        authScheme.setPassword("TestPassword");

        RestAssured.authentication = authScheme;

        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.request(Method.GET, "/");

        String responseBody = response.getBody().asString();
        System.out.println("Response Body ==> " + responseBody);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }
}
