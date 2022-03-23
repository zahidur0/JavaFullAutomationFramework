package com.UKTalentHubJava.test_cases.selenium.selenium_demo_scripts.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class TCS16_UsingActionsStep {
    WebDriver driver;
    String url = "https://www.amazon.co.uk/";
    String textToSearch = "hello";
    WebElement accountDropdown;
    WebElement searchBar;
    WebElement searchButton;
    Actions simpleAction;
    Actions compositeAction;

    @Given("I have navigated to the web page for TC016")
    public void i_have_navigated_to_the_web_page_for_tc016(){
        System.setProperty("webdriver.chrome.driver", "C:/Users/charlie.gilliland/Documents/Drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);

        // This line closes the cookies popup so that we can interact with the page
        driver.findElement(By.name("accept")).click();
    }

    @When("I build and perform the action")
    public void i_have_built_an_action(){
        // Here we are locating the element to use in our action
        // We could also have defined it inside the action but this way we can validate using it later
        accountDropdown = driver.findElement(By.id("nav-link-accountList"));

        // We create a new Actions object passing it the driver object
        simpleAction = new Actions(driver);

        // We then add actions to it, such as here we are telling it to move the mouse to the accountDropdown element
        // Actions give you access to things that aren't available directly from the web page such as double click,
        // right click, keys down etc.
        simpleAction.moveToElement(accountDropdown).build().perform();

    }

    @When("I build and perform the composite action")
    public void i_build_and_perform_the_composite_action(){
        // Here we will interact with two elements the search bar and the search button
        searchBar = driver.findElement(By.id("twotabsearchtextbox"));
        searchButton = driver.findElement(By.id("nav-search-submit-button"));

        // Again, we create the Actions object passing it the driver
        compositeAction = new Actions(driver);

        // We can then chain together methods in side the action
        // This allows us to perform complex actions in a single line
        // By using the build method, we can store this action in a variable and call it as many times as required
        Action storedAction = compositeAction
                .keyDown(Keys.SHIFT)
                .click(searchBar)
                .sendKeys(textToSearch)
                .keyUp(Keys.SHIFT)
                .click(searchButton)
                .build();

        // Calling perform will execute the action
        storedAction.perform();
    }


    @Then("The effect is seen on the web page")
    public void the_effect_is_seen_on_the_web_page(){
        // Here we validate whether the dropdown is showing, which we know as the nav-active class is applied to it
        Assert.assertTrue(accountDropdown.getAttribute("class").contains("nav-active"));

        driver.quit();
    }

    @Then("The full action is performed")
    public void the_full_action_is_performed() throws InterruptedException {
        // We add a wait to give the page time to load the element we need to validate against
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Here we validate that the title of the page includes the string we are searching for
        Assert.assertTrue(driver.getCurrentUrl().contains(textToSearch.toUpperCase()));

        driver.quit();
    }
}
