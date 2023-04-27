package jpvu.tests.functional;

import jpvu.dataproviders.DataProviders;
import jpvu.pages.HomePage;
import jpvu.pages.LoginPage;
import jpvu.pages.RegistrationPage;
import jpvu.tests.pojos.User;
import framework.BaseTest;
import framework.utils.DataType;
import framework.utils.DataGenerator;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTests extends BaseTest {

    @Test(description = "valid registration test", groups = {"functional", "registration", "smoke", "regression"})
    public void registerValidInfo() {
        HomePage hp = new HomePage(getDriver());
        LoginPage lp = hp.clickLoginBtn();
        RegistrationPage rp = lp.clickRegisterBtn();

        rp.registerNewUser();

        Assert.assertTrue(rp.hdrAccountCreated.isDisplayed());
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/success");
    }

    @Test(description = "invalid registration test - invalid email", groups = {"functional", "registration", "regression"})
    public void registerInvalidEmail(){
        HomePage hp = new HomePage(getDriver());
        LoginPage lp = hp.clickLoginBtn();
        RegistrationPage rp = lp.clickRegisterBtn();

        User newUser = User.builder().email(rp.getInvalidEmail()).build();

        rp.registerNewUser(newUser);

        Assert.assertTrue(rp.txtErrorAlert.isDisplayed());
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/create");
    }

    @Test(description = "invalid registration test - email already taken", groups = {"functional", "registration", "regression"})
    public void registerTakenEmail(){
        HomePage hp = new HomePage(getDriver());
        LoginPage lp = hp.clickLoginBtn();
        RegistrationPage rp = lp.clickRegisterBtn();

        User newUser = User.builder().email("takenemail01@gmail.com").build();

        rp.registerNewUser(newUser);

        Assert.assertTrue(rp.txtErrorAlert.isDisplayed());
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/create");
    }

    @Test(description = "invalid registration test - username already taken", groups = {"functional", "registration", "regression"})
    public void registerTakenUsername(){
        HomePage hp = new HomePage(getDriver());
        LoginPage lp = hp.clickLoginBtn();
        RegistrationPage rp = lp.clickRegisterBtn();

        User newUser = User.builder().username("sirtestsalot").build();

        rp.registerNewUser(newUser);

        Assert.assertTrue(rp.txtErrorAlert.isDisplayed());
        Assert.assertEquals(getDriver().findElement(By.xpath("//span[contains(text(),'login name is not available')]")).getText(),
                "This login name is not available. Try different login name!");
    }

    @Test(description = "invalid registration test - required fields empty", groups = {"functional", "registration", "regression"})
    public void registerRequiredFieldsEmpty(){
        HomePage hp = new HomePage(getDriver());
        LoginPage lp = hp.clickLoginBtn();
        RegistrationPage rp = lp.clickRegisterBtn();

        rp.registerNewUser("", "", DataGenerator.getRandomFor(DataType.EMAIL), "",
                "", "California", "United States", "",
                DataGenerator.getRandomFor(DataType.USERNAME), DataGenerator.getRandomString(10));

        Assert.assertTrue(rp.txtErrorAlert.isDisplayed());
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/create");
    }

    @Test(description = "register with invalid info", dataProvider = "InvalidRegistrations",
            dataProviderClass = DataProviders.class,groups = {"functional", "registration", "regression"})
    public void invalidRegistrationTests(String firstname, String lastname, String email, String address, String city, String state, String zip, String country, String username, String password){
        HomePage hp = new HomePage(getDriver());
        LoginPage lp = hp.clickLoginBtn();
        RegistrationPage rp = lp.clickRegisterBtn();

        rp.registerNewUser(firstname, lastname, email, address, city, state, country, zip, username, password);

        Assert.assertTrue(rp.txtErrorAlert.isDisplayed());
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/create");
    }

}
