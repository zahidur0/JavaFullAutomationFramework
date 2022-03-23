package com.UKTalentHubJava.test_cases.selenium.selenium_demo_scripts.Ecommerce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CountryPage {
    WebDriver driver;
    By countrySelect = By.tagName("select");
    By tCCheckbox = By.cssSelector("input.chkAgree");
    By proceedButton = By.xpath("//button[text()='Proceed']");
    By orderCompleteMessage = By.xpath("//span[text()='Thank you, your order has been placed successfully\n" +
            "You'll be redirected to Home page shortly!!']");
    WebDriverWait wait;

    public CountryPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public boolean checkIfNavigated(){
        try {
            wait.until(ExpectedConditions.urlToBe("https://rahulshettyacademy.com/seleniumPractise/#/country"));
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public void selectCountry(String country){
        Select countries = new Select(driver.findElement(countrySelect));
        countries.selectByVisibleText(country);
    }

    public void checkTandC(){
        driver.findElement(tCCheckbox).click();
    }

    public void clickProceed(){
        driver.findElement(proceedButton).click();
    }

    public boolean checkForMessage(){
        WebElement message = driver.findElement(By.xpath("/div/div/div/div/span"));
        return true;
//        try {
//            wait.until(ExpectedConditions.elementToBeClickable(orderCompleteMessage));
//            return true;
//        } catch (Exception e){
//            return false;
//        }
    }
}
