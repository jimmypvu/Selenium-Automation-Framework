package org.jvu.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    @FindBy(xpath = "//button[@title='Continue']")
    public static WebElement btnContinueRegister;
    @FindBy(id = "loginFrm_loginname")
    public static WebElement txtUsername;
    @FindBy(id = "loginFrm_password")
    public static WebElement txtPassword;
    @FindBy(xpath = "//button[@title='Login']")
    public static WebElement btnLogin;
    @FindBy(css = "div[class='alert alert-error alert-danger']")
    public static WebElement lblLoginError;

    public LoginPage(WebDriver givenDriver){
        super(givenDriver);
    }

    public AccountPage login(String username, String password){
        setUsername(username);
        setPassword(password);
        clickLoginBtn();
        return new AccountPage(driver);
    }

    public LoginPage setUsername(String username){
        txtUsername.sendKeys(username);
        return this;
    }

    public LoginPage setPassword(String password){
        txtPassword.sendKeys(password);
        return this;
    }

    public AccountPage clickLoginBtn(){
        btnLogin.click();
        return new AccountPage(driver);
    }

    public RegistrationPage clickRegisterBtn(){
        btnContinueRegister.click();
        return new RegistrationPage(driver);
    }
}
