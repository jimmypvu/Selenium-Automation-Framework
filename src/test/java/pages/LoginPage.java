package pages;

import org.apache.commons.logging.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    @FindBy(xpath = "//button[@title='Continue']")
    WebElement btnContinueRegister;
    @FindBy(id = "loginFrm_loginname")
    WebElement txtUsername;
    @FindBy(id = "loginFrm_password")
    WebElement txtPassword;
    @FindBy(xpath = "//button[@title='Login']")
    WebElement btnLogin;

    public LoginPage(WebDriver givenDriver){
        super(givenDriver);
    }

    public LoginPage setUsername(String username){
        txtUsername.sendKeys(username);
        return this;
    }

    public LoginPage setPassword(String password){
        txtPassword.sendKeys(password);
        return this;
    }

    public LoginPage clkLoginBtn(){
        btnLogin.click();
        return this;
    }

    public RegistrationPage clkRegisterBtn(){
        btnContinueRegister.click();
        return new RegistrationPage(driver);
    }
}
