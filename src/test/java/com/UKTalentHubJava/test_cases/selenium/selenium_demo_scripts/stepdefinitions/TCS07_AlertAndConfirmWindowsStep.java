package com.UKTalentHubJava.test_cases.selenium.selenium_demo_scripts.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TCS07_AlertAndConfirmWindowsStep {
    WebDriver driver;
    String url = "https://rahulshettyacademy.com/AutomationPractice/";

    // This helper method will determine is the alert window is opened
    public boolean isAlertPresent(){
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e){
            return false;
        }
    }

    @Given("I have navigated to the web page for TC007")
    public void i_have_navigated_to_the_web_page_for_tc007(){
        // This sets the system property so selenium knows where to look to find the appropriate driver
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");

        // This creates the driver object that will be used throughout the test
        driver = new ChromeDriver();

        // This gets the given url and loads the webpage
        driver.get(url);
    }

    @Given("I have clicked on a link that opens an alert window")
    public void i_have_clicked_on_a_link_that_opens_an_alert_window(){
        // Here the findElement and click methods are chained find a link and click to open an alrt window
        driver.findElement(By.id("alertbtn")).click();
        boolean alertOpen = isAlertPresent();
        Assert.assertTrue(alertOpen);
    }

    @Given("I have clicked on a link that opens a confirm window")
    public void i_have_clicked_on_a_link_that_opens_a_confirm_window(){
        driver.findElement(By.id("confirmbtn")).click();
        boolean alertOpen = isAlertPresent();
        Assert.assertTrue(alertOpen);
    }

    @When("I select OK on the alert window")
    public void i_select_ok_on_the_alert_window(){
        // In order to interact with an alert window we must use the switchTo method chained with the alert method
        // We then get access to methods concerning the opened window
        driver.switchTo().alert().accept();
    }

    @When("I select yes on the confirm window")
    public void i_select_yes_on_the_confirm_window(){
        // Accept is the positive response to an alert window
        // This means either OK, yes or any other positive response
        driver.switchTo().alert().accept();
    }

    @When("I select no on the confirm window")
    public void i_select_no_on_the_confirm_window(){
        // Dismiss is the negative response, in this case no
        driver.switchTo().alert().dismiss();
    }

    @Then("The window is closed")
    public void the_alert_window_is_closed(){
        boolean alertOpen = isAlertPresent();
        Assert.assertFalse(alertOpen);

        // This method then closes all the currently opened windows
        driver.quit();
    }
}
