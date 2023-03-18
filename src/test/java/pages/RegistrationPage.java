package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Random;

public class RegistrationPage extends BasePage{
    @FindBy(xpath = "//input[@name='firstname']")
    WebElement txtFirstName;
    @FindBy(xpath = "//input[@name='lastname']")
    WebElement txtLastName;
    @FindBy(id = "#AccountFrm_email")
    WebElement txtEmail;
    @FindBy(xpath = "//input[@name='address_1']")
    WebElement txtAddress1;
    @FindBy(id = "AccountFrm_city")
    WebElement txtCity;
    @FindBy(id = "AccountFrm_zone_id")
    WebElement sddState;
    @FindBy(id = "AccountFrm_postcode")
    WebElement txtZipcode;
    @FindBy(id = "AccountFrm_country_id")
    WebElement sddCounty;
    @FindBy(id = "AccountFrm_loginname")
    WebElement txtUsername;
    @FindBy(id = "AccountFrm_password")
    WebElement txtNewPassword;
    @FindBy(id = "AccountFrm_confirm")
    WebElement txtConfirmPassword;
    @FindBy(id = "AccountFrm_newsletter0")
    WebElement btnSubscribe;
    @FindBy(id = "AccountFrm_agree")
    WebElement chkPrivacy;
    @FindBy(id = "//button[@title='Continue']")
    WebElement btnContinue;
    @FindBy(css = "alert alert-error alert-danger")
    WebElement divErrorAlert;
    //E-Mail Address is already registered!
    //Email Address does not appear to be valid!
    //Login name must be alphanumeric only and between 5 and 64 characters!
    //Password must be between 4 and 20 characters!
    //Zip/postal code must be between 3 and 10 characters!

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
