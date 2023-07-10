package com.utility;

import com.aventstack.extentreports.ExtentTest;

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

}
