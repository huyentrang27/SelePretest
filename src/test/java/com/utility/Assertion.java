package com.utility;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.utility.Utility.log4j;

public class Assertion {

    public static void verifyActualAndExpected(ExtentTest logTest, String actual, String expected) {
        try {
            log4j.info("Actual: " + actual);
            log4j.info("Expected: " + expected);
            if (actual.trim().equalsIgnoreCase(expected.trim()))
                TestReporter.logPass(logTest, "Actual: " + actual + "</br>Expected: " + expected + "</br>");
            else
                TestReporter.logFail(logTest, "Actual: " + actual + "</br>Expected: " + expected + "</br>");
        } catch (Exception e) {
            log4j.error("verifyActualAndExpected method - ERROR: ", e);
            TestReporter.logException(logTest, "verifyActualAndExpected method - ERROR: ", e);
        }
    }

    public static void verifyActualAndExpected(ExtentTest logTest, List<String> actual, List<String> expected) {
        try {
            for(int i=0; i< actual.size(); i++)
            {
                log4j.info("Actual: " + actual.get(i));
                log4j.info("Expected: " + expected.get(i));
                if (actual.get(i).trim().equalsIgnoreCase(expected.get(i).trim()))
                    TestReporter.logPass(logTest, "Actual: " + actual + "</br>Expected: " + expected + "</br>");
                else
                    TestReporter.logFail(logTest, "Actual: " + actual + "</br>Expected: " + expected + "</br>");
            }

        } catch (Exception e) {
            log4j.error("verifyActualAndExpected method - ERROR: ", e);
            TestReporter.logException(logTest, "verifyActualAndExpected method - ERROR: ", e);
        }
    }
    public static void assertTrueFalse(ExtentTest logTest, boolean expected, boolean actual) {
        try {
            if (expected == actual) {
                TestReporter.logPass(logTest, "The result is matched, <br/>Expected Result: " + expected + "<br/>Actual Result: " + actual);
            } else {
                TestReporter.logFail(logTest, "The result did not match, <br/>Expected Result: " + expected + "<br/>Actual Result: " + actual);
            }
        } catch (Exception e) {
            log4j.error("assertTrueFalse method - ERROR - ", e);
            TestReporter.logException(logTest, "assertTrueFalse method - ERROR", e);
        }
    }

    public static void assertEquals(ExtentTest logTest, double expected, double actual) {

        try {
            if (Math.abs(actual - expected) < 0.0000001) TestReporter.logPass(logTest, "The result is matched, <br/>Expected Result: " + expected + "<br/>Actual Result: " + actual);
            else TestReporter.logFail(logTest, "The result is matched, <br/>Expected Result: " + expected + "<br/>Actual Result: " + actual);

        } catch (Exception e) {

            log4j.error("assertEquals method - ERROR - ", e);
            TestReporter.logException(logTest, "assertEquals method - ERROR", e);
        }
    }

}
