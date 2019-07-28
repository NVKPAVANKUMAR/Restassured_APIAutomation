package com.employeeapi.base;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;


public class TestBase {

    protected static RequestSpecification httpRequest;
    protected static Response response;
    protected String empID = "2";

    protected Logger logger;

    @BeforeClass
    public void setUp() {
        logger = LogManager.getLogger(this.getClass().getName());
        logger.debug("This is will be printed on debug");
    }


    protected String checkResponseBody(Response res) {
        logger.info("************* Checking Response Body ***********");
        return res.getBody().asString();
    }

    protected int checkStatusCode(Response res) {
        logger.info("************ Checking Status Code ***********");
        return res.getStatusCode();
    }

    protected long checkResponseTime(Response res) {
        logger.info("************ Checking Response Time ***********");
        return res.getTime();
    }

    protected String checkStatusLine(Response res) {
        logger.info("********** Checking Status Line ***********");
        return res.getStatusLine();
    }

    protected String checkContentType(Response res) {
        logger.info("********** Checking Content Type *************");
        return res.header("Content-Type");
    }

    protected String checkServerType(Response res) {
        logger.info("********** Checking Server Type *************");
        return res.header("Server");
    }

    protected String checkContentEncoding(Response res) {
        logger.info("********** Checking Content Encoding *************");
        return res.header("Content-Encoding");

    }

    protected String checkCookies(Response res, String cookie_value) {
        logger.info("********** Checking Cookies *************");
        return res.getCookie(cookie_value);
    }
}
