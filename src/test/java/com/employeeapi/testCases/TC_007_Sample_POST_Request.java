package com.employeeapi.testCases;


import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.http.Method.POST;

public class TC_007_Sample_POST_Request {

    @Test
    void test_CustomerRegistration() {
        RestAssured.baseURI = "http://restapi.demoqa.com/customer";

        RequestSpecification httpRequest = RestAssured.given();

        JSONObject payloadParams = new JSONObject();
        payloadParams.put("FirstName", "Fname3");
        payloadParams.put("LastName", "Lname3");
        payloadParams.put("UserName", "FL3");
        payloadParams.put("Password", "password");
        payloadParams.put("Email", "test3@siriusxm.com");

        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(payloadParams.toJSONString());

        Response response = httpRequest.request(POST, "/register");

        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("successfully"));

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);

        String successCode = response.jsonPath().get("SuccessCode");
        Assert.assertEquals(successCode, "OPERATION_SUCCESS");

        String contentType = response.header("Content-Type");
        Assert.assertEquals(contentType,"application/json");

        Headers allheaders = response.headers();

        for(Header header : allheaders){
            System.out.println(header.getName() + "    " + header.getValue());
        }
    }
}
