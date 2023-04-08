package org.jvu.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage{
    @FindBy(xpath = "//span/i[@class='fa fa-user']")
    WebElement hdrMyAccount;
    @FindBy(xpath = "//a[@data-original-title = 'Logoff']")
    WebElement btnLogout;
    @FindBy(xpath = "//a[@title='Edit account details']")
    WebElement btnEditAccDetails;
    @FindBy(xpath = "//a[@data-original-title='Change password']")
    WebElement btnChangePassword;
    @FindBy(xpath ="//li/a[@data-original-title='Manage Address Book']")
    WebElement btnManageAddresses;
    @FindBy(xpath = "//a[@data-original-title='My wish list']")
    WebElement btnMyWishlist;
    @FindBy(xpath = "//li/a[@data-original-title='Order history']")
    WebElement btnOrderHistory;
    @FindBy(xpath = "//li/a[@data-original-title='Transaction history']")
    WebElement btnTransactionHistory;
    @FindBy(xpath = "//li/a[@data-original-title='Downloads']")
    WebElement btnDownloads;
    @FindBy(xpath = "//li/a[@data-original-title='Notifications']")
    WebElement btnNotifications;
    @FindBy(xpath = "//a[@class='active menu_home']")
    WebElement btnHomepage;

    public static By byAccLogoutHdr = By.xpath("//span[contains(text(), 'Account Logout')]");
    public static By byMyAccountHdr = By.xpath("//span/i[@class='fa fa-user']");

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
