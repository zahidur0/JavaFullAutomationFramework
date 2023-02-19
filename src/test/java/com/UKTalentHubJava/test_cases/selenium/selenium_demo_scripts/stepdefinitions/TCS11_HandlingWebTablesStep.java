package com.UKTalentHubJava.test_cases.selenium.selenium_demo_scripts.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class TCS11_HandlingWebTablesStep {
    WebDriver driver;
    String url = "https://rahulshettyacademy.com/AutomationPractice/";
    List<WebElement> columns;
    List<WebElement> rows;
    WebElement specificRow;
    List<WebElement> specificRowData;

    @Given("I have navigated to the web page for TC011")
    public void i_have_navigated_to_the_web_page_for_tc011(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
    }

    @When("I locate all the columns in the table")
    public void i_locate_all_the_columns_in_the_table(){
        rows = driver.findElements(By.xpath("//*[@name='courses']/tbody/tr"));
        Assert.assertTrue(!rows.isEmpty());
    }

    @Then("I am able to get the number of columns")
    public void i_am_able_to_get_the_number_of_columns(){
        int numberOfRows = rows.size();
        System.out.println(numberOfRows);
    }

    @When("I locate all the rows in the table")
    public void i_locate_all_the_rows_in_the_table(){
        columns = driver.findElements(By.xpath("//*[@name='courses']/tbody/tr[1]/th"));
        Assert.assertTrue(!columns.isEmpty());
    }

    @Then("I am able to get the number of rows")
    public void i_am_able_to_get_the_number_of_rows(){
        int numberOfColumns = columns.size();
        System.out.println(numberOfColumns);
    }

    @When("I locate a specific row in the table")
    public void i_locate_a_specific_row_in_the_table(){
        specificRow = driver.findElement(By.xpath("(//*[@name='courses']/tbody/tr)[3]"));
    }

    @Then("I am able to get all the data for that row")
    public void i_am_able_to_get_all_the_data_from_that_row(){
        specificRowData = specificRow.findElements(By.tagName("td"));
        for(WebElement e : specificRowData){
            System.out.println(e.getText());
        }

        driver.quit();
    }
}
