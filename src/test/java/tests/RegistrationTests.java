package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.RegistrationPage;

public class RegistrationTests extends BaseTest{

    @Test(description = "valid registration test", enabled = false)
    public void registerValidInfo() {
        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = homePage.clkLoginBtn();
        RegistrationPage regPage = loginPage.clkRegisterBtn();

        regPage.setFirstName("sir")
                .setLastName("testsalot")
                .setEmail(regPage.genString(10, true, true, false) + "@gmail.com")
                .setAddress("123 testing lane")
                .setCity("west testington")
                .selectCountry("United States")
                .selectState("California")
                .setZip("12345")
                .setUsername(regPage.genString(10, true, true, false))
                .setPassword("1234iTestMore!")
                .clkPrivacy()
                .clkContinue()
                .waitForPresence(By.xpath("//span[contains(text(),'Your Account Has Been Created!')]"));

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/success");
    }

    @Test(description = "invalid registration test - invalid email", enabled = true)
    public void registerInvalidEmail(){
        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = homePage.clkLoginBtn();
        RegistrationPage regPage = loginPage.clkRegisterBtn();

        regPage.setFirstName("sir")
                .setLastName("testsalot")
                .setEmail(regPage.genString(10, false, true, true) + "@gmail.com")
                .setAddress("123 testing lane")
                .setCity("west testington")
                .selectCountry("United States")
                .selectState("California")
                .setZip("12345")
                .setUsername(regPage.genString(10, true, true, false))
                .setPassword("1234iTestMore!")
                .clkPrivacy()
                .clkContinue()
                .waitForPresence(By.xpath("//div[@class='alert alert-error alert-danger']"));

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/create");
    }

    @Test(description = "invalid registration test - email already taken", enabled = true)
    public void registerEmailTaken(){
        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = homePage.clkLoginBtn();
        RegistrationPage regPage = loginPage.clkRegisterBtn();

        regPage.setFirstName("sir")
                .setLastName("testsalot")
                .setEmail("testable1@gmail.com")
                .setAddress("123 testing lane")
                .setCity("west testington")
                .selectCountry("United States")
                .selectState("California")
                .setZip("12345")
                .setUsername(regPage.genString(10, true, true, false))
                .setPassword("1234iTestMore!")
                .clkPrivacy()
                .clkContinue()
                .waitForPresence(By.xpath("//div[@class='alert alert-error alert-danger']"));

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/create");
    }
    @Test(description = "invalid registration test - username already taken", enabled = true)
    public void registerUsernameTaken(){
        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = homePage.clkLoginBtn();
        RegistrationPage regPage = loginPage.clkRegisterBtn();

        regPage.setFirstName("sir")
                .setLastName("testsalot")
                .setEmail(regPage.genString(10, true, true, false) + "@gmail.com")
                .setAddress("123 testing lane")
                .setCity("west testington")
                .selectCountry("United States")
                .selectState("California")
                .setZip("12345")
                .setUsername("sirtestsalot")
                .setPassword("1234iTestMore!")
                .clkPrivacy()
                .clkContinue()
                .waitForPresence(By.xpath("//div[@class='alert alert-error alert-danger']"));

        Assert.assertEquals(getDriver().findElement(By.xpath("//span[contains(text(),'login name is not available')]")).getText(),
                "This login name is not available. Try different login name!");
    }

    @Test(description = "invalid registration test - required fields empty", enabled = true)
    public void registerRequiredFieldsEmpty(){
        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = homePage.clkLoginBtn();
        RegistrationPage regPage = loginPage.clkRegisterBtn();

        regPage.setFirstName("sir")
                .setLastName("testsalot")
                .setEmail(regPage.genString(10, true, true, false) + "@gmail.com")
                .selectCountry("United States")
                .selectState("California")
                .setZip("12345")
                .setUsername("sirtestsalot")
                .clkPrivacy()
                .clkContinue()
                .waitForPresence(By.xpath("//div[@class='alert alert-error alert-danger']"));

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/create");
    }

    @Test(description = "register with invalid info", dataProvider = "InvalidRegDP", enabled = true)
    public void regTests01(/*String firstname, String lastname, String email, String address, String city, String state, String zip, String country, String username, String password*/){
        HomePage homePage = new HomePage(getDriver());
        homePage.clkLoginBtn();
        Assert.assertFalse(getDriver().getCurrentUrl().isBlank());
    }

    @Test(description = "register with valid info", dataProvider = "ValidRegistrationProviders", dataProviderClass = BaseTest.class, enabled = true)
    public void registrationTests02(String firstname, String lastname, String email, String address, String city, String state, String zip, String country, String username, String password){
        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = homePage.clkLoginBtn();
        RegistrationPage regPage = loginPage.clkRegisterBtn();

        regPage.setFirstName(firstname)
                .setLastName(lastname)
                .setEmail(email);
        regPage.pause(2000);
        regPage.setAddress(address)
                .setCity(city)
                .selectCountry(country)
                .selectState(state)
                .setZip(zip)
                .setUsername(username)
                .setPassword(password)
                .clkPrivacy();

        regPage.pause(2000);

        regPage.clkContinue()
                .waitForPresence(By.xpath("//div[@class='alert alert-error alert-danger']"));
//                .waitForPresence(By.xpath("//span[contains(text(),'Your Account Has Been Created!')]"));

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/create");
    }
}
