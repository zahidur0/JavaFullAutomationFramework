package com.UKTalentHubJava.test_cases.selenium.selenium_test_scripts.step_definitions;


import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import com.UKTalentHubJava.utilities.ReadConfig;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class TrelloLoginStep extends BaseClass {

    BaseClass base;
    ReadConfig configReader = new ReadConfig();
    String trelloLoginPageUrl = configReader.getTrelloLoginPageUrl();
    String loginId = configReader.getLoginId();
    String passwordId = configReader.getPasswordId();
    String loginButtonId = configReader.getLoginButtonId();
    String loginErrorMessageXPath = configReader.getLoginErrorMessageXPath();

    // This constructor is required to pass the driver from
    // the cucumber hooks to this test class (see cucumber picocontainer)
    public TrelloLoginStep(BaseClass base) {
        this.base = base;
    }

    @Given("the user is on the trello login page")
    public void theUserIsOnTheTrelloLoginPage() {
        base.driver.get(trelloLoginPageUrl);
        Assert.assertEquals("https://trello.com/login", base.driver.getCurrentUrl());
    }

    @When("the user enters username {string} and password {string}")
    public void theUserEnterUsernameAndPassword(String login, String password) {
        base.driver.findElement(By.id(loginId)).sendKeys(login);
        base.driver.findElement(By.id(passwordId)).sendKeys(password);
    }

    @And("the user click on the login button")
    public void theUserClickOnTheLoginButton() {
        base.driver.findElement(By.id(loginButtonId)).click();
    }

    @Then("the user does not successfully login into the application homepage")
    public void theUserDoNotSuccessfullyLoginIntoTheApplicationHomepage() {
        WebDriverWait wait = new WebDriverWait(base.driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(loginErrorMessageXPath)));
        System.out.println("test");
    }

}
