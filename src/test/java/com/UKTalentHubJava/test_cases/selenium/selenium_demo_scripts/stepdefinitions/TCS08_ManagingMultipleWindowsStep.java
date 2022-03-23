package com.UKTalentHubJava.test_cases.selenium.selenium_demo_scripts.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.Set;

public class TCS08_ManagingMultipleWindowsStep {
    WebDriver driver;
    String url = "https://the-internet.herokuapp.com/windows";
    Set<String> windowHandles;
    String parentHandle;
    String childHandle;
    WebElement childPageElement;

    @Given("I have navigated to the web page for TC008")
    public void i_have_navigated_to_the_web_page_for_tc008(){
        // This sets the system property so selenium knows where to look to find the appropriate driver
        System.setProperty("webdriver.chrome.driver", "C:/Users/charlie.gilliland/Documents/Drivers/chromedriver.exe");

        // This creates the driver object that will be used throughout the test
        driver = new ChromeDriver();

        // This gets the given url and loads the webpage
        driver.get(url);
    }

    @When("I click on a link to open a new window")
    public void i_click_on_a_link_to_open_a_new_window(){
        // Here findElement returns a button that we click to open a new window
        driver.findElement(By.cssSelector("div.example>a")).click();

        // The getWindowHandles method will return the handles for each of the windows in the form of a Set<String>
        windowHandles = driver.getWindowHandles();

        // the iterator method of set converts the set into Iterator
        Iterator<String> handlesIterator = windowHandles.iterator();

        // Using the iterator we can assign the parent and child handles to variables
        parentHandle = handlesIterator.next();
        childHandle = handlesIterator.next();

        // It allows us to assert on how many windows there should be
        Assert.assertTrue(windowHandles.size() == 2);
    }

    @Then("I am able to navigate to the new window")
    public void i_am_able_to_navigate_to_the_new_window(){
        // The switchTo method defines that the focus is being shifted
        // The window method defines that we are switching to another window
        // We do this be passing the window handle as the argument
        driver.switchTo().window(childHandle);
    }

    @Then("I am able to access elements within the new window")
    public void i_am_able_to_access_element_within_the_new_windows(){
        // Once the focus has been shifted any attempt to access elements will only work in the new window
        childPageElement = driver.findElement(By.tagName("h3"));
        Assert.assertEquals("New Window", childPageElement.getText());
    }

    @Then("I am able to close the child window")
    public void i_am_able_to_close_the_child_window(){
        // Calling close will close the window that currently has focus
        // In this case it is the child window
        driver.close();
    }

    @Then("I am able to navigate back to the original window")
    public void i_am_able_to_navigate_back_to_the_original_window(){
        // Once again using switchTo we can go back to the parent window
        driver.switchTo().window(parentHandle);
        Assert.assertEquals(url, driver.getCurrentUrl());

        // The quit method then closes all browsers
        driver.quit();
    }




}
