package jpvu.tests.functional;

import jpvu.dataproviders.DataProviders;
import jpvu.pages.AccountPage;
import jpvu.pages.HomePage;
import jpvu.pages.LoginPage;
import framework.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {
    @Test(description = "login with valid credentials test", groups = {"functional", "login", "smoke", "regression"})
    public void loginValidCredentials(){
        //sirtestsalot / 1234iTestMore!
        HomePage hp = new HomePage(getDriver());
        LoginPage lp = hp.clickLoginBtn();

        AccountPage ap = lp.login("sirtestsalot", "1234iTestMore!");

        Assert.assertTrue(ap.hdrMyAccount.isDisplayed());
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/account");
    }

    @Test(description = "login with invalid credentials test - empty username", groups = {"functional", "login", "regression"})
    public void loginEmptyUsername(){
        HomePage hp = new HomePage(getDriver());
        LoginPage lp = hp.clickLoginBtn();

        lp.login("", "1234iTestMore!");

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/login");
    }

    @Test(description = "login with invalid credentials test - nonexistent username", groups = {"functional", "login", "regression"})
    public void loginNonexistentUsername(){
        HomePage hp = new HomePage(getDriver());
        LoginPage lp = hp.clickLoginBtn();
        lp.login("idefdontexist77", "1234iTestMore!");

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/login");
    }

    @Test(description = "login with invalid credentials test - empty password", groups = {"functional", "login", "regression"})
    public void loginEmptyPassword(){
        HomePage hp = new HomePage(getDriver());
        LoginPage lp = hp.clickLoginBtn();

        lp.login("sirtestsalot", "");

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/login");
    }

    @Test(description = "login with invalid credentials test - incorrect password", groups = {"functional", "login", "regression"})
    public void loginInvalidPassword(){
        HomePage hp = new HomePage(getDriver());
        LoginPage lp = hp.clickLoginBtn();

        lp.login("sirtestsalot", "badpassword");

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/login");
    }

    @Test(description = "logout after successful login", groups = {"functional", "login", "regression"})
    public void logoutSuccess(){
        HomePage hp = new HomePage(getDriver());
        LoginPage lp = hp.clickLoginBtn();

        AccountPage ap = lp.login("sirtestsalot", "1234iTestMore!");

        ap.logout();

        Assert.assertTrue(ap.hdrLogoutSuccess.isDisplayed());
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/logout");
    }

    @Test(description = "invalid login tests", dataProvider = "InvalidLogins",
            dataProviderClass = DataProviders.class, groups = {"functional", "login", "regression"})
    public void invalidLoginTests(String username, String password){
        HomePage hp = new HomePage(getDriver());
        LoginPage lp = hp.clickLoginBtn();

        lp.login(username, password);

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/login");
    }

}
