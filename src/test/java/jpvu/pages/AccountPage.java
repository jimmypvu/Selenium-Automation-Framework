package jpvu.pages;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage {
    @FindBy(xpath = "//span/i[@class='fa fa-user']")
    public WebElement hdrMyAccount;
    @FindBy(xpath = "//a[@data-original-title = 'Logoff']")
    public WebElement btnLogout;
    @FindBy(xpath = "//a[@title='Edit account details']")
    public WebElement btnEditAccDetails;
    @FindBy(xpath = "//a[@data-original-title='Change password']")
    public WebElement btnChangePassword;
    @FindBy(xpath ="//li/a[@data-original-title='Manage Address Book']")
    public WebElement btnManageAddresses;
    @FindBy(xpath = "//a[@data-original-title='My wish list']")
    public WebElement btnMyWishlist;
    @FindBy(xpath = "//li/a[@data-original-title='Order history']")
    public WebElement btnOrderHistory;
    @FindBy(xpath = "//li/a[@data-original-title='Transaction history']")
    public WebElement btnTransactionHistory;
    @FindBy(xpath = "//li/a[@data-original-title='Downloads']")
    public WebElement btnDownloads;
    @FindBy(xpath = "//li/a[@data-original-title='Notifications']")
    public WebElement btnNotifications;
    @FindBy(xpath = "//a[@class='active menu_home']")
    public WebElement btnHomepage;
    @FindBy(xpath = "//span[contains(text(), 'Account Logout')]")
    public WebElement hdrLogoutSuccess;

    public AccountPage(WebDriver givenDriver){
        super(givenDriver);
    }

    public AccountPage logout(){
        btnLogout.click();
        return this;
    }

    public HomePage clickHomeBtn(){
//        pause(500);
//        scrollIntoView(btnHomepage);
        btnHomepage.click();
        pause(500);
        return new HomePage(driver);
    }
}
