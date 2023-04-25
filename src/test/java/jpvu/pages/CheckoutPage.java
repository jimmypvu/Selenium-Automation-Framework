package jpvu.pages;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePage {
    @FindBy(css = "h1[class='heading1']")
    public WebElement hdrGuestCheckout;
    @FindBy(css = "#guestFrm_firstname")
    public WebElement txtFirstName;
    @FindBy(css = "#guestFrm_lastname")
    public WebElement txtLastName;
    @FindBy(css = "#guestFrm_email")
    public WebElement txtEmail;
    @FindBy(css = "#guestFrm_address_1")
    public WebElement txtAddress1;
    @FindBy(css = "#guestFrm_city")
    public WebElement txtCity;
    @FindBy(css = "#guestFrm_zone_id")
    public WebElement selState;
    @FindBy(css = "#guestFrm_country_id")
    public WebElement selCountry;
    @FindBy(css = "#guestFrm_postcode")
    public WebElement txtZipcode;
    @FindBy(css = "#guestFrm_shipping_indicator")
    public WebElement chkDiffShipping;
    @FindBy(css = "button[title='Continue']")
    public WebElement btnContinue;
    @FindBy(css = "#checkout_btn")
    public WebElement btnCheckoutConfirm;
    @FindBy(xpath = "//span[contains(text(),'Your Order Has Been Processed')]")
    public WebElement hdrCheckoutSuccess;
    @FindBy(css = "a[title='Continue']")
    public WebElement lnkContinue;


    public CheckoutPage (WebDriver givenDriver){
        super(givenDriver);
    }

    public CheckoutPage enterShippingInfo(String fn, String ln, String em, String addy, String city, String country, String state, String zip){
        enterFirstName(fn);
        enterLastName(ln);
        enterEmail(em);
        enterAddress(addy);
        enterCity(city);
        selectCountry(country);
        selectState(state);
        enterZip(zip);
        return this;
    }

    public CheckoutPage enterFirstName(String fn){
        txtFirstName.sendKeys(fn);
        return this;
    }

    public CheckoutPage enterLastName(String ln){
        txtLastName.sendKeys(ln);
        return this;
    }

    public CheckoutPage enterEmail(String email){
        txtEmail.sendKeys(email);
        return this;
    }

    public CheckoutPage enterAddress(String address){
        txtAddress1.sendKeys(address);
        return this;
    }

    public CheckoutPage enterCity(String city){
        txtCity.sendKeys(city);
        return this;
    }

    public CheckoutPage selectCountry(String country){
        scrollIntoView(selCountry);
        Select countries = new Select(selCountry);
        countries.selectByVisibleText(country);
        return this;
    }

    public CheckoutPage selectState(String state){
        pause(1000);
        scrollIntoView(selState);
        Select states = new Select(selState);
        states.selectByVisibleText(state);
        return this;
    }

    public CheckoutPage enterZip(String zip){
        txtZipcode.sendKeys(zip);
        return this;
    }

    public CheckoutPage clickContinue(){
        scrollIntoView(btnContinue);
        btnContinue.click();
        return this;
    }

    public CheckoutPage clickCheckoutConfirm(){
        btnCheckoutConfirm.click();
        return this;
    }

}
