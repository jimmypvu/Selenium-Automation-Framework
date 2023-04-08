package org.jvu.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage{
//    public static By byCartQuantityTxt = By.cssSelector("input[id^='cart_quantity']");
    @FindBy(css = "input[id='cart_quantity50']")
    WebElement txtItemQuantity;
    @FindBy(css = "#cart_update")
    WebElement btnUpdateCart;
    @FindBy(css = "#cart_checkout1")
    WebElement btnCheckout;

    public CartPage (WebDriver givenDriver){
        super(givenDriver);
    }

    public CartPage updateQuantity(String num){
        txtItemQuantity.sendKeys(Keys.chord(Keys.CONTROL, "A", Keys.DELETE));
        txtItemQuantity.sendKeys(num);
        btnUpdateCart.click();
        return this;
    }

    public int getQuantity(){
        return Integer.parseInt(txtItemQuantity.getAttribute("value"));
    }

    public LoginPage clickCheckoutBtn(){
        btnCheckout.click();
        return new LoginPage(driver);
    }

    public CheckoutPage clickCheckout(){
        btnCheckout.click();
        return new CheckoutPage(driver);
    }


}
