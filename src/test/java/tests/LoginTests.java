package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.util.Random;

public class LoginTests extends BaseTest {
    @Test(description = "login with valid credentials test")
    public void loginValidCredentials(){
        //sirtestsalot / 1234iTestMore!
        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = homePage.clkLoginBtn();


        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/account");
    }

    @Test(description = "login with invalid credentials test - empty email")
    public void loginEmptyEmail(){

    }
}
