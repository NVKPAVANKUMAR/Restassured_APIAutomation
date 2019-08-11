package com.employeeapi.testCases;

import Utils.XLUtils;
import com.employeeapi.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.http.Method.POST;

public class TC_009_Post_API_DataDriven extends TestBase {

    private String path = "testdata/EmpData.xlsx";
    private List<String> responseList = new ArrayList<>();

    @BeforeClass
    public void setup() {
        logger.info("********** Started TC009_Post_DataDriven_Employees *********");
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
    }

    @Test(dataProvider = "empdataprovider")
    public void postNewEmployee(String ename, String eage, String esal, String msg) throws IOException {
        RequestSpecification httpRequest = RestAssured.given();

        JSONObject payloadParams = new JSONObject();
        payloadParams.put("name", ename);
        payloadParams.put("salary", esal);
        payloadParams.put("age", eage);
        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(payloadParams.toJSONString());

        Response response = httpRequest.request(POST, "/create");

        String responseBody = response.getBody().asString();
        // System.out.println("Response Body is : " + responseBody);
        responseList.add(responseBody);
        int rownum = XLUtils.getRowCount(path, "Sheet1");
        for (int r = 0; r <= rownum; r++) {
            try {
                XLUtils.setCellData(path, "Sheet1", r + 1, 3, responseList.get(r));
            }catch (IndexOutOfBoundsException e){
                e.getMessage();
            }
        }
    }

    @DataProvider(name = "empdataprovider")
    Object[][] getEmpData() throws IOException {
        int rownum = XLUtils.getRowCount(path, "Sheet1");
        int colcount = XLUtils.getCellCount(path, "Sheet1", 1);

        String empdata[][] = new String[rownum][colcount];
        for (int r = 1; r <= rownum; r++) {
            for (int c = 0; c < colcount - 1; c++) {
                empdata[r - 1][c] = XLUtils.getCellData(path, "Sheet1", r, c);
            }
        }
        return empdata;
    }
}
