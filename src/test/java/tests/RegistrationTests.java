package tests;

import dataproviders.DataProviders;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.RegistrationPage;

import java.lang.reflect.Method;

import static extentreports.ExtentTestManager.startTest;

public class RegistrationTests extends BaseTest{

    @Test(description = "valid registration test", enabled = true)
    public void registerValidInfo() {
        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = homePage.clickLoginBtn();
        RegistrationPage regPage = loginPage.clickRegisterBtn();

        regPage.registerNewUser("", "testsalot", regPage.getRandomEmail(),
                        "123 testing lane", "west testington", "California", "United States",
                        "12345", regPage.getRandomUsername(), "1234iTestMore!")
                .waitForPresence(By.xpath("//span[contains(text(),'Your Account Has Been Created!')]"));

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/success");
    }

    @Test(description = "invalid registration test - invalid email", enabled = true)
    public void registerInvalidEmail(){
        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = homePage.clickLoginBtn();
        RegistrationPage regPage = loginPage.clickRegisterBtn();

        regPage.registerNewUser("sir", "testsalot", regPage.getInvalidEmail(),
                        "123 testing lane", "west testington", "California", "United States",
                        "12345", regPage.getRandomUsername(), "1234iTestMore!")
                .waitForPresence(By.xpath("//div[@class='alert alert-error alert-danger']"));

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/create");
    }

    @Test(description = "invalid registration test - email already taken", enabled = false)
    public void registerEmailTaken(){
        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = homePage.clickLoginBtn();
        RegistrationPage regPage = loginPage.clickRegisterBtn();

        regPage.registerNewUser("sir", "testsalot", "testable1@gmail.com",
                        "123 testing lane", "west testington", "California", "United States",
                        "12345", regPage.getRandomUsername(), "1234iTestMore!")
                .waitForPresence(By.xpath("//div[@class='alert alert-error alert-danger']"));

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/create");
    }
    @Test(description = "invalid registration test - username already taken", enabled = false)
    public void registerUsernameTaken(){
        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = homePage.clickLoginBtn();
        RegistrationPage regPage = loginPage.clickRegisterBtn();

        regPage.registerNewUser("sir", "testsalot", regPage.getRandomEmail(),
                        "123 testing lane", "west testington", "California", "United States",
                        "12345", "sirtestsalot", "1234iTestMore!")
                .waitForPresence(By.xpath("//div[@class='alert alert-error alert-danger']"));

        Assert.assertEquals(getDriver().findElement(By.xpath("//span[contains(text(),'login name is not available')]")).getText(),
                "This login name is not available. Try different login name!");
    }

    @Test(description = "invalid registration test - required fields empty", enabled = false)
    public void registerRequiredFieldsEmpty(){
        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = homePage.clickLoginBtn();
        RegistrationPage regPage = loginPage.clickRegisterBtn();

        regPage.registerNewUser("sir", "", "", "123 testing lane",
                "west testington", "California", "United States", "12345", "", "")
                .waitForPresence(By.xpath("//div[@class='alert alert-error alert-danger']"));

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/create");
    }

    @Test(description = "register with invalid info", dataProvider = "InvalidRegistrations", dataProviderClass = DataProviders.class, enabled = false)
    public void invalidRegistrationTests(String firstname, String lastname, String email, String address, String city, String state, String zip, String country, String username, String password){
        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = homePage.clickLoginBtn();
        RegistrationPage regPage = loginPage.clickRegisterBtn();

        regPage.registerNewUser(firstname, lastname, email, address, city, state, country, zip, username, password)
                .waitForPresence(By.xpath("//div[@class='alert alert-error alert-danger']"));

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/create");
    }

}
