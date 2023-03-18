package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    @FindBy(xpath = "//button[@title='Continue']")
    WebElement btnContinue;

    public LoginPage(WebDriver givenDriver){
        super(givenDriver);
    }

    public RegistrationPage clkRegister(){
        btnContinue.click();
        return new RegistrationPage(driver);
    }
}
