package com.UKTalentHubJava.test_cases.selenium.selenium_demo_scripts.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class TCS10_UsingACalendarStep {
    WebDriver driver;
    String url = "https://www.booking.com/index.en-gb.html";
    List<WebElement> dates;
    String dateToBook = LocalDate.now().plusDays(5).toString();

    @Given("I have navigated to the web page for TC010")
    public void i_have_navigated_to_the_web_page_for_tc010() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/Users/charlie.gilliland/Documents/Drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // These next lines are present to get rid of the cookies pop-up
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        Thread.sleep(1000);
    }

    @When("I click to open the calendar")
    public void i_click_to_open_the_calendar(){
        driver.findElement(By.cssSelector("div[data-calendar2-type='checkin']")).click();
    }

    @When("I enter the day required")
    public void i_enter_the_day_required(){
        dates = driver.findElements(By.cssSelector("td[data-bui-ref='calendar-date']"));
        boolean matchFound = false;
        for(WebElement date : dates){
            if(date.getAttribute("data-date").equalsIgnoreCase(dateToBook)){
                date.click();
                matchFound = true;
                break;
            }
        }
        Assert.assertTrue(matchFound);
    }

    @Then("The specific day is selected")
    public void the_specific_day_is_selected(){
        String selectedDate = driver.findElement(By.cssSelector("td.bui-calendar__date--selected")).getAttribute("data-date");
        Assert.assertEquals(dateToBook, selectedDate);

        driver.quit();
    }
}
