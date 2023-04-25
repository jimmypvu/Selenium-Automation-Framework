package jpvu.pages;

import framework.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {
    @FindBy(css = "input[id='cart_quantity50']")
    public WebElement txtItemQuantity;
    @FindBy(css = "#cart_update")
    public WebElement btnUpdateCart;
    @FindBy(css = "#cart_checkout1")
    public WebElement btnCheckout;

    public CartPage (WebDriver givenDriver){
        super(givenDriver);
    }

    public CartPage updateQuantity(int qt){
//        txtItemQuantity.sendKeys(Keys.chord(Keys.CONTROL, "A", Keys.DELETE));
        txtItemQuantity.clear();
        txtItemQuantity.sendKeys(String.valueOf(qt));
        btnUpdateCart.click();
        return this;
    }

    public int getQuantity(){
        pause(500);
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
