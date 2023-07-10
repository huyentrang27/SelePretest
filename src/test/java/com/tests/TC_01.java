package com.tests;

import com.common.TestBase;
import com.pageObjects.DataTablesPage;
import com.pageObjects.HomePage;
import com.utility.TestReporter;
import com.utility.Utility;
import com.utility.WebDriverUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static com.common.GlobalVariables.THEINTERNET_URL;

public class TC_01 extends TestBase {

    @Test(dataProvider = "getDataForTest", description = "Verify that the first table (Example 1) has proper data in the first row")
    public void TC01(Hashtable<String, String> data) {
        logStep = TestReporter.logStepInfo(logMethod, "Step #1: Navigate to " + THEINTERNET_URL);
        WebDriverUtils.navigateToTestSite(logStep, THEINTERNET_URL);

        logStep = TestReporter.logStepInfo(logMethod, "Step #2: Click on " +data.get("LinkName")+" link");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.clickOnTheLink(data.get("LinkName"));

        logStep = TestReporter.logStepInfo(logMethod, "Step #3: Verify that user is on File DownLoader page");
        DataTablesPage dataTablesPage = PageFactory.initElements(Utility.getDriver(), DataTablesPage.class);
        dataTablesPage.verifyProperDataDisplayInTheFirstRow(logStep, data.get("ExpectedData"));
    }
}
