package com.utility.webDrivers;

import com.aventstack.extentreports.ExtentTest;
import com.utility.TestReporter;
import com.utility.Utility;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LocalDriver {
    public RemoteWebDriver driver;

    public synchronized RemoteWebDriver initializeDriver(ExtentTest logTest, String browser){
        try {
            Utility.log4j.info("createInstance method - Starts");
            TestReporter.logInfo(logTest, "createInstance method - Starts");

            ChromeOptions ChOptions = new ChromeOptions();
            FirefoxOptions FFOptions = new FirefoxOptions();
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(ChOptions);
                    break;
                case "chromeHeadless":
                    ChOptions.addArguments("--headless");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(ChOptions);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(FFOptions);
                    break;
                case "firefoxHeadless":
                    FFOptions.setHeadless(true);
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(FFOptions);
                    break;
                default:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
            }
//            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            return driver;
        } catch (Exception e){
            Utility.log4j.error("createInstance method - ERROR: ", e);
            TestReporter.logException(logTest, "createInstance method - ERROR: ", e);
        }
        return null;
    }
}
