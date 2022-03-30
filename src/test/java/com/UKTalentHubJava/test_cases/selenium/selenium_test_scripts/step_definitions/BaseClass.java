package com.UKTalentHubJava.test_cases.selenium.selenium_test_scripts.step_definitions;

import com.UKTalentHubJava.utilities.ReadConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;

public class BaseClass {

    public WebDriver driver;
    public Logger logger;

    public WebDriver setup(String br) {
        logger = LogManager.getLogger(BaseClass.class);
        ReadConfig readConfig = new ReadConfig();
        switch (br) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Drivers\\chromedriverV99.4.0\\chromedriver.exe");
                return new ChromeDriver();
            case "firefox":
                System.setProperty("webdriver.gecko.driver", readConfig.getFirefoxPath());
                return new FirefoxDriver();
            case "ie":
                System.setProperty("webdriver.ie.driver", readConfig.getIEPath());
                return new InternetExplorerDriver();
        }
        System.out.println("Defaulting to Chrome due to no browser specified.");
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Drivers\\chromedriverV99.4.0\\chromedriver.exe");
        return new ChromeDriver();
    }
}