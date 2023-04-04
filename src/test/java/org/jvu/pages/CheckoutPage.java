package org.jvu.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePage{
    @FindBy(css = "h1[class='heading1']")
    WebElement hdrGuestCheckout;
    @FindBy(css = "#guestFrm_firstname")
    WebElement txtFirstName;
    @FindBy(css = "#guestFrm_lastname")
    WebElement txtLastName;
    @FindBy(css = "#guestFrm_email")
    WebElement txtEmail;
    @FindBy(css = "#guestFrm_address_1")
    WebElement txtAddress1;
    @FindBy(css = "#guestFrm_city")
    WebElement txtCity;
    @FindBy(css = "#guestFrm_zone_id")
    WebElement sddStateRegion;
    @FindBy(css = "#guestFrm_country_id")
    WebElement sddCountry;
    @FindBy(css = "#guestFrm_postcode")
    WebElement txtZipcode;
    @FindBy(css = "#guestFrm_shipping_indicator")
    WebElement chkDiffShipping;
    @FindBy(css = "button[title='Continue']")
    WebElement btnContinue;
    @FindBy(css = "#checkout_btn")
    WebElement btnCheckoutConfirm;
    @FindBy(xpath = "//span[contains(text(),'Your Order Has Been Processed')]")
    WebElement hdrCheckoutSuccess;
    @FindBy(css = "a[title='Continue']")
    WebElement lnkContinue;
    public static By byCheckoutSuccessHdr = By.xpath("//span[contains(text(),'Your Order Has Been Processed')]");



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
        scrollIntoView(sddCountry);
        Select countries = new Select(sddCountry);
        countries.selectByVisibleText(country);
        return this;
    }

    public CheckoutPage selectState(String state){
        pause(1000);
        scrollIntoView(sddStateRegion);
        Select states = new Select(sddStateRegion);
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
