package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
    @FindBy(xpath = "//a[contains(text(), 'Login or register')]")
    WebElement loginRegisterBtn;

    public HomePage(WebDriver givenDriver){
        super(givenDriver);
    }

    public LoginPage clkLoginBtn(){
        loginRegisterBtn.click();
        return new LoginPage(driver);
    }


}
