package com.UKTalentHubJava.test_cases.selenium.selenium_demo_scripts.Ecommerce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static com.UKTalentHubJava.test_cases.selenium.selenium_demo_scripts.Ecommerce.POMUtils.formatProductName;


public class ProductPage {

    WebDriver driver;
    WebDriverWait wait;
    By viewBasketButton = By.cssSelector("a.cart-icon");
    By checkoutButton = By.xpath("//button[text()='PROCEED TO CHECKOUT']");
    By productNames = By.cssSelector("h4.product-name");
    By addToCartButtons = By.xpath("//div[@class='product-action']/button[@type='button']");
    By productIncrementButtons = By.cssSelector("a.increment");
    By productDecrementButtons = By.cssSelector("a.decrement");
    By productsInCart = By.xpath("//*[@id='root']/div/header/div/div[3]/div[2]/div[1]/div[1]/ul/li");
    By removeProductFromCartButton = By.cssSelector("a.product-remove");

    public ProductPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public boolean checkIfNavigated(){
        try {
            wait.until(ExpectedConditions.urlToBe("https://rahulshettyacademy.com/seleniumPractise/#/"));
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public void clickViewBasket(){
        driver.findElement(viewBasketButton).click();
    }

    public void clickCheckoutButton(){
        driver.findElement(checkoutButton).click();
    }

    public boolean addItemToBasket(String productName){
        boolean productFound = false;
        List<WebElement> productNamesList = driver.findElements(productNames);
        List<WebElement> addToCartButtonsList = driver.findElements(addToCartButtons);
        for(int i=0;i<productNamesList.size();i++){
            String formattedName = productNamesList.get(i).getText().split("-")[0].trim();
            if(productName.equalsIgnoreCase(formattedName)){
                addToCartButtonsList.get(i).click();
                productFound = true;
                break;
            }
        }
        return productFound;
    }

    public void clickIncrementButton(String productName, int amountToAdd){
        List<WebElement> productNamesList = driver.findElements(productNames);
        List<WebElement> incrementButtonsList = driver.findElements(productIncrementButtons);
        for(int i=0;i<productNamesList.size();i++){
            String formattedName = productNamesList.get(i).getText().split("-")[0].trim();
            if(productName.equalsIgnoreCase(formattedName)){
                for(int j=0;j<amountToAdd;j++){
                    incrementButtonsList.get(i).click();
                }
                break;
            }
        }
    }

    public void clickDecrementButton(String productName, int amountToDecrease){
        List<WebElement> productNamesList = driver.findElements(productNames);
        List<WebElement> decrementButtonsList = driver.findElements(productDecrementButtons);
        for(int i=0;i<productNamesList.size();i++){
            String formattedName = formatProductName(productNamesList.get(i).getText());
            if(productName.equalsIgnoreCase(formattedName)){
                for(int j=0;j<amountToDecrease;j++){
                    decrementButtonsList.get(i).click();
                }
                break;
            }
        }
    }

    public boolean checkItemsInBasket(Object[][] itemsList){
        boolean match = true;
        List<WebElement> productsInCartList = driver.findElements(productsInCart);
        for(int i=0;i<itemsList.length;i++){
            String productName = formatProductName(productsInCartList.get(i).findElement(By.xpath("div[@class='product-info']/p[@class='product-name']")).getText());
            String productQuantity = productsInCartList.get(i).findElement(By.xpath("div[@class='product-total']/p[@class='quantity']")).getText();
            if(!productName.equalsIgnoreCase(itemsList[i][0].toString()) || productQuantity.equalsIgnoreCase(itemsList[i][1].toString())){
                match = false;
            }
        }
        return match;
    }

    public void removeItemFromBasket(String itemName){
        List<WebElement> productsInCartList = driver.findElements(productsInCart);
        for(int i=0;i<productsInCartList.size();i++){
            String productName = formatProductName(productsInCartList.get(i).findElement(By.xpath("div[@class='product-info']/p[@class='product-name']")).getText());
            if(productName.equalsIgnoreCase(itemName)){
                productsInCartList.get(i).findElement(removeProductFromCartButton).click();
            }
        }
    }



}
