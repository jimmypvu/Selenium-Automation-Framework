package org.jvu.tests.webtests;

import org.framework.BaseTest;
import org.framework.utils.DataType;
import org.framework.utils.RandomDataGenerator;
import org.jvu.dataproviders.DataProviders;
import org.jvu.tests.pojos.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.jvu.pages.HomePage;
import org.jvu.pages.LoginPage;
import org.jvu.pages.RegistrationPage;

public class RegistrationTests extends BaseTest {

    @Test(description = "valid registration test", groups = {"web", "registration", "smoke", "regression"})
    public void registerValidInfo() {
        HomePage hp = new HomePage(getDriver());
        LoginPage lp = hp.clickLoginBtn();
        RegistrationPage rp = lp.clickRegisterBtn();

        rp.registerNewUser();

        Assert.assertTrue(rp.waitAndGet(RegistrationPage.byAccCreatedHdr).isDisplayed());
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/success");
    }

    @Test(description = "invalid registration test - invalid email", groups = {"web", "registration", "regression"})
    public void registerInvalidEmail(){
        HomePage hp = new HomePage(getDriver());
        LoginPage lp = hp.clickLoginBtn();
        RegistrationPage rp = lp.clickRegisterBtn();

        User newUser = User.builder().email(rp.getInvalidEmail()).build();

        rp.registerNewUser(newUser);

        Assert.assertTrue(rp.waitAndGet(RegistrationPage.byErrorAlertDiv).isDisplayed());
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/create");
    }

    @Test(description = "invalid registration test - email already taken", groups = {"web", "registration", "regression"})
    public void registerTakenEmail(){
        HomePage hp = new HomePage(getDriver());
        LoginPage lp = hp.clickLoginBtn();
        RegistrationPage rp = lp.clickRegisterBtn();

        User newUser = User.builder().email("takenemail01@gmail.com").build();

        rp.registerNewUser(newUser);

        Assert.assertTrue(rp.waitAndGet(RegistrationPage.byErrorAlertDiv).isDisplayed());
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/create");
    }

    @Test(description = "invalid registration test - username already taken", groups = {"web", "registration", "regression"})
    public void registerTakenUsername(){
        HomePage hp = new HomePage(getDriver());
        LoginPage lp = hp.clickLoginBtn();
        RegistrationPage rp = lp.clickRegisterBtn();

        User newUser = User.builder().username("sirtestsalot").build();

        rp.registerNewUser(newUser);

        Assert.assertTrue(rp.waitAndGet(RegistrationPage.byErrorAlertDiv).isDisplayed());
        Assert.assertEquals(getDriver().findElement(By.xpath("//span[contains(text(),'login name is not available')]")).getText(),
                "This login name is not available. Try different login name!");
    }

    @Test(description = "invalid registration test - required fields empty", groups = {"web", "registration", "regression"})
    public void registerRequiredFieldsEmpty(){
        HomePage hp = new HomePage(getDriver());
        LoginPage lp = hp.clickLoginBtn();
        RegistrationPage rp = lp.clickRegisterBtn();

        rp.registerNewUser("", "", RandomDataGenerator.getRandomFor(DataType.EMAIL), "",
                "", "California", "United States", "",
                RandomDataGenerator.getRandomFor(DataType.USERNAME), RandomDataGenerator.getRandomString(10));

        Assert.assertTrue(rp.waitAndGet(RegistrationPage.byErrorAlertDiv).isDisplayed());
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/create");
    }

    @Test(description = "register with invalid info", dataProvider = "InvalidRegistrations",
            dataProviderClass = DataProviders.class,groups = {"web", "registration", "regression"})
    public void invalidRegistrationTests(String firstname, String lastname, String email, String address, String city, String state, String zip, String country, String username, String password){
        HomePage hp = new HomePage(getDriver());
        LoginPage lp = hp.clickLoginBtn();
        RegistrationPage rp = lp.clickRegisterBtn();

        rp.registerNewUser(firstname, lastname, email, address, city, state, country, zip, username, password);

        Assert.assertTrue(rp.waitAndGet(RegistrationPage.byErrorAlertDiv).isDisplayed());
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/create");
    }

}
