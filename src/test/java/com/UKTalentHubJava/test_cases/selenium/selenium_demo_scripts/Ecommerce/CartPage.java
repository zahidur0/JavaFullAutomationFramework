package com.UKTalentHubJava.test_cases.selenium.selenium_demo_scripts.Ecommerce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static com.UKTalentHubJava.test_cases.selenium.selenium_demo_scripts.Ecommerce.POMUtils.formatProductName;


public class CartPage {
    WebDriver driver;
    By productCartItems = By.cssSelector("#productCartTables>tbody>tr");
    By promoCodeInput = By.cssSelector("input.promoCode");
    By promoCodeButton = By.cssSelector("button.promoBtn");
    By promoCodeInfo = By.cssSelector("span.promoInfo");
    By placeOrderButton = By.xpath("//button[text()='Place Order']");
    WebDriverWait wait;

    public CartPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public boolean checkIfNavigated(){
        try {
            wait.until(ExpectedConditions.urlToBe("https://rahulshettyacademy.com/seleniumPractise/#/cart"));
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public boolean checkItemsInBasket(Object[][] itemsList){
        List<WebElement> productsInBasket = driver.findElements(productCartItems);
        boolean match = true;
        for(int i=0;i<itemsList.length;i++){
            String productName = formatProductName(productsInBasket.get(i).findElement(By.cssSelector("td>p.product-name")).getText());
            String quantity = productsInBasket.get(i).findElement(By.cssSelector("td>p.quantity")).getText();
            if(!itemsList[i][0].toString().equalsIgnoreCase(productName) || !itemsList[i][1].toString().equalsIgnoreCase(quantity)){
                match = false;
            }
        }
        return match;
    }

    public void enterPromoCode(String code){
        driver.findElement(promoCodeInput).sendKeys(code);
    }

    public void applyPromoCode(){
        driver.findElement(promoCodeButton).click();
    }

    public boolean checkPromoCode(){
        boolean hasWorked = false;
        wait.until(ExpectedConditions.visibilityOfElementLocated(promoCodeInfo));
        WebElement promoInfo = driver.findElement(promoCodeInfo);
        if(promoInfo.getText().equalsIgnoreCase("Code applied ..!")){
            hasWorked = true;
        }
        return hasWorked;
    }

    public void clickPlaceOrderButton(){
        driver.findElement(placeOrderButton).click();
    }
}
