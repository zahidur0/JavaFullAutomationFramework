package com.UKTalentHubJava.test_cases.selenium.selenium_demo_scripts.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class TCS17_HandlingMultipleTabsStep {
    WebDriver driver;
    String url1 = "https://savkk.github.io/selenium-practice/form/";
    String url2 = "https://en.wikipedia.org/wiki/Aloeides_caledoni";
    String url3 = "https://en.wikipedia.org/wiki/Kostelec_(Hodon%C3%ADn_District)";
    String tab1Handle;
    String tab2Handle;
    String tab3Handle;
    String textFromTab2;
    String textFromTab3;

    @Given("I have navigated to a form")
    public void i_have_navigated_to_a_form(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url1);
    }

    @Given("I have opened two extra tabs")
    public void i_have_opened_two_extra_tabs(){
        driver.switchTo().newWindow(WindowType.TAB);
        driver.switchTo().newWindow(WindowType.TAB);
    }

    @Given("I have navigated to two separate pages on the other tabs")
    public void i_have_navigated_to_two_separate_pages_on_the_other_tabs(){
        Iterator<String> handles = driver.getWindowHandles().iterator();
        tab1Handle = handles.next();
        tab2Handle = handles.next();
        tab3Handle = handles.next();
        driver.switchTo().window(tab2Handle).get(url2);
        driver.switchTo().window(tab3Handle).get(url3);
    }

    @When("I grab the text from the second tab")
    public void i_grab_the_text_from_the_second_tab(){
        driver.switchTo().window(tab2Handle);
        textFromTab2 = driver.findElement(By.id("firstHeading")).getText();
    }

    @Then("I am able to insert it into the first name input in the first tab")
    public void i_am_able_to_insert_it_into_the_first_name_input_in_the_first_tab(){
        driver.switchTo().window(tab1Handle);
        driver.findElement(with(By.tagName("input"))
                .below(driver.findElement(By.xpath("//label[text()='First Name:']"))))
                .sendKeys(textFromTab2);
    }

    @When("I grab the text from the third tab")
    public void i_grab_the_text_from_the_third_tab(){
        driver.switchTo().window(tab3Handle);
        textFromTab3 = driver.findElement(By.id("firstHeading")).getText();
    }

    @Then("I am able to insert it into the last name input in the first tab")
    public void i_am_able_to_insert_it_into_the_last_name_input_in_the_first_tab(){
        driver.switchTo().window(tab1Handle);
        driver.findElement(with(By.tagName("input"))
                        .below(driver.findElement(By.xpath("//label[text()='Last Name:']"))))
                        .sendKeys(textFromTab3);

        driver.quit();
    }



}
