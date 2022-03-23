package com.UKTalentHubJava.test_cases.selenium.selenium_demo_scripts.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class TCS13_RelativeLocatorsStep {
    WebDriver driver;
    String url = "https://savkk.github.io/selenium-practice/form/";
    WebElement explicitlyLocatedNameLabel;
    WebElement explicitlyLocatedRadioButton;
    WebElement relativelyLocatedNameInput;
    WebElement relativelyLocatedRadioButton;
    String textToSend = "Test";

    @Given("I have navigated to the web page for TC013")
    public void i_have_navigated_to_the_web_page_for_tc013(){
        System.setProperty("webdriver.chrome.driver", "C:/Users/charlie.gilliland/Documents/Drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
    }

    @When("I locate an element on the page")
    public void i_locate_an_element_on_the_page(){
        // Here we are setting up web elements whose positions we can use to get other web elements surrounding them
        // This will grab the "First Name:" label at the top of the form
        explicitlyLocatedNameLabel = driver.findElement(By.xpath("//label[text()='First Name:']"));

        // This will grab the "Male" radio button
        explicitlyLocatedRadioButton = driver.findElement(By.name("sex"));
    }

    @When("I select another element based on its relative position")
    public void i_select_another_element_based_on_its_relative_position(){
        // Here we use the relative locator method below to grab the web element immediately beneath the given element
        // Currently, these locators only work using with(By.tagName()) and will look for the element with that tag
        // This line will grab the input element of first name
        relativelyLocatedNameInput = driver.findElement(with(By.tagName("input")).below(explicitlyLocatedNameLabel));

        // Here we use the toRightOf method to grab the radio button immediately to the right of "Male"
        relativelyLocatedRadioButton = driver.findElement(with(By.tagName("input")).toRightOf(explicitlyLocatedRadioButton));
    }

    @Then("I am able to interact with that element")
    public void i_am_able_to_interact_with_that_element(){
        // Here we are sending text to the element so that we can ensure it has selected the required element
        relativelyLocatedNameInput.sendKeys(textToSend);

        // Here again, we are clicking the radio button to check that we have selected the correct element
        relativelyLocatedRadioButton.click();

        // These next two elements are for validation only and such are local to this method
        WebElement explicitlyLocatedNameInput = driver.findElement(By.xpath("//*[@id='testform']/div[1]/input"));
        WebElement explicitlyLocatedRadioButton2 = driver.findElement(By.xpath("//*[@id='testform']/div[4]/input[2]"));

        // We can then use the explicitly located references to check that our relative locators have acted
        // on the correct elements
        Assert.assertEquals(textToSend, explicitlyLocatedNameInput.getAttribute("value"));
        Assert.assertTrue(explicitlyLocatedRadioButton2.isSelected());

        driver.quit();
    }
}
