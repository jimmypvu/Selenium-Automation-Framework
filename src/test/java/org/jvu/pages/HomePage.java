package org.jvu.pages;

import org.framework.BasePage;
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
    WebElement btnLoginRegister;
    @FindBy(xpath = "//a[@class='active menu_home']")
    WebElement btnHome;
    @FindBy(xpath = "//ul[@class='nav-pills categorymenu']//a[contains(text(),'Apparel & accessories')]")
    WebElement lnkApparel;
    @FindBy(xpath = "//ul[@class='nav-pills categorymenu']//a[contains(text(),'Makeup')]")
    WebElement lnkMakeup;
    @FindBy(xpath = "//a[@href='https://automationteststore.com/index.php?rt=product/category&path=43']")
    WebElement lnkSkincare;
    @FindBy(xpath = "//a[@href='https://automationteststore.com/index.php?rt=product/category&path=49']")
    WebElement lnkFragrance;
    @FindBy(xpath = "//a[@href='https://automationteststore.com/index.php?rt=product/category&path=58']")
    WebElement lnkMens;
    @FindBy(xpath = "//nav[@class='subnav']//a[contains(text(),'Hair Care')]")
    WebElement lnkHaircare;
    @FindBy(css = ".nav-pills li:nth-child(8)>a")
    WebElement lnkBooks;
    @FindBy(css ="#topnav select.form-control")
    WebElement sddMainMenu;
    @FindBy(xpath = "//div[@class='btn-group search-bar']//input[@type='text']")
    WebElement txtSearchBar;
    @FindBy(css = "ul.language li[class='dropdown hover'] .dropdown-toggle+.currency")
    WebElement sddCurrency;
    @FindBy(css = "a[class='dropdown-toggle'][href$='checkout/cart']")
    WebElement btnShoppingCart;
    @FindBy(xpath = "//section[@id='featured']//a[@data-id='50']")
    WebElement btnAddFeaturedToCart;
    @FindBy(css = "li.hover>a[href$='checkout/cart']")
    WebElement btnCheckout;
    @FindBy(css = "#block_frame_featured_1769 a[data-id='50']+div.price div.oneprice")
    WebElement lblFeaturedItemPrice;
    @FindBy(css = "i[class='fa fa-shopping-cart fa-fw']+span[class='label label-orange font14']")
    WebElement lblItemCount;
    public static By byLoginRegisterBtn = By.xpath("//a[contains(text(), 'Login or register')]");
    public static By byCartTotalLbl = By.cssSelector("span.cart_total");
    @FindBy(css = "span.cart_total")
    WebElement lblCartTotal;


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
