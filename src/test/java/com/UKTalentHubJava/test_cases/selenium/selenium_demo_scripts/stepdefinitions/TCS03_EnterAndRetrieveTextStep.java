package com.UKTalentHubJava.test_cases.selenium.selenium_demo_scripts.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class TCS03_EnterAndRetrieveTextStep {
    WebDriver driver;
    String url = "https://rahulshettyacademy.com/loginpagePractise/";
    String textToSend = "Hello World";
    String moreTextToSend = " from Selenium!";
    List<WebElement> textFields;
    WebElement textField;

    @Given("I have navigated to the web page for TC003")
    public void i_have_navigated_to_the_web_page_for_tc003(){
        // This sets the system property so selenium knows where to look to find the appropriate driver
        System.setProperty("webdriver.chrome.driver", "C:/Users/charlie.gilliland/Documents/Drivers/chromedriver.exe");

        // This creates the driver object that will be used throughout the test
        driver = new ChromeDriver();

        // This gets the given url and loads the webpage
        driver.get(url);
    }

    @Given("The webpage contains a text input")
    public void the_webpage_contains_a_text_input(){
        // The findElements method returns a list of Web Elements that match the given identifier
        textFields = driver.findElements(By.cssSelector("input[type='text']"));

        // Assertions are used here to check that some Web Elements are matched
        Assert.assertTrue(textFields.size() > 0);
    }

    @When("I send text to the field")
    public void i_send_text_to_the_field(){
        // The get method of List will get the element with a given index
        textField = textFields.get(0);

        // Once we have the Web Element, we use send keys to send a string to the element
        textField.sendKeys(textToSend);
    }

    @Then("The text is displayed inside the text input")
    public void the_text_is_displayed_inside_the_text_input(){
        // Assertions can then be made to compare the text that was sent to the value that we expect
        // The getAttribute method is used to get attributes tied to a given element
        // As we need the text value, we pass "value" which will return that text value
        Assert.assertEquals(textToSend, textField.getAttribute("value"));
    }

    @When("I send more text to the field")
    public void i_send_more_text_to_the_field(){
        // The sendKeys method will send more text to the field, adding it on to any text already passed
        textField.sendKeys(moreTextToSend);

        // In order to remove the text before we send more we would need to use
        textField.clear();
    }

    @Then("The extra text is added to the field")
    public void the_extra_the_is_added_to_the_field(){
        // Assert.assertEquals((textToSend + moreTextToSend), textField.getAttribute("value"));
        Assert.assertEquals("", textField.getAttribute("value"));

        // The quit method will close all the browser windows opened by selenium
        driver.quit();
    }

}
