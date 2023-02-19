package com.UKTalentHubJava.test_cases.selenium.selenium_demo_scripts.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TCS15_ExplicitWaitsStep {
    WebDriver driver;
    String url = "https://the-internet.herokuapp.com/dynamic_loading/2";
    WebDriverWait wait;

    @Given("I have created an explicit wait")
    public void i_have_created_an_explicit_wait(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();

        // Here we create a WebDriverWait object, passing it the driver object and the Duration of time it should wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @When("I navigate to the web page for TC015")
    public void i_navigate_to_the_web_page_for_tc015(){
        driver.get(url);
    }

    @Then("The explicit wait will stop execution until the condition is met")
    public void the_explicit_wait_will_stop_execution_until_the_condition_is_met(){
        // Clicking this element will load another after a given amount of time
        driver.findElement(By.xpath("//button[text()='Start']")).click();

        // We have to tell selenium when to use this wait using the until method
        // The ExpectedConiditons class gives us access to numerous conditions that will need to be met before the
        // script will continue
        // Here we are waiting until the element is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='Hello World!']")));

        // We can then assign the web element and make assertions that it has been located correctly
        WebElement loadedElement = driver.findElement(By.xpath("//h4[text()='Hello World!']"));
        Assert.assertTrue(loadedElement != null);

        driver.quit();
    }
}
