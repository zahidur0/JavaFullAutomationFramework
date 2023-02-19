package com.UKTalentHubJava.test_cases.selenium.selenium_demo_scripts.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TCS02_ClickAndNavigateStep {
    WebDriver driver;
    String url = "https://the-internet.herokuapp.com/";

    @Given("I have navigated to the web page for TC002")
    public void i_have_navigated_to_the_web_page_for_tc002(){
        // This sets the system property so selenium knows where to look to find the appropriate driver
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");

        // This creates the driver object that will be used throughout the test
        driver = new ChromeDriver();

        // This gets the given url and loads the webpage
        driver.get(url);

        // Assertions can be made on the page to ensure we have navigated properly
        Assert.assertEquals("https://the-internet.herokuapp.com/", driver.getCurrentUrl());
    }

    @When("I click on a link")
    public void i_click_on_a_link(){
        // findElement will return a Web Element on the current page/frame with focus
        WebElement button = driver.findElement(By.xpath("//a[text()='Add/Remove Elements']"));

        // The click method will then perform a click on that element
        button.click();

        // These lines could be joined as such
        // driver.findElement(By.xpath("//a[text()='Add/Remove Elements']")).click();
    }

    @Then("I am navigated to another web page")
    public void i_am_navigated_to_another_web_page(){
        Assert.assertTrue(!driver.getCurrentUrl().equals(url));

        // The quit method will stop the given driver
        driver.quit();

        // We could also use close but this will only close the current browser
        // driver.close();
    }

}
