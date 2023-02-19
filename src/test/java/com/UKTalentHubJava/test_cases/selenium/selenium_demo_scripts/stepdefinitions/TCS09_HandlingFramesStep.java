package com.UKTalentHubJava.test_cases.selenium.selenium_demo_scripts.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TCS09_HandlingFramesStep {
    WebDriver driver;
    String url = "https://the-internet.herokuapp.com/nested_frames";

    @Given("I have navigated to the web page for TC009")
    public void i_have_navigated_to_the_web_page_for_tc009(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
    }

    @When("I switch focus to the middle frame")
    public void i_switch_focus_to_the_middle_frame(){
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-middle");
    }

    @Then("I am able to access the text displayed in middle")
    public void i_am_able_to_access_the_text_displayed_in_middle(){
        String content = driver.findElement(By.id("content")).getText();
        Assert.assertEquals("MIDDLE", content);
    }

    @When("I switch focus to the bottom frame")
    public void i_switch_focus_to_the_bottom_frame(){
        driver.switchTo().defaultContent();
        driver.switchTo().frame("frame-bottom");
    }

    @Then("I am able to access the text displayed in bottom")
    public void i_am_able_to_access_the_text_displayed_in_bottom(){
        String content = driver.findElement(By.tagName("body")).getText();
        Assert.assertEquals("BOTTOM", content);

        driver.quit();
    }

}
