package com.employeeapi.testCases;

import Utils.RestUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TC_003_Post_SignUp_Request {
    private static Map<String, String> requestMap = new HashMap<>();

    @BeforeClass
    public void postData() {
        requestMap.put("FirstName", RestUtils.getFirstName());
        requestMap.put("LastName", RestUtils.getLastName());
        requestMap.put("UserName", RestUtils.getUserName());
        requestMap.put("Password", RestUtils.getPassword());
        requestMap.put("Email", RestUtils.getEmailID());

        RestAssured.baseURI = "http://restapi.demoqa.com/customer";
        RestAssured.basePath = "/register";
    }

    @Test
    public void testPost() {
        given().
                contentType("application/json").
                body(requestMap).
                when().
                post().
                then().
                statusCode(201).
                and().
                body("SuccessCode", equalTo("OPERATION_SUCCESS")).
                and().
                body("Message", equalTo("Operation completed successfully"));
    }
}
