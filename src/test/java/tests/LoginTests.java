package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends BaseTest {
    @Test(description = "login with valid credentials test", enabled = true)
    public void loginValidCredentials(){
        //sirtestsalot / 1234iTestMore!
        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = homePage.clkLoginBtn();
        loginPage.login("sirtestsalot", "1234iTestMore!")
                    .waitForPresence(By.xpath("//span/i[@class='fa fa-user']"));

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/account");
    }

    @Test(description = "login with invalid credentials test - empty username", enabled = true)
    public void loginEmptyUsername(){
        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = homePage.clkLoginBtn();
        loginPage.login("", "1234iTestMore!");

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/login");
    }

    @Test(description = "login with invalid credentials test - nonexistent username", enabled = true)
    public void loginNonexistentUsername(){
        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = homePage.clkLoginBtn();
        loginPage.login("idefdontexist77", "1234iTestMore!");

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/login");
    }

    @Test(description = "login with invalid credentials test - empty password", enabled = true)
    public void loginEmptyPassword(){
        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = homePage.clkLoginBtn();
        loginPage.login("sirtestsalot", "");

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/login");
    }

    @Test(description = "login with invalid credentials test - incorrect password", enabled = true)
    public void loginInvalidPassword(){
        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = homePage.clkLoginBtn();
        loginPage.login("sirtestsalot", "badpassword");

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/login");
    }

    @Test(description = "logout after successful login", enabled = true)
    public void logoutSuccess(){
        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = homePage.clkLoginBtn();
        AccountPage accPage = loginPage.login("sirtestsalot", "1234iTestMore!");
        accPage.logout();

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/logout");
    }

    @Test(description = "login tests", dataProvider = "LoginProviders")
    public void loginTests(String username, String password){
        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = homePage.clkLoginBtn();
        loginPage.login(username, password);

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/login");
    }
}
