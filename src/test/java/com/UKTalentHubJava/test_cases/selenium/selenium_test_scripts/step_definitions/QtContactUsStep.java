package com.UKTalentHubJava.test_cases.selenium.selenium_test_scripts.step_definitions;

import com.UKTalentHubJava.screenshot_taker.ScreenshotTaker;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import com.UKTalentHubJava.utilities.ReadConfig;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.util.List;

public class QtContactUsStep {
    WebDriver driver;
    ReadConfig configReader = new ReadConfig();
    String qualitestMainUrl = configReader.getQualitestMainUrl();
    String driverPath = configReader.getDriverPath();
    String contactUsButtonPath = configReader.getContactUsButtonPath();
    String firstNameId = configReader.getFirstNameId();
    String lastNameId = configReader.getLastNameId();
    String emailId = configReader.getEmailId();
    String phoneId = configReader.phoneId();
    String companyNameId = configReader.getCompanyNameId();
    String radioButtonsId = configReader.getRadioButtonsId();
    String helpTextboxId = configReader.getHelpTextboxId();
    String locationId = configReader.getLocationId();


    @Before("@qt-contact-us")
    public void iInitialiseTheChromeBrowser() {
        //System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
        System.setProperty("webdriver.chrome.driver",
                System.getProperty("user.dir") + "\\Drivers\\chromedriverV99.4.0\\chromedriver.exe");
        //WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Given("the user loads Qualitest official site")
    public void theUserLoadsQualitestOfficialSite() {
        driver.get(qualitestMainUrl);
        // maximise the window
        driver.manage().window().maximize();
    }

    @And("the page url is {string}")
    public void thePageUrlIs(String qualitestMainUrl) {
        Assert.assertEquals(driver.getCurrentUrl(), qualitestMainUrl);
    }

    @Then("the page states {string}")
    public void thePageStatesTheWorldSLeadingAILedQualityEngineeringCompanyQualitest(String mainPageTitle) {
        // check if the tab title matches the expected tab title
        System.out.println("==================================================================");
        System.out.println(driver.getTitle());
        System.out.println(mainPageTitle);
        Assert.assertEquals(mainPageTitle, driver.getTitle());
    }

    @Given("the user is on Qualitest site")
    public void theUserIsOnQualitestSite() {
        driver.get(qualitestMainUrl);
        // maximise the window
        driver.manage().window().maximize();
    }

    @When("the user clicks on Contact us button")
    public void theUserClicksOnContactUsButton() {
        driver.findElement(By.cssSelector(contactUsButtonPath)).click();
    }

    @Then("the user is able to access the Qualitest contact us page")
    public void theUserIsAbleToAccessTheQualitestContactUsPage() {
        Assert.assertEquals("https://qualitestgroup.com/contact-us/", driver.getCurrentUrl());
    }

    @And("the page contains a form for the user")
    public void thePageContainsAFormForTheUser() {
        List<WebElement> formList = driver.findElements(By.tagName("form"));
        System.out.println("Number of form tags: " + formList.size());
        Assert.assertTrue(formList.size() > 0);
    }

    @When("the user enters first name")
    public void theUserEntersFirstName() throws InterruptedException {
        driver.findElement(By.id(firstNameId)).sendKeys("Mike");
    }

    @And("the user enters last name")
    public void theUserEntersLastName() throws InterruptedException {
        driver.findElement(By.id(lastNameId)).sendKeys("Rowland");
    }

    @And("the user enters company name")
    public void theUserEntersCompanyName() throws InterruptedException {
        driver.findElement(By.id(companyNameId)).sendKeys("Definitely A Real Company");
    }

    @And("the user selects what they want to talk about")
    public void theUserSelectsWhatTheyWantToTalkAbout() {
        List<WebElement> radioButtons = driver.findElements(By.cssSelector(radioButtonsId));
        Assert.assertFalse(radioButtons.isEmpty());
        WebElement radioButton = radioButtons.get(1);
        radioButton.click();
    }

    @And("the user enters email")
    public void theUserEntersEmail() throws InterruptedException {
        driver.findElement(By.id(emailId)).sendKeys("mikerowland@email.com");
    }

    @And("the user enters phone number")
    public void theUserEntersPhoneNumber() throws InterruptedException {
        driver.findElement(By.id(phoneId)).sendKeys("12345678901");
    }

    @And("the user enters location")
    public void theUserEntersLocation() {
        WebElement dropdown = driver.findElement(By.id(locationId));
        Select options = new Select(dropdown);
        options.selectByVisibleText("UK & Europe");
    }

    @And("the user fills how can we help section")
    public void theUserFillsHowCanWeHelpSection() {

        driver.findElement(By.id(helpTextboxId)).sendKeys("help");
    }

    @And("the user clicks on Submit button")
    public void theUserClicksOnSubmitButton() {
    }

    @Then("the user receives a thank you message")
    public void theUserReceivesAThankYouMessage() {

    }

    @AfterStep("@qt-contact-us")
    public void screenShot() throws IOException {
        new ScreenshotTaker(driver);
    }

    @After("@qt-contact-us")
    public void driverQuit() {
        driver.quit();
    }

}
