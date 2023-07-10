package com.pageObjects;

import com.aventstack.extentreports.ExtentTest;
import com.utility.TestReporter;
import com.utility.WebDriverUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FileDownloaderPage extends BasePage {

    /**
     * WebElement
     */
    @FindBy(xpath = "//div[@id='content']//h3")
    private WebElement label_Header;

    @FindBy(xpath = "//div[@id='content']//a")
    private List<WebElement> link_FileDownload;

    /**
     * Action Methods
     */
    public boolean isPageHeaderDisplayed ()
    {
        return WebDriverUtils.doesControlExist(label_Header);
    }

    public int downloadAbleFileCount()
    {
        return link_FileDownload.size();
    }
}
