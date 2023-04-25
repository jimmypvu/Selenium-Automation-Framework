package jpvu.pages;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ItemPage extends BasePage {
    @FindBy(css = "h1.productname")
    public WebElement hdrProductName;
    @FindBy(css = "#product_quantity")
    public WebElement txtQuantity;
    @FindBy(css = "ul.productpagecart a")
    public WebElement btnAddToCart;
    @FindBy(css = "a[class=''wishlist_add btn btn-large']")
    public WebElement btnAddToWishlist;
    @FindBy(css = "a[class='wishlist_remove btn btn-large']")
    public WebElement btnRemoveFromWishlist;
    @FindBy(xpath = "//a[contains(text(),'Reviews')]")
    public WebElement tabReviews;
    @FindBy(css =".star-rating-control #rating4")
    public WebElement radStar;
    @FindBy(css = "#name")
    public WebElement txtNameOnReview;
    @FindBy(css = "#text")
    public WebElement txtReview;
    @FindBy(css = "input#captcha") //disable captcha for testing environment
    public WebElement txtCaptcha;

    public ItemPage (WebDriver givenDriver){
        super(givenDriver);
    }


}
