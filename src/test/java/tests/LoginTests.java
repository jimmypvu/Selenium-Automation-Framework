package tests;

import dataproviders.DataProviders;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends BaseTest {
    @Test(description = "login with valid credentials test", enabled = true)
    public void loginValidCredentials(){
        //sirtestsalot / 1234iTestMore!
        HomePage hp = new HomePage(getDriver());
        LoginPage lp = hp.clickLoginBtn();
        lp.login("sirtestsalot", "1234iTestMore!");

        WebElement successHeader = lp.waitAndGet(AccountPage.byMyAccountHdr);

        Assert.assertTrue(successHeader.isDisplayed());
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/account");
    }

    @Test(description = "login with invalid credentials test - empty username", enabled = true)
    public void loginEmptyUsername(){
        HomePage hp = new HomePage(getDriver());
        LoginPage lp = hp.clickLoginBtn();
        lp.login("", "1234iTestMore!");

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/login");
    }

    @Test(description = "login with invalid credentials test - nonexistent username", enabled = true)
    public void loginNonexistentUsername(){
        HomePage hp = new HomePage(getDriver());
        LoginPage lp = hp.clickLoginBtn();
        lp.login("idefdontexist77", "1234iTestMore!");

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/login");
    }

    @Test(description = "login with invalid credentials test - empty password", enabled = true)
    public void loginEmptyPassword(){
        HomePage hp = new HomePage(getDriver());
        LoginPage lp = hp.clickLoginBtn();
        lp.login("sirtestsalot", "");

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/login");
    }

    @Test(description = "login with invalid credentials test - incorrect password", enabled = true)
    public void loginInvalidPassword(){
        HomePage hp = new HomePage(getDriver());
        LoginPage lp = hp.clickLoginBtn();
        lp.login("sirtestsalot", "badpassword");

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/login");
    }

    @Test(description = "logout after successful login", enabled = true)
    public void logoutSuccess(){
        HomePage hp = new HomePage(getDriver());
        LoginPage lp = hp.clickLoginBtn();
        AccountPage ap = lp.login("sirtestsalot", "1234iTestMore!");
        ap.logout();

        WebElement successHeader = ap.waitAndGet(AccountPage.byAccLogoutHdr);

        Assert.assertTrue(successHeader.isDisplayed());
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/logout");
    }

    @Test(description = "invalid login tests", dataProvider = "InvalidLogins", dataProviderClass = DataProviders.class, enabled = true)
    public void invalidLoginTests(String username, String password){
        HomePage hp = new HomePage(getDriver());
        LoginPage lp = hp.clickLoginBtn();
        lp.login(username, password);

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/login");
    }

}
