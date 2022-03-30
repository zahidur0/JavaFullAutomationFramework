package com.UKTalentHubJava.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AutomationPractice {

    WebDriver ldriver;

    public AutomationPractice(WebDriver rdriver) {
        ldriver = rdriver;
        PageFactory.initElements(rdriver, this);
    }

    @FindBy(id = "email")
    @CacheLookup
    WebElement email;

    @FindBy(id = "passwd")
    @CacheLookup
    WebElement password;

    @FindBy(id = "SubmitLogin")
    @CacheLookup
    WebElement loginButton;

//    @FindBy(xpath = "//div[@class='alert alert-danger']/p")
//    @CacheLookup
//    By incorrectPasswordMessage;

    public void setUsername(String username) {
        email.sendKeys(username);
    }

    public void setPassword(String pass) {
        password.sendKeys(pass);
    }

    public void clickLogin() {
        loginButton.click();
    }

    public boolean incorrectPasswordMessageExists() {
        return !ldriver.findElements(By.xpath("//div[@class='alert alert-danger']/p")).isEmpty();
    }

}
