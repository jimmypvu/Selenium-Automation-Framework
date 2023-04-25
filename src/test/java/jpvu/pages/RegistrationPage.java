package jpvu.pages;

import jpvu.tests.pojos.User;
import framework.BasePage;
import framework.utils.DataType;
import framework.utils.DataGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

public class RegistrationPage extends BasePage {
    @FindBy(xpath = "//input[@name='firstname']")
    public WebElement txtFirstName;
    @FindBy(xpath = "//input[@name='lastname']")
    public WebElement txtLastName;
    @FindBy(id = "AccountFrm_email")
    public WebElement txtEmail;
    @FindBy(xpath = "//input[@name='address_1']")
    public WebElement txtAddress1;
    @FindBy(id = "AccountFrm_city")
    public WebElement txtCity;
    @FindBy(id = "AccountFrm_zone_id")
    public WebElement sddState;
    @FindBy(id = "AccountFrm_postcode")
    public WebElement txtZipcode;
    @FindBy(id = "AccountFrm_country_id")
    public WebElement sddCountry;
    @FindBy(id = "AccountFrm_loginname")
    public WebElement txtUsername;
    @FindBy(id = "AccountFrm_password")
    public WebElement txtNewPassword;
    @FindBy(id = "AccountFrm_confirm")
    public WebElement txtConfirmPassword;
    @FindBy(id = "AccountFrm_newsletter0")
    public WebElement btnSubscribeNo;
    @FindBy(id = "AccountFrm_agree")
    public WebElement chkPrivacy;
    @FindBy(xpath = "//button[@title='Continue']")
    public WebElement btnContinue;
    @FindBy(xpath = "//span[contains(text(),'Your Account Has Been Created!')]")
    public WebElement hdrAccountCreated;
    @FindBy(css = "[class='alert alert-error alert-danger']")
    public WebElement txtErrorAlert;


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

    public RegistrationPage registerNewUser(){
        setFirstName(DataGenerator.getRandomFor(DataType.FIRSTNAME))
                .setLastName(DataGenerator.getRandomFor(DataType.LASTNAME))
                .setEmail(DataGenerator.getRandomFor(DataType.EMAIL))
                .setAddress(DataGenerator.getRandomFor(DataType.ADDRESS))
                .setCity(DataGenerator.getRandomFor(DataType.CITY))
                .selectCountry("United States")
                .selectState("California")
                .setZip(DataGenerator.getRandomNumber(5))
                .setUsername(DataGenerator.getRandomFor(DataType.USERNAME))
                .setPassword(DataGenerator.getRandomString(10))
                .clickPrivacy()
                .clickContinue();
        return this;
    }

    public RegistrationPage registerNewUser(User user){
        setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setEmail(user.getEmail())
                .setAddress(user.getAddress())
                .setCity(user.getCity())
                .selectCountry(user.getCountry())
                .selectState(user.getState())
                .setZip(user.getZip())
                .setUsername(user.getUsername())
                .setPassword(user.getPassword())
                .clickPrivacy()
                .clickContinue();
        return this;
    }

    public String getRandomEmail(){
        Random rand = new Random();
        return "t" + System.currentTimeMillis() + rand.nextInt(1000) + "@gmail.com";
    }

    public String getFakerEmail(){
        return DataGenerator.getRandomEmail();
    }

    public String getInvalidEmail(){
        return DataGenerator.getRandomString(10, false, true, true) + "@gmail.com";
    }

    public String getRandomUsername(){
        Random rand = new Random();
        return "t" + System.currentTimeMillis() + rand.nextInt(1000);
    }

    public String getFakerUsername(){
        return DataGenerator.getRandomFor(DataType.USERNAME);
    }

}
