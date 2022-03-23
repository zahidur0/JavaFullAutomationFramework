package com.UKTalentHubJava.test_cases.selenium.selenium_demo_scripts.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class TCS14_ImplicitWaitsStep {
    WebDriver driver;
    String url = "https://the-internet.herokuapp.com/dynamic_loading/2";

    @Given("I have supplied the correct setup to add an implicit wait")
    public void i_have_supplied_the_correct_setup_to_add_an_implicit_wait(){
        System.setProperty("webdriver.chrome.driver", "C:/Users/charlie.gilliland/Documents/Drivers/chromedriver.exe");
        driver = new ChromeDriver();

        // Implicit waits can be set with the following syntax
        // The method takes a Duration object, which can be of any unit of time from nanoseconds to days
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @When("I navigate to the web page for TC014")
    public void i_have_navigated_to_the_web_page_for_tc014(){
        // This will load the web page but not all elements will be visible once the page is loaded
        driver.navigate().to(url);
    }

    @Then("The driver will implicitly wait before the test fails")
    public void the_driver_will_implicitly_wait_before_the_test_fails(){
        // Clicking this element will load another after a given amount of time
        driver.findElement(By.xpath("//button[text()='Start']")).click();

        // Because of the implicit wait, selenium will wait the given time before failing this step
        WebElement loadedElement = driver.findElement(By.xpath("//h4[text()='Hello World!']"));

        // We can then assert that the element was loaded successfully
        Assert.assertTrue(loadedElement != null);

        driver.quit();

    }


}
