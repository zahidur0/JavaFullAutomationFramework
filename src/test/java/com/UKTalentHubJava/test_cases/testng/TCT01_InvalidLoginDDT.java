package com.UKTalentHubJava.test_cases.testng;

import com.UKTalentHubJava.page_objects.AutomationPractice;
import com.UKTalentHubJava.utilities.XLUtils;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;

public class TCT01_InvalidLoginDDT extends BaseClassAutomationPractice {
    @Test(dataProvider = "LoginData")
    public void LoginDDT(String uname, String pwd) throws IOException {
        AutomationPractice ap = new AutomationPractice(driver);
        ap.setUsername(uname);
        logger.info("Entered username");
        ap.setPassword(pwd);
        logger.info("Entered password");
        ap.clickLogin();

        if (ap.incorrectPasswordMessageExists()) {
            logger.info("Login failed");
//            new ScreenshotTaker(driver, System.getProperty("user.dir") +
//                    "\\screenshots\\" + method.getName() + ".png");
//            Assert.fail("Login failed");
        } else {
            if (driver.getTitle().equals("My account - My Store")) {
                logger.info("Login successful");
                Assert.fail("Login successful");
            } else {
                Assert.fail("Account page not reached");
            }
        }
    }

    @DataProvider(name = "LoginData")
    public Object[][] getData() throws IOException {
        String path = System.getProperty("user.dir") + "\\src\\test\\java\\com\\UKTalentHubJava\\test_data\\one-entry.xlsx";
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
    }
}
