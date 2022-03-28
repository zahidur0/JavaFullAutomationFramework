package com.UKTalentHubJava.testCases;

import com.UKTalentHubJava.pageObjects.AutomationPractice;
import com.UKTalentHubJava.screenshot_taker.ScreenshotTaker;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.*;
import java.util.Arrays;

public class TCT04_SqlLoginTest extends BaseClassAutomationPractice{

    @Test(dataProvider = "SqlLoginData")
    public void LoginDDT(String uname, String pwd) throws IOException {
        AutomationPractice ap = new AutomationPractice(driver);
        ap.setUsername(uname);
        logger.info("Entered username");
        ap.setPassword(pwd);
        logger.info("Entered password");
        ap.clickLogin();

        if (ap.incorrectPasswordMessageExists()) {
            logger.info("Login failed");
            new ScreenshotTaker(driver);
            Assert.fail("Login failed");
        } else {
            if (driver.getTitle().equals("My account - My Store")) {
                logger.info("Login successful");
            } else {
                Assert.fail("Account page not reached");
            }
        }
    }

    private Connection connect() {

        String url = "jdbc:sqlite:" +
                System.getProperty("user.dir") +
                "/src/test/java/com/UKTalentHubJava/testData/LoginDetails.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public int countRows() throws SQLException {
        String sql = "SELECT COUNT(*) FROM correct_login_details";
        Connection conn = this.connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs.getInt(1);
    }

    @DataProvider(name = "SqlLoginData")
    public Object[][] selectAll() throws SQLException {

        String sql = "SELECT username, password FROM correct_login_details";
        Connection conn = this.connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        int colNum = rs.getMetaData().getColumnCount();
        int rowNum = countRows();
        // data provider must return an Object[][] or Iterable<Object[]>
        Object[][] loginDetails = new Object[rowNum][colNum];
        System.out.println(rowNum);

        for (int i = 0; i < rowNum; i++) {
            rs.next();
            for (int j = 0; j <= (colNum - 1); j++) {
                System.out.println(i + " " + j);
                System.out.println(rs.getString(j + 1));
                loginDetails[i][j] = rs.getString(j + 1);
            }
        }

        conn.close();
        System.out.println(Arrays.deepToString(loginDetails));
        return loginDetails;
    }

    public static void main(String[] args) throws SQLException {
        TCT04_SqlLoginTest sqliteData = new TCT04_SqlLoginTest();
        sqliteData.selectAll();
    }

}