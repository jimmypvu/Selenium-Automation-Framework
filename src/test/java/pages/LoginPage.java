package pages;

<<<<<<< Updated upstream:src/test/java/pages/LoginPage.java
import org.apache.commons.logging.Log;
=======
import org.openqa.selenium.By;
>>>>>>> Stashed changes:src/test/java/org/jvu/pages/LoginPage.java
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
<<<<<<< Updated upstream:src/test/java/pages/LoginPage.java
    @FindBy(xpath = "//span[contains(text(), 'Account Logout')]")
    WebElement hdrAccLogout;
=======
    public static By byLoginErrorLbl = By.cssSelector("div[class='alert alert-error alert-danger']");
>>>>>>> Stashed changes:src/test/java/org/jvu/pages/LoginPage.java

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
