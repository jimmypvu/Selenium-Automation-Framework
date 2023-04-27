package jpvu.pages;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomePage extends BasePage {
    @FindBy(xpath = "//a[contains(text(), 'Login or register')]")
    public WebElement btnLoginRegister;
    @FindBy(xpath = "//a[@class='active menu_home']")
    public WebElement btnHome;
    @FindBy(xpath = "//ul[@class='nav-pills categorymenu']//a[contains(text(),'Apparel & accessories')]")
    public WebElement lnkApparel;
    @FindBy(xpath = "//ul[@class='nav-pills categorymenu']//a[contains(text(),'Makeup')]")
    public WebElement lnkMakeup;
    @FindBy(xpath = "//a[@href='https://automationteststore.com/index.php?rt=product/category&path=43']")
    public WebElement lnkSkincare;
    @FindBy(xpath = "//a[@href='https://automationteststore.com/index.php?rt=product/category&path=49']")
    public WebElement lnkFragrance;
    @FindBy(xpath = "//a[@href='https://automationteststore.com/index.php?rt=product/category&path=58']")
    public WebElement lnkMens;
    @FindBy(xpath = "//nav[@class='subnav']//a[contains(text(),'Hair Care')]")
    public WebElement lnkHaircare;
    @FindBy(css = ".nav-pills li:nth-child(8)>a")
    public WebElement lnkBooks;
    @FindBy(css ="#topnav select.form-control")
    public WebElement selMainMenu;
    @FindBy(xpath = "//div[@class='btn-group search-bar']//input[@type='text']")
    public WebElement txtSearchBar;
    @FindBy(css = "ul.language li[class='dropdown hover'] .dropdown-toggle+.currency")
    public WebElement sddCurrency;
    @FindBy(css = "a[class='dropdown-toggle'][href$='checkout/cart']")
    public WebElement btnShoppingCart;
    @FindBy(xpath = "//section[@id='featured']//a[@data-id='50']")
    public WebElement btnAddFeaturedToCart;
    @FindBy(css = "li.hover>a[href$='checkout/cart']")
    public WebElement btnCheckout;
    @FindBy(css = "#block_frame_featured_1769 a[data-id='50']+div.price div.oneprice")
    public WebElement lblFeaturedItemPrice;
    @FindBy(css = "[class='nav topcart pull-left'] span.label-orange")
    public WebElement lblItemCount;
    @FindBy(css = "span.cart_total")
    public WebElement lblCartTotal;



    public HomePage(WebDriver givenDriver){
        super(givenDriver);
    }

    public LoginPage clickLoginBtn(){
        btnLoginRegister.click();
        return new LoginPage(driver);
    }

    public SearchPage searchItem(String item){
        txtSearchBar.clear();
        txtSearchBar.sendKeys(item);
        txtSearchBar.sendKeys(Keys.ENTER);
        pause(1000);
        return new SearchPage(driver);
    }

    public HomePage addFeaturedItemToCart(){
        btnAddFeaturedToCart.click();
        return this;
    }

    public CartPage clickCartBtn(){
        btnCheckout.click();
        pause(500);
        return new CartPage(driver);
    }

    public double getItemPrice(){
        return Double.parseDouble(lblFeaturedItemPrice.getText().toString().replaceAll("\\$", ""));
    }

    public double getCartTotal(){
        return Double.parseDouble(lblCartTotal.getText().toString().replaceAll("\\$", ""));
    }

    public int getItemCount(){
        return Integer.parseInt(lblItemCount.getText().toString());
    }

    public List<WebElement> getAddToCartBtns(){
        return driver.findElements(By.cssSelector("a[title='Add to Cart']"));
    }

    public HomePage addRandomItemToCart(){
        List<WebElement> products = getAddToCartBtns();
        Random rand = new Random();
        WebElement item = products.get(rand.nextInt(products.size()));
        item.click();
        return this;
    }

    public HomePage addAllItemsToCart(){
//        List<WebElement> brokenBtns = findBrokenBtns();
        List<WebElement> allProductBtns = getAddToCartBtns();

//        for(WebElement item : brokenBtns){
//            if(allProductBtns.contains(item))
//                allProductBtns.remove(item);
//        }

        for(WebElement item : allProductBtns){
            if(item.getAttribute("href").toString().equals("https://automationteststore.com/#")){
                item.click();
            }
        }
        return this;
    }

    public List<WebElement> findBrokenBtns() {
        List<WebElement> allBtns = getAddToCartBtns();
        List<WebElement> brokenBtns = new ArrayList<>();

        for (WebElement btn : allBtns) {
            if (!btn.getAttribute("href").toString().equals("https://automationteststore.com/#")) {
                brokenBtns.add(btn);
            }
        }

        return brokenBtns;
    }
}
