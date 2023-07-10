package com.pageObjects;

import com.aventstack.extentreports.ExtentTest;
import com.utility.Assertion;
import com.utility.TestReporter;
import com.utility.Utility;
import com.utility.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataTablesPage extends Utility {
    /**
     * Xpath
     */
    String xpath_FirstRowValues = "//table[@id='table1']//tbody/tr[1]/td";
    String xpath_ValuesOfCorrespondingColumn = "//table[@id='table1']//tr/td[count(//table[@id='table1']//span[contains(text(), '%s')]/../preceding-sibling::th) +1]";
    String dynamic_Header = "//table[@id='table1']//span[text()='%s']";

    /**
     * Methods
     */
    public void clickOnTheHeader(String headerName, int count)
    {
        WebElement header = Utility.getDriver().findElement(By.xpath((String.format(dynamic_Header, headerName))));
        WebDriverUtils.waitForControl(header);
        for(int i=0; i<count; i++)
            header.click();
    }

    public void verifyProperDataDisplayInTheFirstRow (ExtentTest logTest, String expectedData)
    {
        List<String> properData = Arrays.asList(expectedData.split("-"));
        List<WebElement> firstRowValues = getDriver().findElements(By.xpath(xpath_FirstRowValues));
        for(int i=0; i<properData.size(); i++)
            Assertion.verifyActualAndExpected(logTest, firstRowValues.get(i).getText(), properData.get(i));
    }

    public void verifyDataIsSortedASCAfterClickingOnce(ExtentTest logTest, String headerName)
    {
        WebDriverUtils.refreshPage();
        TestReporter.logInfo(logTest, "Get values of " + headerName + " column before sorting");
        List<WebElement> valuesOfCorrespondingColumnElement = getDriver().findElements(By.xpath(String.format(xpath_ValuesOfCorrespondingColumn, headerName)));
        ArrayList valuesBeforeSorting = new ArrayList<>();
        for (WebElement e : valuesOfCorrespondingColumnElement.toArray(new WebElement[0]))
            valuesBeforeSorting.add(e.getText());

        Utility.sortDataASC(logTest, valuesBeforeSorting);
        TestReporter.logInfo(logTest, valuesBeforeSorting.toString());

        TestReporter.logInfo(logTest, "Click on " + headerName + " header once");
        clickOnTheHeader(headerName, 1);

        TestReporter.logInfo(logTest, "Get values of " + headerName + " column");
        valuesOfCorrespondingColumnElement = getDriver().findElements(By.xpath(String.format(xpath_ValuesOfCorrespondingColumn, headerName)));
        ArrayList valuesAfterSorting = new ArrayList<>();
        for (WebElement e : valuesOfCorrespondingColumnElement.toArray(new WebElement[0]))
            valuesAfterSorting.add(e.getText());
        TestReporter.logInfo(logTest, valuesAfterSorting.toString());

        TestReporter.logInfo(logTest, "Verify data of " +headerName+ " column is sorted ASC after clicking on the header once");
        Assertion.verifyActualAndExpected(logTest, valuesAfterSorting, valuesBeforeSorting);
    }

    public void verifyDataIsSortedDESCAfterClickingTwice(ExtentTest logTest, String headerName)
    {
        WebDriverUtils.refreshPage();
        TestReporter.logInfo(logTest, "Get values of " + headerName + "column before sorting");
        List<WebElement> valuesOfCorrespondingColumnElement = getDriver().findElements(By.xpath(String.format(xpath_ValuesOfCorrespondingColumn, headerName)));
        ArrayList valuesBeforeSorting = new ArrayList<>();
        for (WebElement e : valuesOfCorrespondingColumnElement.toArray(new WebElement[0]))
            valuesBeforeSorting.add(e.getText());

        Utility.sortDataDESC(logTest, valuesBeforeSorting);

        TestReporter.logInfo(logTest, "Click on " + headerName + "header twice");
        clickOnTheHeader(headerName, 2);

        TestReporter.logInfo(logTest, "Get values of " + headerName + "column");
        valuesOfCorrespondingColumnElement = getDriver().findElements(By.xpath(String.format(xpath_ValuesOfCorrespondingColumn, headerName)));
        ArrayList valuesAfterSorting = new ArrayList<>();
        for (WebElement e : valuesOfCorrespondingColumnElement.toArray(new WebElement[0]))
            valuesAfterSorting.add(e.getText());

        TestReporter.logInfo(logTest, "Verify data of " +headerName+ " column is sorted DESC after clicking on the header twice");
        Assertion.verifyActualAndExpected(logTest, valuesAfterSorting, valuesBeforeSorting);
    }
}
