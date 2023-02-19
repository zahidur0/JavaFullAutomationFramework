package com.UKTalentHubJava.test_cases.selenium.selenium_demo_scripts.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class TCS18_TakingScreenshotsStep {
    WebDriver driver;
    String url = "https://savkk.github.io/selenium-practice/form/";
    File screenshot;
    boolean saveSuccessful;
    String pathToSaveScreenshot1 = System.getProperty("user.dir") + "\\screenshots\\screenshot1.png";
    String pathToSaveScreenshot2 = System.getProperty("user.dir") + "\\screenshots\\screenshot2.png";

    @Given("I have navigated to the web page for TC018")
    public void i_have_navigated_to_the_web_page_for_tc018(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
    }

    @When("I take a screenshot of the whole page")
    public void i_take_a_screenshot_of_the_whole_page(){
        screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        saveSuccessful = false;
        try {
            FileUtils.copyFile(screenshot, new File(pathToSaveScreenshot1));
            saveSuccessful = true;
        } catch (Exception e){
            saveSuccessful = false;
        }
    }

    @When("I take a screenshot of a specific element")
    public void i_take_a_screenshot_of_a_specific_element(){
        WebElement inputElement = driver.findElement(with(By.tagName("input"))
                .below(driver.findElement(By.xpath("//label[text()='First Name:']"))));
        screenshot = inputElement.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File(pathToSaveScreenshot2));
            saveSuccessful = true;
        } catch (Exception e) {
            saveSuccessful = false;
        }
    }

    @Then("A file in generated and saved to my machine")
    public void a_file_is_generated_and_saved_to_my_machine(){
        Assert.assertTrue(screenshot != null);
        Assert.assertTrue(saveSuccessful);

        driver.quit();
    }
}
