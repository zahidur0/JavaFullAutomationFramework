package com.UKTalentHubJava.testCases;

import com.UKTalentHubJava.utilities.ReadConfig;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    ReadConfig readconfig = new ReadConfig();
    public String baseURL = readconfig.getApplicationURL();

    public String username = readconfig.getUsername();
    public String password = readconfig.getPassword();


    public WebDriver driver;
    public static Logger logger;

    @Parameters("browser")
    @BeforeMethod
    public void setup(String br) {

        logger = Logger.getLogger("UkTalentHub");
        PropertyConfigurator.configure("Log4j.properties");

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);

        if (br.equals("chrome")) {
            ChromeOptions ops = new ChromeOptions();
            ops.addArguments("--disable-notifications");

            //System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\indic\\Desktop\\Work\\Qualitest\\JavaCoP\\framework\\new\\JavaFullAutomationFramework\\Drivers\\chromedriverV99.4.0\\chromedriver.exe");
            //WebDriverManager.chromedriver().setup();

            driver = (ChromeDriver) new ChromeDriver(ops);
            driver.manage().window().maximize();

        } else if (br.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", readconfig.getFirefoxPath());
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        } else if (br.equals("ie")) {
            System.setProperty("webdriver.ie.driver", readconfig.getIEPath());
            driver = new InternetExplorerDriver();
            driver.manage().window().maximize();
        }


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(baseURL);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    public void captureScreen(WebDriver driver, String tname) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File target = new File(System.getProperty("user.dir") + "/screenshots/" + tname + ".png");
        FileUtils.copyFile(source, target);
        System.out.println("Screenshot taken");
    }

    public String randomestring() {
        String generatedstring = RandomStringUtils.randomAlphabetic(8);
        return (generatedstring);
    }

    public static String randomeNum() {
        String generatedString2 = RandomStringUtils.randomNumeric(4);
        return (generatedString2);
    }

    public boolean isAlertPresent() { // user defined method created to check alert is present or not
            try {
                new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.alertIsPresent());
//            driver.switchTo().alert();
                return true;
            } catch (TimeoutException e) {
                System.out.println("Alert not present");
                return false;
            }
        }
}
