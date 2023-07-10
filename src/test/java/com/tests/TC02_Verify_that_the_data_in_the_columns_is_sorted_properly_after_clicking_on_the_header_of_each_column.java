package com.tests;

import com.common.TestBase;
import com.pageObjects.DataTablesPage;
import com.pageObjects.HomePage;
import com.utility.TestReporter;
import com.utility.Utility;
import com.utility.WebDriverUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import static com.common.GlobalVariables.THEINTERNET_URL;

public class TC02_Verify_that_the_data_in_the_columns_is_sorted_properly_after_clicking_on_the_header_of_each_column extends TestBase {

    @Test(dataProvider = "getDataForTest", description = "Verify that the data in the columns is sorted ASC/DESC properly after clicking on the header of each column in the first table (Example 1)")
    public void TC01(Hashtable<String, String> data) {
        logStep = TestReporter.logStepInfo(logMethod, "Step #1: Navigate to " + THEINTERNET_URL);
        WebDriverUtils.navigateToTestSite(logStep, THEINTERNET_URL);

        logStep = TestReporter.logStepInfo(logMethod, "Step #2: Click on " +data.get("LinkName")+" link");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.clickOnTheLink("Sortable Data Tables");

        logStep = TestReporter.logStepInfo(logMethod, "Step #3: Verify that the data in the columns is sorted ASC/DESC properly when clicking on the header 1 times");
        DataTablesPage dataTablesPage = PageFactory.initElements(Utility.getDriver(), DataTablesPage.class);
        List<String> headerName = Arrays.asList(data.get("HeaderName").split("-"));
        for(int i=0; i<headerName.size(); i++)
        {
            dataTablesPage.verifyDataIsSortedASCAfterClickingOnce(logStep, headerName.get(i));
            dataTablesPage.verifyDataIsSortedDESCAfterClickingTwice(logStep, headerName.get(i));
        }
    }
}
