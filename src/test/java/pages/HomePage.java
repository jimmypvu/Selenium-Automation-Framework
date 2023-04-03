package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
    @FindBy(xpath = "//a[contains(text(), 'Login or register')]")
    WebElement btnLoginRegister;
<<<<<<< Updated upstream:src/test/java/pages/HomePage.java
=======
    public static By byLoginRegisterBtn = By.xpath("//a[contains(text(), 'Login or register')]");
>>>>>>> Stashed changes:src/test/java/org/jvu/pages/HomePage.java

    public HomePage(WebDriver givenDriver){
        super(givenDriver);
    }

    public LoginPage clickLoginBtn(){
        btnLoginRegister.click();
        return new LoginPage(driver);
    }


}
