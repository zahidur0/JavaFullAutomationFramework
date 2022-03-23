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

public class TCS04_HandlingRadioButtonsAndCheckboxesStep {
    WebDriver driver;
    String url = "https://rahulshettyacademy.com/AutomationPractice/";
    List<WebElement> radioButtons;
    WebElement selectedRadioButton1;
    WebElement selectedRadioButton2;
    List<WebElement> checkboxes;
    WebElement selectedCheckbox;

    @Given("I have navigated to the web page for TC004")
    public void i_have_navigated_to_the_web_page_for_tc004(){
        // This sets the system property so selenium knows where to look to find the appropriate driver
        System.setProperty("webdriver.chrome.driver", "C:/Users/charlie.gilliland/Documents/Drivers/chromedriver.exe");

        // This creates the driver object that will be used throughout the test
        driver = new ChromeDriver();

        // This gets the given url and loads the webpage
        driver.get(url);
    }

    @Given("The webpage contains radio buttons")
    public void the_webpage_contains_radio_buttons(){
        // The findElements method will return a list of web elements that match the given locator
        // Here we use the input type of radio, which will return all radio buttons
        radioButtons = driver.findElements(By.cssSelector("input[type='radio']"));

        // AssertTrue is used to check if that list has elements, therefore the page contains radio buttons
        Assert.assertTrue(!radioButtons.isEmpty());
    }

    @Given("The webpage contains checkboxes")
    public void the_webpage_contains_checkboxes(){
        // Here we use input type of checkbox to return alla list of all the checkboxes
        checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));

        // Again we assert that this list is not empty
        Assert.assertTrue(!checkboxes.isEmpty());
    }

    @When("I select a radio button")
    public void i_select_a_radio_button(){
        // The get method will return the element at that index, in this case the first radio button
        selectedRadioButton1 = radioButtons.get(0);

        // The click method will perform a click on that element
        selectedRadioButton1.click();
    }

    @When("I select a checkbox")
    public void i_select_a_checkbox(){
        selectedCheckbox = checkboxes.get(0);
        selectedCheckbox.click();
    }

    @Then("The correct radio button is selected")
    public void the_correct_radio_button_is_selected(){
        // The isSelected will return a boolean value based on whether the radio button is selected or not
        boolean isSelected = selectedRadioButton1.isSelected();

        // We can then use assertions to check the result
        Assert.assertTrue(isSelected);
    }

    @Then("The checkbox is checked")
    public void the_checkbox_is_checked(){
        // Unlike radio buttons, the inEnabled method allows us to see whether a checkbox has been selected
        boolean isChecked = selectedCheckbox.isEnabled();

        // This also returns a boolean value on which we can make assertions
        Assert.assertTrue(isChecked);
    }

    @When("I select a different radio button")
    public void i_select_a_different_radio_button(){
        // We then select a different radio button
        selectedRadioButton2 = radioButtons.get(1);
        selectedRadioButton2.click();
    }

    @Then("The radio button selection is changed")
    public void the_radio_button_selection_is_changed(){
        // By selecting another radio button we need to check whether the first selection if unselected
        boolean isDeselected = selectedRadioButton1.isSelected();
        Assert.assertFalse(isDeselected);
        boolean isSelected = selectedRadioButton2.isSelected();
        Assert.assertTrue(isSelected);

        // The quit method will then close all the browser windows
        driver.quit();
    }


}
