package com.UKTalentHubJava.testCases.unused_tests;


import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.UKTalentHubJava.pageObjects.LoginPage;


public class TCT01_LoginTest extends BaseClass {

    @Test
    public void loginTest() throws IOException {

        logger.info("URL is opened");

        LoginPage lp = new LoginPage(driver);
        lp.switchToCookiesFrame();
        lp.setAcceptCookies();
        driver.switchTo().defaultContent();

        lp.setUserName(username);
        logger.info("Entered username");
        lp.setPassword(password);
        logger.info("Entered password");

        lp.clickSubmit();

        if (isAlertPresent()) {
            driver.switchTo().alert().accept();
            driver.switchTo().defaultContent();
            logger.warn("Login failed");
            Assert.fail("login failed");
        } else {
            logger.info("Login passed");
            if (driver.getTitle().equals("Guru99 Bank Manager HomePage")) {
                logger.info("Login test passed");
            } else {
                captureScreen(driver, "loginTest");
                logger.info("Login test failed (window title not correct)");
                Assert.fail("window title is not correct");
            }
            lp.clickLogout();
            // close logout alert
            driver.switchTo().alert().accept();
            driver.switchTo().defaultContent();
        }
    }
}
