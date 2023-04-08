package org.jvu.tests;

import org.jvu.dataproviders.DataProviders;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.jvu.pages.HomePage;
import org.jvu.pages.LoginPage;
import org.jvu.pages.RegistrationPage;

public class RegistrationTests extends BaseTest{

    @Test(description = "valid registration test", groups = {"web", "registration", "smoke", "regression"})
    public void registerValidInfo() {
        HomePage hp = new HomePage(getDriver());
        LoginPage lp = hp.clickLoginBtn();
        RegistrationPage rp = lp.clickRegisterBtn();

        rp.registerNewUser("sir", "testsalot", rp.getRandomEmail(),
                        "123 testing lane", "west testington", "California", "United States",
                        "12345", rp.getRandomUsername(), "1234iTestMore!");

        WebElement successHeader = rp.waitAndGet(RegistrationPage.byAccCreatedHdr);

        Assert.assertTrue(successHeader.isDisplayed());
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/success");
    }

    @Test(description = "invalid registration test - invalid email", groups = {"web", "registration", "regression"})
    public void registerInvalidEmail(){
        HomePage hp = new HomePage(getDriver());
        LoginPage lp = hp.clickLoginBtn();
        RegistrationPage rp = lp.clickRegisterBtn();

        rp.registerNewUser("sir", "testsalot", rp.getInvalidEmail(),
                        "123 testing lane", "west testington", "California", "United States",
                        "12345", rp.getRandomUsername(), "1234iTestMore!")
                .waitFor(RegistrationPage.byErrorAlertDiv);

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/create");
    }

    @Test(description = "invalid registration test - email already taken", groups = {"web", "registration", "regression"})
    public void registerEmailTaken(){
        HomePage hp = new HomePage(getDriver());
        LoginPage lp = hp.clickLoginBtn();
        RegistrationPage rp = lp.clickRegisterBtn();

        rp.registerNewUser("sir", "testsalot", "testable1@gmail.com",
                        "123 testing lane", "west testington", "California", "United States",
                        "12345", rp.getRandomUsername(), "1234iTestMore!")
                .waitFor(RegistrationPage.byErrorAlertDiv);

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/create");
    }
    @Test(description = "invalid registration test - username already taken", groups = {"web", "registration", "regression"})
    public void registerUsernameTaken(){
        HomePage hp = new HomePage(getDriver());
        LoginPage lp = hp.clickLoginBtn();
        RegistrationPage rp = lp.clickRegisterBtn();

        rp.registerNewUser("sir", "testsalot", rp.getRandomEmail(),
                        "123 testing lane", "west testington", "California", "United States",
                        "12345", "sirtestsalot", "1234iTestMore!")
                .waitFor(RegistrationPage.byErrorAlertDiv);

        Assert.assertEquals(getDriver().findElement(By.xpath("//span[contains(text(),'login name is not available')]")).getText(),
                "This login name is not available. Try different login name!");
    }

    @Test(description = "invalid registration test - required fields empty", groups = {"web", "registration", "regression"})
    public void registerRequiredFieldsEmpty(){
        HomePage hp = new HomePage(getDriver());
        LoginPage lp = hp.clickLoginBtn();
        RegistrationPage rp = lp.clickRegisterBtn();

        rp.registerNewUser("sir", "", "", "123 testing lane",
                "west testington", "California", "United States", "12345", "", "")
                .waitFor(RegistrationPage.byErrorAlertDiv);

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/create");
    }

    @Test(description = "register with invalid info", dataProvider = "InvalidRegistrations",
            dataProviderClass = DataProviders.class,groups = {"web", "registration", "regression"})
    public void invalidRegistrationTests(String firstname, String lastname, String email, String address, String city, String state, String zip, String country, String username, String password){
        HomePage hp = new HomePage(getDriver());
        LoginPage lp = hp.clickLoginBtn();
        RegistrationPage rp = lp.clickRegisterBtn();

        rp.registerNewUser(firstname, lastname, email, address, city, state, country, zip, username, password)
                .waitFor(RegistrationPage.byErrorAlertDiv);

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/create");
    }

}
