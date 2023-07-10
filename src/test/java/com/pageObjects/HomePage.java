package com.pageObjects;

import com.utility.Utility;
import com.utility.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage extends Utility{
    /**
     * DynamicLocators
     */
    String dynamicLink = "//div[@id='content']//a[text()='%s']";

    /**
     * Methods
     */
    public void clickOnTheLink (String linkName) {
        WebElement dynamic_Link = Utility.getDriver().findElement(By.xpath((String.format(dynamicLink, linkName))));
        WebDriverUtils.waitForControl(dynamic_Link);
        dynamic_Link.click();
    }
}
