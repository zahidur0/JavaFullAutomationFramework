package com.UKTalentHubJava.test_cases.selenium.selenium_test_scripts.step_definitions;

import com.UKTalentHubJava.utilities.ReadConfig;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BaseClass {

    public WebDriver driver;
    public WebDriver setup(String br) {
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