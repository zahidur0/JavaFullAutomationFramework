package com.UKTalentHubJava.test_cases.step_definitions;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.UKTalentHubJava.utilities.ReadConfig;


public class TrelloLoginStep {
    ReadConfig configReader = new ReadConfig();
    String driverPath = configReader.getDriverPath();
    String trelloLoginPageUrl = configReader.getTrelloLoginPageUrl();
    String loginId = configReader.getLoginId();
    String passwordId = configReader.getPasswordId();
    String loginButtonId = configReader.getLoginButtonId();
    String loginErrorMessageXPath = configReader.getLoginErrorMessageXPath();

    WebDriver driver;
    @Before
    public void iInitialiseTheChromeBrowser() {
        // initialise the driver
        //System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Ulisses.Dasilva\\UKTalentHubJava\\Drivers\\chromedriverV99.4.0\\chromedriver.exe");
        //WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Given("the user is on the trello login page")
    public void theUserIsOnTheTrelloLoginPage() {
        driver.get(trelloLoginPageUrl);
        Assert.assertEquals("https://trello.com/login", driver.getCurrentUrl());
    }

    @When("the user enters username {string} and password {string}")
    public void theUserEnterUsernameAndPassword(String login, String password) {
        driver.findElement(By.id(loginId)).sendKeys(login);
        driver.findElement(By.id(passwordId)).sendKeys(password);
    }

    @And("the user click on the login button")
    public void theUserClickOnTheLoginButton() {
        driver.findElement(By.id(loginButtonId)).click();
    }

    @Then("the user does not successfully login into the application homepage")
    public void theUserDoNotSuccessfullyLoginIntoTheApplicationHomepage() {
//        WebDriverWait wait = new WebDriverWait(driver, 5);
//        wait.until(ExpectedConditions
//                        .visibilityOfElementLocated(By.xpath(loginErrorMessageXPath)));
        System.out.println("test");

    }

    @After
    public void driverQuit() {
        driver.quit();
    }

}
