package com.UKTalentHubJava.test_cases.selenium.selenium_demo_scripts.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TCS06_HandlingDynamicDropdownsStep {
    WebDriver driver;
    String url = "https://www.booking.com/flights/index.en-gb.html";
    String query = "BR";
    String desiredMatch = "BRS Bristol Airport";
    WebElement searchInput;
    List<WebElement> searchResults;
    WebElement displayedElement;
    WebDriverWait wait;
    @Given("I have navigated to the web page for TC006")
    public void i_have_navigated_to_the_web_page_for_tc006() throws InterruptedException {
        // This sets the system property so selenium knows where to look to find the appropriate driver
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");

        // This creates the driver object that will be used throughout the test
        driver = new ChromeDriver();

        // This gets the given url and loads the webpage
        driver.get(url);

        // This sets in implicit wait of the driver to 3 seconds
        // This accommodates for the time it takes for web elements to load
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        // These next two lines deal with the cookies pop-up that opens when the page is initially loaded
        driver.findElement(By.cssSelector("button#onetrust-accept-btn-handler")).click();
    }

    @Given("There is an input for the query")
    public void there_is_an_input_for_the_query() {
        // The findElement method is used to find the search element on the web page
        try {
            searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector(".css-1ovag24")));
        } catch (Exception e) {
            searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("div[aria-label='Where from?']")));
        }
        Assert.assertTrue(searchInput != null);
    }


    @When("I enter the query string")
    public void i_enter_the_query_string() throws InterruptedException {
        // The click method here gives focus to the text input
        searchInput.click();

        // This next click removes the default value that is loaded with the page
        try {
            driver.findElement(By.cssSelector(".css-i5im3r")).click();
        } catch (Exception e) {
            driver.findElement(
                    By.cssSelector("Icon-module__root___0jUKs css-lyj9ft Icon-module__root--size-small___AvlR0"))
                    .click();
        }

        // The sendKeys method will send the query into the input field
        driver.findElement(By.cssSelector("input[placeholder='Where from?']")).sendKeys(query);
    }

    @Then("The list is updated with elements that match the query")
    public void the_list_is_updated_with_elements_that_match_the_query() {
        // The findElements method here will get a list of those elements that are generated, based on our query
        searchResults = driver.findElements(By.cssSelector(".css-gn7u3i"));
    }

    @When("I select an option")
    public void i_select_an_option() {
        // Once we have all the elements that match, we can use a for loop to iterate through them
        // Once we have confirmed there is a match we can select that specific element and use the click method to select it
        boolean matchFound = false;
        for (WebElement e : searchResults) {
//            System.out.println(e.getText());
            System.out.println("isMatch");
            if (e.getText().equalsIgnoreCase(desiredMatch)) {
                e.click();
                matchFound = true;
                break;
            }
        }
        Assert.assertTrue(matchFound);
    }

    @Then("The option is displayed")
    public void the_option_is_displayed() {
        // The findElement method here is used to get the selected element
        displayedElement = driver.findElement(By.xpath("//div[@class='css-1ffxmmt']/div[text()='BRS Bristol Airport']"));

        // We can them use this to assert that the correct functionality is working
        Assert.assertEquals(desiredMatch, displayedElement.getText());

        // The quit method then closes all the opened browsers
        driver.quit();
    }
}
