package com.UKTalentHubJava.page_objects.unused_page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriver ldriver;

    public LoginPage(WebDriver rdriver) {
        ldriver = rdriver;
        PageFactory.initElements(rdriver, this);
    }

    @FindBy(id = "save")
    @CacheLookup
    WebElement acceptCookies;

    @FindBy(id = "gdpr-consent-notice")
    @CacheLookup
    WebElement cookiesIFrame;

    @FindBy(xpath = "//input[@type='text']")
    @CacheLookup
    WebElement txtUserName;

    @FindBy(name = "password")
    @CacheLookup
    WebElement txtPassword;

    @FindBy(name = "btnLogin")
    @CacheLookup
    WebElement btnLogin;

    @FindBy(css = "a[href='Logout.php']")
    @CacheLookup
    WebElement lnkLogout;

    public void switchToCookiesFrame() {
        try {
            new WebDriverWait(ldriver, Duration.ofSeconds(5)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(cookiesIFrame)).switchTo().frame(cookiesIFrame);
        } catch (Exception e) {

        }
//        ldriver.switchTo().frame(cookiesIFrame);
    }

    public void setAcceptCookies() {
        // had to introduce an infinite loop due to Selenium not always accepting cookies despite using
        // a wait with an elementToBeClickable expectation
        boolean cookiesAcceptedFlag = false;
        while (!cookiesAcceptedFlag) {
            try {
                new WebDriverWait(ldriver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(acceptCookies)).click();
                System.out.println("Cookies acceptance failed - retrying.. ");
            } catch (Exception e) {
                System.out.println("Cookies Accepted");
                cookiesAcceptedFlag = true;
            }
        }
    }

    public void setUserName(String uname) {
        txtUserName.sendKeys(uname);
    }

    public void setPassword(String pwd) {
        txtPassword.sendKeys(pwd);
    }

    public void clickSubmit() {
        System.out.println("Preparing to click log in button");
        try {
            System.out.println("Tried to click log in button");
//            new WebDriverWait(ldriver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(btnLogin)).click();
            Thread.sleep(1000);
            btnLogin.click();
            System.out.println("Clicked log in");
//            new WebDriverWait(ldriver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOf(btnLogin));
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Alert did not appear after attempting to log in");
        }
//        new WebDriverWait(ldriver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(btnLogin)).click();
//        btnLogin.click();

    }

    public void clickLogout() {
        lnkLogout.click();
    }


}









