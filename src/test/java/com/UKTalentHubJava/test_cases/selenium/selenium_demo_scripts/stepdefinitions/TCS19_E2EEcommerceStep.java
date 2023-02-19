package com.UKTalentHubJava.test_cases.selenium.selenium_demo_scripts.stepdefinitions;

import com.UKTalentHubJava.test_cases.selenium.selenium_demo_scripts.Ecommerce.CartPage;
import com.UKTalentHubJava.test_cases.selenium.selenium_demo_scripts.Ecommerce.CountryPage;
import com.UKTalentHubJava.test_cases.selenium.selenium_demo_scripts.Ecommerce.ProductPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TCS19_E2EEcommerceStep {
    WebDriver driver;
    WebDriverWait wait;
    String url = "https://rahulshettyacademy.com/seleniumPractise/#/";
    ProductPage productPage;
    CartPage cartPage;
    CountryPage countryPage;
    Object[][] productsToAdd = new Object[][]{
            {"Brocolli", 10},
            {"Cucumber", 5},
            {"Carrot", 2}
    };
    String productToRemove = productsToAdd[0][0].toString();
    String correctPromoCode = "rahulshettyacademy";
    String incorrectPromoCode = "incorrect";

    @Given("I have navigated to the web page for TC019")
    public void i_void_navigated_to_the_web_page_for_tc019(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Given("I have created all the POM objects")
    public void i_have_created_all_the_pom_objects(){
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        countryPage = new CountryPage(driver);
    }

    @When("I add items to my cart")
    public void i_add_items_to_my_cart(){
        for(int i=0;i<productsToAdd.length;i++){
            String productName = (String)productsToAdd[i][0];
            int amountToAdd = ((Integer)productsToAdd[i][1])-1;
            productPage.clickIncrementButton(productName, amountToAdd);
            productPage.addItemToBasket(productName);
        }
    }

    @Then("The items are displayed in my basket")
    public void the_items_are_displayed_in_my_basket(){
        productPage.clickViewBasket();
        Assert.assertTrue(productPage.checkItemsInBasket(productsToAdd));
    }

    @When("I remove an item from my basket")
    public void i_remove_an_item_from_my_basket(){
        productPage.removeItemFromBasket(productToRemove);
        removeItemFromList(productToRemove);
    }

    @Then("The item is removed from the basket")
    public void the_item_is_removed_from_the_basket(){
        productPage.checkItemsInBasket(productsToAdd);
    }

    @When("I go to checkout")
    public void i_go_to_checkout(){
        productPage.clickCheckoutButton();
    }

    @Then("I am redirected to the cart page")
    public void i_am_redirected_to_the_cart_page(){
        Assert.assertTrue(cartPage.checkIfNavigated());
    }

    @Then("I am able to see a breakdown of all items in my basket")
    public void i_am_able_see_a_breakdown_of_all_items_in_my_basket(){
        Assert.assertTrue(cartPage.checkItemsInBasket(productsToAdd));
    }

    @When("I enter a correct promo code")
    public void i_enter_a_correct_promo_code(){
        cartPage.enterPromoCode(correctPromoCode);
        cartPage.applyPromoCode();
    }

    @When("I enter an incorrect promo code")
    public void i_enter_an_incorrect_promo_code(){
        cartPage.enterPromoCode(incorrectPromoCode);
        cartPage.applyPromoCode();
    }

    @Then("I see a message to say my coupon has been applied")
    public void i_see_a_message_to_say_my_coupon_has_been_applied(){
        Assert.assertTrue(cartPage.checkPromoCode());
    }

    @Then("I see a message to say my coupon has not been applied")
    public void i_see_a_message_to_say_my_coupon_has_not_been_applied(){
        Assert.assertFalse(cartPage.checkPromoCode());
    }

    @When("I click the place order button")
    public void i_click_the_place_order_button(){
        cartPage.clickPlaceOrderButton();
    }

    @Then("I am redirected to the country page")
    public void i_am_redirected_to_the_country_page(){
        Assert.assertTrue(countryPage.checkIfNavigated());
    }

    @When("I enter a country")
    public void i_enter_a_country(){
        countryPage.selectCountry("United Kingdom");
    }

    @When("I agree to the terms and conditions")
    public void i_agree_to_the_terms_and_conditions(){
        countryPage.checkTandC();
    }

    @When("I click proceed")
    public void i_click_proceed(){
        countryPage.clickProceed();
    }

    @Then("I am redirected to the product page")
    public void i_am_redirected_to_the_product_page(){
        Assert.assertTrue(productPage.checkIfNavigated());
        driver.quit();
    }

    public void removeItemFromList(String itemToRemove){
        Object[][] alteredArr;
        List<Object[]> rowsToKeep = new ArrayList<>();
        for(Object[] row : productsToAdd){
            boolean found = false;
            for(Object testValue : row){
                if(testValue.toString().equalsIgnoreCase(itemToRemove)){
                    found = true;
                    break;
                }
            }
            if(!found){
                rowsToKeep.add(row);
            }
            alteredArr = new Object[rowsToKeep.size()][];
            for(int i=0; i< rowsToKeep.size();i++){
                alteredArr[i] = rowsToKeep.get(i);
            }
            productsToAdd = alteredArr;
        }
    }

}
