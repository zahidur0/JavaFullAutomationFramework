package com.UKTalentHubJava.test_cases.selenium.selenium_demo_scripts.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TCS12_ScrollingStep {
    WebDriver driver;
    String url = "https://the-internet.herokuapp.com/";
    JavascriptExecutor js;
    int amountToScroll;
    long offSet;

    @Given("I have navigated to the web page for TC012")
    public void i_have_navigated_to_the_web_page_for_tc012(){
        System.setProperty("webdriver.chrome.driver", "C:/Users/charlie.gilliland/Documents/Drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
    }

    @When("I scroll the page down a specific amount")
    public void i_scroll_the_page_down_a_specific_amount(){
        // In order to scroll the browser we first need to cast the driver as a JavascriptExecutor
        // This allows us to execute scripts directly on the browser
        js = (JavascriptExecutor)driver;

        // Here we execute a script, calling the JavaScript method scrollBy passing a value to scroll the y-axis
        js.executeScript("window.scrollBy(0," + amountToScroll + ")");
    }

    @Then("The offset of the window has changed by the specific amount")
    public void the_offset_of_the_window_has_changed_by_the_specific_amount(){
        // In order to validate that the scroll has been successful we call another JavaScript method
        // Here we get the offset of the page using the window property pageYOffset
        offSet = (long) js.executeScript("return window.pageYOffset;");

        // We can then use this value to assert that the correct amount of scrolling has taken place
        Assert.assertEquals(amountToScroll, offSet);

        driver.quit();
    }
}
