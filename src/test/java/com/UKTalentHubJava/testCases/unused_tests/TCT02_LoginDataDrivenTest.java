package com.UKTalentHubJava.testCases.unused_tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;

import com.UKTalentHubJava.testCases.BaseClass;
import com.UKTalentHubJava.utilities.XLUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.UKTalentHubJava.pageObjects.LoginPage;

public class TCT02_LoginDataDrivenTest extends BaseClass {

    @Test(dataProvider = "LoginData")
    public void loginDDT(String user, String pwd) throws IOException, InterruptedException {

        logger.info("URL is opened");

        LoginPage lp = new LoginPage(driver);
        lp.switchToCookiesFrame();
        lp.setAcceptCookies();
        driver.switchTo().defaultContent();
//        System.out.println(user);
        lp.setUserName(user);
        logger.info("Entered username");
        lp.setPassword(pwd);
        logger.info("Entered password");
        System.out.println("reached log in button");
        lp.clickSubmit();
        System.out.println("reached alert section");
        if (isAlertPresent()) {
//            new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.alertIsPresent()).accept();
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

    @DataProvider(name = "LoginData")
    public Object[][] getData() throws IOException {
//        String path = System.getProperty("user.dir") + "src/test/java/com/UKTalentHubJava/testData/LoginData.xlsx";
        String path = System.getProperty("user.dir") + "\\src\\test\\java\\com\\UKTalentHubJava\\testData\\LoginData.xlsx";
        int rowNum = XLUtils.getRowCount(path, "Sheet1");
        int colCount = XLUtils.getCellCount(path, "Sheet1", 1);

        Object[][] loginData = new String[rowNum][colCount];

        for (int i = 1; i <= rowNum; i++) {
            for (int j = 0; j < colCount; j++) {
                loginData[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j);//1 0
            }
        }
        System.out.println(Arrays.deepToString(loginData));
        return loginData;

        // manually input data if required
//        Object[][] loginData = new Object[3][2];
//        loginData[0][0] = "firstsetusername";
//        loginData[0][1] = "firstpassword";
//
//        loginData[1][0] = username;
//        loginData[1][1] = password;
//
//        loginData[2][0] = "thirdsetusername";
//        loginData[2][1] = "thirdpassword";
//        return loginData;
    }
}


