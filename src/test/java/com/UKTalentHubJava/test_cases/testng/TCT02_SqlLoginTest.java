package com.UKTalentHubJava.test_cases.testng;

import com.UKTalentHubJava.page_objects.AutomationPractice;
import com.UKTalentHubJava.screenshot_taker.ScreenshotTaker;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.Arrays;

public class TCT02_SqlLoginTest extends BaseClassAutomationPractice {

    @Test(dataProvider = "SqlLoginData")
    public void IncorrectLoginDDT(String uname, String pwd) throws IOException {
        AutomationPractice ap = new AutomationPractice(driver);
        ap.setUsername(uname);
        logger.debug("Entered username");
        ap.setPassword(pwd);
        logger.debug("Entered password");
        ap.clickLogin();

        if (ap.incorrectPasswordMessageExists()) {
            logger.info("Incorrect login test successful");
        } else {
            new ScreenshotTaker(driver);
            logger.error("Incorrect login test failed");
            Assert.fail("Incorrect login test failed");
        }
    }

    @Test(dataProvider = "SqlLoginData")
    public void CorrectLoginDDT(String uname, String pwd) throws IOException {
        AutomationPractice ap = new AutomationPractice(driver);
        ap.setUsername(uname);
        logger.debug("Entered username");
        ap.setPassword(pwd);
        logger.debug("Entered password");
        ap.clickLogin();

        if (driver.findElement(By.xpath("//a[text()='Sign out']")).isDisplayed()) {
            logger.info("Login successful");
        } else {
            new ScreenshotTaker(driver);
            logger.error("Login failed");
            Assert.fail("Login failed");
        }

    }

    @DataProvider(name = "SqlLoginData")
    public Object[][] selectAllCorrect(Method testMethod) throws SQLException {
        String methodName = testMethod.getName();
        int rowNum = 0;
        String sql = "";
        if (methodName.equals("CorrectLoginDDT")) {
            sql = "SELECT username, password FROM correct_login_details";
            rowNum = countRows("correct_login_details");
        } else if (methodName.equals("IncorrectLoginDDT")) {
            sql = "SELECT username, password FROM incorrect_login_details";
            rowNum = countRows("incorrect_login_details");
        }

        Connection conn = this.connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        int colNum = rs.getMetaData().getColumnCount();
        // data provider must return an Object[][] or Iterable<Object[]>
        Object[][] loginDetails = new Object[rowNum][colNum];
        System.out.println(rowNum);

        for (int i = 0; i < rowNum; i++) {
            rs.next();
            for (int j = 0; j <= (colNum - 1); j++) {
                loginDetails[i][j] = rs.getString(j + 1);
            }
        }
        conn.close();
        System.out.println(Arrays.deepToString(loginDetails));
        return loginDetails;
    }

    private Connection connect() {
        String url = "jdbc:sqlite:" +
                System.getProperty("user.dir") +
                "/src/test/java/com/UKTalentHubJava/test_data/LoginDetails.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public int countRows(String tableName) throws SQLException {
        String sql = "SELECT COUNT(*) FROM " + tableName;
        Connection conn = this.connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        int rowNum = rs.getInt(1);
        conn.close();
        return rowNum;
    }
}