package com.employeeapi.testCases;

import Utils.ConfigParser;
import Utils.XLUtils;
import com.employeeapi.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TC_010_KWD_API_TEST extends TestBase {


    private String path = "testdata/EmpData.xlsx";
    private int actualResponseCode;
    private List<String> responseList = new ArrayList<>();


    @BeforeClass
    void getAllEmployees() {
        logger.info("********** Started TC010 Keyword Driven API Tests *********");
        RestAssured.baseURI = ConfigParser.parser("reqres_api_baseurl");
    }

    @Test(dataProvider = "empdataprovider")
    void TestReqResAPI(String API_call, String endpoint, String request_body, String expectedResponseCode, String previousResp) {
        switch (API_call) {
            case "GET":
            case "DELETE":

                httpRequest = RestAssured.given();
                response = httpRequest.request(API_call, endpoint);
                actualResponseCode = checkStatusCode(response);
                Assert.assertEquals(Integer.parseInt(expectedResponseCode), actualResponseCode);
                responseList.add(response.getBody().asString());
                break;

            case "POST":
            case "PUT":
            case "PATCH":

                httpRequest = RestAssured.given();
                httpRequest.header("Content-Type", "application/json");
                httpRequest.body(request_body);
                response = httpRequest.request(API_call, endpoint);
                actualResponseCode = checkStatusCode(response);
                Assert.assertEquals(Integer.parseInt(expectedResponseCode), actualResponseCode);
                responseList.add(response.getBody().asString());
                break;
        }
    }

    @DataProvider(name = "empdataprovider")
    Object[][] getEmpData() throws IOException {
        int rownum = XLUtils.getRowCount(path, "KWD");
        int colcount = XLUtils.getCellCount(path, "KWD", 1);
        String[][] empdata = new String[rownum][colcount];
        for (int r = 1; r <= rownum; r++) {
            for (int c = 0; c <= colcount - 1; c++) {
                empdata[r - 1][c] = XLUtils.getCellData(path, "KWD", r, c);
            }
        }
        return empdata;
    }

    private void setResponse() {
        try {
            int rownum = XLUtils.getRowCount(path, "KWD");
            for (int r = 0; r <= rownum; r++) {
                try {
                    XLUtils.setCellData(path, "KWD", r + 1, 4, responseList.get(r));
                } catch (IndexOutOfBoundsException e) {
                    e.getMessage();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @AfterClass
    public void tearDown(){
        logger.info("TC_010 Execution done & Writing back response to sheet");
        setResponse();
    }

}
