package com.utility;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Function;

import static com.common.GlobalVariables.WAIT_TIME_60;

public class WebDriverUtils extends Utility {
    public static void navigateToTestSite(ExtentTest logTest, String url) {
        try {
            TestReporter.logInfo(logTest, "Navigate to: " + url);
            Utility.log4j.info("navigateToTestSite method start ...");

            WebDriverUtils.switchToWindowHandle();
            Utility.getDriver().navigate().to(url);

            Utility.log4j.info("navigateToTestSite method start ...");
            WebDriverUtils.waitForPageLoaded();
        } catch (Exception e) {
            Utility.log4j.error("navigateToTestSite method - ERROR: ", e);
            TestReporter.logException(logTest, "navigateToTestSite method - ERROR: ", e);
        }
    }

    public static void waitForPageLoaded() {
        Wait<WebDriver> wait = new WebDriverWait(Utility.getDriver(), WAIT_TIME_60);
        try {
            //Wait for HTML load
            wait.until(new Function<WebDriver, Boolean>() {
                public Boolean apply(WebDriver driver) {
                    Utility.sleep(1);
                    boolean readyState = ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                    boolean activeJQuery = ((JavascriptExecutor) driver).executeScript("if (typeof jQuery != 'undefined') {return jQuery.active == 0; } else { return true; }").equals(true);
                    return readyState && activeJQuery;
                }
            });

        } catch (Exception e) {
            Utility.log4j.error("waitForPageloaded - ERROR: ", e);
        }
    }

    public static void switchToWindowHandle() {
        try {
            String popupWindowHandle = getWindowHandle(Utility.getDriver());
            Utility.getDriver().switchTo().window(popupWindowHandle);
            maximizeWindow();
        } catch (Exception e) {
            Utility.log4j.error("switchToWindowHandle method - ERROR: ", e);
        }
    }

    public static String getWindowHandle(WebDriver driver) {
        //get all the window handles after the popup window appears
        Set<String> afterPopup = driver.getWindowHandles();
        Iterator<String> iterator = afterPopup.iterator();
        while (iterator.hasNext()) {
            Utility.subWindowHandler = iterator.next();
        }

        return Utility.subWindowHandler;
    }

    public static void maximizeWindow() {
        try {
            Utility.getDriver().manage().window().maximize();
        } catch (Exception e) {
            Utility.log4j.error("maximizeWindow method - ERROR: ", e);
        }
    }

    public static void waitForControl(WebElement element) {
        try {
            new WebDriverWait(Utility.getDriver(), WAIT_TIME_60).until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {

        }
    }

    public static void refreshPage() {
        try {
            Utility.getDriver().navigate().refresh();
            WebDriverUtils.waitForPageLoaded();
        } catch (Exception e) {
            Utility.log4j.error("refreshPage method - ERROR - ", e);
        }
    }

    public static boolean doesControlExist(WebElement control) {
        try {
            return control.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isFileDownloaded(ExtentTest logTest, String expectedFileName, String fileExtension, int timeOut) {
        try {
            // Download Folder Path
            String folderName = System.getProperty("user.dir") + File.separator + "downloads";

            // Array to Store List of Files in Directory
            File[] listOfFiles;

            // Store File Name
            String fileName;

            //  Consider file is not downloaded
            boolean fileDownloaded = false;

            // capture time before looking for files in directory
            // last modified time of previous files will always less than start time
            // this is basically to ignore previous downloaded files
            long startTime = Instant.now().toEpochMilli();

            // Time to wait for download to finish
            long waitTime = startTime + timeOut;

            // while current time is less than wait time
            while (Instant.now().toEpochMilli() < waitTime) {
                // get all the files of the folder
                listOfFiles = new File(folderName).listFiles();

                // iterate through each file
                for (File file : listOfFiles) {
                    // get the name of the current file
                    fileName = file.getName().toLowerCase();

                    // condition 1 - Last Modified Time > Start Time
                    // condition 2 - till the time file is completely downloaded extension will be crdownload
                    // Condition 3 - Current File name contains expected Text
                    // Condition 4 - Current File name contains expected extension
                    if (file.lastModified() > startTime && !fileName.contains("crdownload") && fileName.contains(expectedFileName.toLowerCase()) && fileName.contains(fileExtension.toLowerCase())) {
                        // File Found
                        fileDownloaded = true;
                        break;
                    }
                }
                // File Found Break While Loop
                if (fileDownloaded)
                    break;
            }
            // File Not Found
            return fileDownloaded;
        } catch (Exception e) {
            return false;
        }
    }
}
