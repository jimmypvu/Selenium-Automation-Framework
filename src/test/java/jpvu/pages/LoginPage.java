package jpvu.pages;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(xpath = "//button[@title='Continue']")
    public WebElement btnContinueRegister;
    @FindBy(id = "loginFrm_loginname")
    public WebElement txtUsername;
    @FindBy(id = "loginFrm_password")
    public WebElement txtPassword;
    @FindBy(xpath = "//button[@title='Login']")
    public WebElement btnLogin;
    @FindBy(xpath = "//span[contains(text(), 'Account Logout')]")
    public WebElement hdrAccLogout;
    @FindBy(css = "#accountFrm_accountguest")
    public WebElement radGuestCheckout;
    @FindBy(css = "div[class='alert alert-error alert-danger']")
    public WebElement lblLoginError;


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

    public CheckoutPage clickGuestCheckout(){
        radGuestCheckout.click();
        btnContinueRegister.click();
        return new CheckoutPage(driver);
    }

}
