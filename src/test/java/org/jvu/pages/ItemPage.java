package org.jvu.pages;

import org.framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ItemPage extends BasePage {
    @FindBy(css = "h1.productname")
    WebElement hdrProductName;
    @FindBy(css = "#product_quantity")
    WebElement txtQuantity;
    @FindBy(css = "ul.productpagecart a")
    WebElement btnAddToCart;
    @FindBy(css = "a[class=''wishlist_add btn btn-large']")
    WebElement btnAddToWishlist;
    @FindBy(css = "a[class='wishlist_remove btn btn-large']")
    WebElement btnRemoveFromWishlist;
    @FindBy(xpath = "//a[contains(text(),'Reviews')]")
    WebElement tabReviews;
    @FindBy(css =".star-rating-control #rating4")
    WebElement rdoStar;
    @FindBy(css = "#name")
    WebElement txtNameOnReview;
    @FindBy(css = "#text")
    WebElement txtReview;
    @FindBy(css = "input#captcha") //disable captcha for testing environment
    WebElement txtCaptcha;
    public static By byProductNameHdr = By.cssSelector("h1.productname");

    public ItemPage (WebDriver givenDriver){
        super(givenDriver);
    }


}
