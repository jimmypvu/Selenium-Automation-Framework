package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.RegistrationPage;

import java.time.Duration;
import java.util.function.Function;

public class RegistrationTests extends BaseTest{

    @Test(description = "valid registration test", enabled = true)
    public void validRegistration() {
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
    public void invalidRegistration01(){
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
    public void invalidRegistration02(){
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
    public void invalidRegistration03(){
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

    @Test(description = "invalid registration test - required fields empty", enabled = true, priority = 0)
    public void invalidRegistration04(){
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
}
