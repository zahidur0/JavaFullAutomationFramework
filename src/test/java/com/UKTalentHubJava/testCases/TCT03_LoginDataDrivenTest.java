package com.UKTalentHubJava.testCases;

import com.UKTalentHubJava.pageObjects.AutomationPractice;
import com.UKTalentHubJava.utilities.XLUtils;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;

public class TCT03_LoginDataDrivenTest extends BaseClassAutomationPractice {

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
            Assert.fail("Login failed");
        } else {
            logger.info("Login successful");
        }
    }

    @DataProvider(name = "LoginData")
    public Object[][] getData() throws IOException {
        String path = System.getProperty("user.dir") + "\\src\\test\\java\\com\\UKTalentHubJava\\testData\\AutomationPracticeLoginDetails.xlsx";
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
