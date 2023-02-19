package com.UKTalentHubJava.test_cases.testng;

import com.UKTalentHubJava.page_objects.AutomationPractice;
import com.UKTalentHubJava.screenshot_taker.ScreenshotTaker;
import com.UKTalentHubJava.utilities.XLUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class TCT01_LoginDataDrivenTest extends BaseClassAutomationPractice {

    @Test(dataProvider = "LoginData")
    public void ValidLoginDDT(String uname, String pwd) throws IOException {
        AutomationPractice ap = new AutomationPractice(driver);
        ap.setUsername(uname);
        logger.info("Entered username");
        ap.setPassword(pwd);
        logger.info("Entered password");
        ap.clickLogin();

        if (driver.findElement(By.xpath("//a[text()='Sign out']")).isDisplayed()) {
            logger.info("Login successful");
        } else {
            new ScreenshotTaker(driver);
            logger.error("Login failed");
            Assert.fail("Login failed");
        }
    }

    @Test(dataProvider = "LoginData")
    public void InvalidLoginDDT(String uname, String pwd) throws IOException {
        AutomationPractice ap = new AutomationPractice(driver);
        ap.setUsername(uname);
        logger.info("Entered username");
        ap.setPassword(pwd);
        logger.info("Entered password");
        ap.clickLogin();

        if (ap.incorrectPasswordMessageExists()) {
            logger.info("Incorrect login test successful");
        } else {
            new ScreenshotTaker(driver);
            logger.error("Incorrect login test failed");
            Assert.fail("Incorrect login test failed");
        }
    }

    @DataProvider(name = "LoginData")
    public Object[][] getValidData(Method testMethod) throws IOException {
        String methodName = testMethod.getName();
        String path = "";
        if (methodName.equals("ValidLoginDDT")) {
            path = System.getProperty("user.dir") + "\\src\\test\\java\\com\\UKTalentHubJava\\test_data\\ValidLoginDetails.xlsx";
        } else if (methodName.equals("InvalidLoginDDT")) {
            path = System.getProperty("user.dir") + "\\src\\test\\java\\com\\UKTalentHubJava\\test_data\\InvalidLoginDetails.xlsx";
        }

        int rowNum = XLUtils.getRowCount(path, "Account Details");
        int colCount = XLUtils.getCellCount(path, "Account Details", 1);

        Object[][] loginData = new String[rowNum][colCount];

        for (int i = 1; i <= rowNum; i++) {
            for (int j = 0; j < colCount; j++) {
                loginData[i - 1][j] = XLUtils.getCellData(path, "Account Details", i, j);//1 0
            }
        }
        System.out.println(Arrays.deepToString(loginData));
        return loginData;
    }
}