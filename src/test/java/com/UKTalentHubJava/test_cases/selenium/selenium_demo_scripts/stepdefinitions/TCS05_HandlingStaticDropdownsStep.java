package com.UKTalentHubJava.test_cases.selenium.selenium_demo_scripts.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TCS05_HandlingStaticDropdownsStep {
    WebDriver driver;
    String url = "https://rahulshettyacademy.com/AutomationPractice/";
    List<WebElement> dropdowns;
    Select selectedDropdown;

    @Given("I have navigated to the web page for TC005")
    public void i_have_navigated_to_the_web_page_for_tc005(){
        // This sets the system property so selenium knows where to look to find the appropriate driver
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");

        // This creates the driver object that will be used throughout the test
        driver = new ChromeDriver();

        // This gets the given url and loads the webpage
        driver.get(url);
    }

    @Given("The webpage contains a static dropdown")
    public void the_webpage_contains_a_static_dropdown(){
        // The findElements method will return a list of all elements that match the given locator
        // In this case all elements that have the select tag (Only 1 in this case)
        dropdowns = driver.findElements(By.tagName("select"));
    }

    @When("I select an element from the dropdown")
    public void i_select_an_element_from_the_dropdown(){
        // When working with selects, we can save the Web Element as a Select object
        // This allows us to access to methods contained in the Select class
        selectedDropdown = new Select(dropdowns.get(0));

        // There a multiple ways of selecting options within the Select class
        // The first is selecting by index (0 indexed)
        selectedDropdown.selectByIndex(0);

        // The second was if by passing the value assigned to each option
        selectedDropdown.selectByValue("option1");

        // The third is selecting by visible text
        selectedDropdown.selectByVisibleText("Option2");
    }

    @Then("The option is selected and displayed")
    public void the_option_is_selected_and_displayed(){
        // We can save this value to see which value is saved and make assertions based on it
        String selectedOption = selectedDropdown.getFirstSelectedOption().getText();
        Assert.assertEquals("Option2", selectedOption);

        // The quit method then closes all the browsers opened by selenium
        driver.quit();
    }
}
