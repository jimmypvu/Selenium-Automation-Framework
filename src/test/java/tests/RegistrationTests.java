package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.RegistrationPage;

public class RegistrationTests extends BaseTest{

    @Test(description = "valid registration test", enabled = true, priority = 0)
    public void validRegistration(){
        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = homePage.clkLoginBtn();
        RegistrationPage regPage = loginPage.clkRegister();
        regPage.setFirstName("sir").setLastName("testsalot");

        Assert.assertEquals(regPage.findElement(By.xpath("//input[@name='firstname']")).getAttribute("value"), "sir");
    }

    @Test(description = "invalid registration test - invalid email", enabled = true, priority = 0)
    public void invalidRegistrationBadEmail(){
        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = homePage.clkLoginBtn();
        RegistrationPage regPage = loginPage.clkRegister();
        regPage.setFirstName("mister").setLastName("testsalot");

        Assert.assertEquals(regPage.findElement(By.xpath("//input[@name='firstname']")).getAttribute("value"), "mister");
    }

    @Test(description = "invalid registration test - email already taken", enabled = true, priority = 0)
    public void invalidRegistrationDupeEmail(){
        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = homePage.clkLoginBtn();
        RegistrationPage regPage = loginPage.clkRegister();
        regPage.setFirstName("lady").setLastName("testsalot");

        Assert.assertEquals(regPage.findElement(By.xpath("//input[@name='firstname']")).getAttribute("value"), "lady");
    }

    @Test(description = "invalid registration test - empty required fields", enabled = true, priority = 0)
    public void invalidRegistrationEmptyFields(){

    }
}
