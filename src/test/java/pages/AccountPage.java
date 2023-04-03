package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage{
    @FindBy(xpath = "//span/i[@class='fa fa-user']")
    WebElement hdrMyAccount;
    @FindBy(xpath = "//a[@data-original-title = 'Logoff']")
    WebElement btnLogout;
<<<<<<< Updated upstream:src/test/java/pages/AccountPage.java
=======
    public static By byAccLogoutHdr = By.xpath("//span[contains(text(), 'Account Logout')]");
    public static By byMyAccountHdr = By.xpath("//span/i[@class='fa fa-user']");

>>>>>>> Stashed changes:src/test/java/org/jvu/pages/AccountPage.java
    public AccountPage(WebDriver givenDriver){
        super(givenDriver);
    }

    public AccountPage logout(){
        btnLogout.click();
        return this;
    }
}
