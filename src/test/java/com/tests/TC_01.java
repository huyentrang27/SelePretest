package com.tests;

import com.common.TestBase;
import com.pageObjects.FileDownloaderPage;
import com.pageObjects.HomePage;
import com.utility.Assertion;
import com.utility.TestReporter;
import com.utility.Utility;
import com.utility.WebDriverUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static com.common.GlobalVariables.THEINTERNET_URL;

public class TC_01 extends TestBase {
    @Test(description = " Verify that the file has been downloaded successfully to your local machine with correct file name")
    public void TC01() {
        String fileName = "sample_media_file.png";
        logStep = TestReporter.logStepInfo(logMethod, "Step #1: Navigate to " + THEINTERNET_URL);
        WebDriverUtils.navigateToTestSite(logStep, THEINTERNET_URL);

        logStep = TestReporter.logStepInfo(logMethod, "Step #2: Click on " + "File Download" + " link");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.clickOnTheLink("File Download");

        logStep = TestReporter.logStepInfo(logMethod, "VP #1: Verify that user is on File DownLoader page");
        FileDownloaderPage fileDownloaderPage = PageFactory.initElements(Utility.getDriver(), FileDownloaderPage.class);
        Assertion.assertTrueFalse(logStep, true, fileDownloaderPage.isPageHeaderDisplayed());

        logStep = TestReporter.logStepInfo(logMethod, "VP #2: Verify that user is on File DownLoader page");
        Assertion.assertEquals(logStep, 25, fileDownloaderPage.downloadAbleFileCount());

        logStep = TestReporter.logStepInfo(logMethod, "Step #3: Click on " + fileName + " file");
        fileDownloaderPage.clickOnTheLink(fileName);
        String fileExtension = fileName.split(".")[fileName.split(".").length-1];
        Assertion.assertTrueFalse(logStep, true, WebDriverUtils.isFileDownloaded(logStep, fileName, fileExtension, 2));
    }
}
