package org.jvu.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage{
    @FindBy(xpath = "//span/i[@class='fa fa-user']")
    public static WebElement hdrMyAccount;
    @FindBy(xpath = "//a[@data-original-title = 'Logoff']")
    public static WebElement btnLogout;
    @FindBy(xpath = "//span[contains(text(), 'Account Logout')]")
    public static WebElement hdrAccLogout;

    public AccountPage(WebDriver givenDriver){
        super(givenDriver);
    }

    public AccountPage logout(){
        btnLogout.click();
        return this;
    }
}
