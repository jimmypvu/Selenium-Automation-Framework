package org.jvu.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

public class RegistrationPage extends BasePage{
    @FindBy(xpath = "//input[@name='firstname']")
    public static WebElement txtFirstName;
    @FindBy(xpath = "//input[@name='lastname']")
    public static WebElement txtLastName;
    @FindBy(id = "AccountFrm_email")
    public static WebElement txtEmail;
    @FindBy(xpath = "//input[@name='address_1']")
    public static WebElement txtAddress1;
    @FindBy(id = "AccountFrm_city")
    public static WebElement txtCity;
    @FindBy(id = "AccountFrm_zone_id")
    public static WebElement sddState;
    @FindBy(id = "AccountFrm_postcode")
    public static WebElement txtZipcode;
    @FindBy(id = "AccountFrm_country_id")
    public static WebElement sddCountry;
    @FindBy(id = "AccountFrm_loginname")
    public static WebElement txtUsername;
    @FindBy(id = "AccountFrm_password")
    public static WebElement txtNewPassword;
    @FindBy(id = "AccountFrm_confirm")
    public static WebElement txtConfirmPassword;
    @FindBy(id = "AccountFrm_newsletter0")
    public static WebElement btnSubscribeNo;
    @FindBy(id = "AccountFrm_agree")
    public static WebElement chkPrivacy;
    @FindBy(xpath = "//button[@title='Continue']")
    public static WebElement btnContinue;
    @FindBy(css = "alert alert-error alert-danger")
    public static WebElement divError;
    //E-Mail Address is already registered!
    //Email Address does not appear to be valid!
    //Login name must be alphanumeric only and between 5 and 64 characters!
    //Password must be between 4 and 20 characters!
    //Zip/postal code must be between 3 and 10 characters!
    @FindBy(xpath = "//span[contains(text(),'Your Account Has Been Created!')]")
    public static WebElement hdrAccCreated;

    public RegistrationPage(WebDriver givenDriver){
        super(givenDriver);
    }

    public RegistrationPage setFirstName(String name){
        txtFirstName.sendKeys(name);
        return this;
    }

    public RegistrationPage setLastName(String name){
        txtLastName.sendKeys(name);
        return this;
    }

    public RegistrationPage setEmail(String email){
        txtEmail.sendKeys(email);
        return this;
    }

    public RegistrationPage setAddress(String address){
        txtAddress1.sendKeys(address);
        return this;
    }

    public RegistrationPage setCity(String city){
        txtCity.sendKeys(city);
        return this;
    }

    public RegistrationPage selectCountry(String country){
        scrollIntoView(sddCountry);
        Select countries = new Select(sddCountry);
        countries.selectByVisibleText(country);
        return this;
    }

    public RegistrationPage selectState(String state){
        try{
            Thread.sleep(1000);
        }catch(Exception e){
            e.printStackTrace();
        }
        scrollIntoView(sddState);
        Select states = new Select(sddState);
        states.selectByVisibleText(state);
        return this;
    }

    public RegistrationPage setZip(String zip){
        scrollIntoView(txtZipcode);
        txtZipcode.sendKeys(zip);
        return this;
    }

    public RegistrationPage setUsername(String username){
        scrollIntoView(txtUsername);
        txtUsername.sendKeys(username);
        return this;
    }

    public RegistrationPage setPassword(String password){
        scrollIntoView(txtNewPassword);
        txtNewPassword.sendKeys(password);
        txtConfirmPassword.sendKeys(password);
        return this;
    }

    public RegistrationPage clickPrivacy(){
        scrollIntoView(chkPrivacy);
        chkPrivacy.click();
        return this;
    }

    public RegistrationPage clickContinue(){
        scrollIntoView(btnContinue);
        btnContinue.click();
        return this;
    }

    public RegistrationPage registerNewUser(String fn, String ln, String email, String addy, String city, String state, String country, String zip, String username, String pw){
        setFirstName(fn)
                .setLastName(ln)
                .setEmail(email)
                .setAddress(addy)
                .setCity(city)
                .selectCountry(country)
                .selectState(state)
                .setZip(zip)
                .setUsername(username)
                .setPassword(pw)
                .clickPrivacy()
                .clickContinue();
        return this;
    }

    public String getRandomEmail(){
        Random rand = new Random();
        return "t" + System.currentTimeMillis() + rand.nextInt(1000) + "@gmail.com";
    }

    public String getInvalidEmail(){
        return genString(10, false, true, true) + "@gmail.com";
    }

    public String getRandomUsername(){
        Random rand = new Random();
        return "t" + System.currentTimeMillis() + rand.nextInt(1000);
    }

    public String genString(int n, boolean abc, boolean nums, boolean special){
        String chars = "";
        if(abc){
            chars += "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        }
        if(nums){
            chars += "0123456789";
        }
        if(special){
            chars += "~!@#$%^&*()_-+=[]{}\\|:;'\"<>,./?";
        }

        StringBuilder sb = new StringBuilder(n);
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            sb.append(chars.charAt(rand.nextInt(chars.length())));
        }

        return sb.toString();
    }

}
