package com.UKTalentHubJava.test_cases.selenium.selenium_demo_scripts.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TCS01_NavigateToPageStep {
    WebDriver driver;
    String url = "https://www.google.com/";

    @Given("I have set up the web driver")
    public void i_have_set_up_a_web_driver(){
        // This sets the system property so selenium knows where to look to find the appropriate driver
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");

        // This creates the driver object that will be used throughout the test
        driver = new ChromeDriver();
    }

    @When("I navigate to a correct url")
    public void i_navigate_to_the_url(){
        // The get method will load the given url, and will wait until the page has fully loaded before continuing the script
        driver.get(url);
        
        // Alternatively, navigate to will navigate to the page but not halt the script
        driver.navigate().to(url);
    }

    @Then("The correct page is displayed")
    public void the_correct_page_is_displayed(){
        // Finally, assertions can be made against the page being displayed

        // getCurrentUrl will retrieve the current url
        Assert.assertEquals(url, driver.getCurrentUrl());

        // getTitle will get the title of the webpage
        Assert.assertEquals("Google", driver.getTitle());
        driver.quit();
    }

}
