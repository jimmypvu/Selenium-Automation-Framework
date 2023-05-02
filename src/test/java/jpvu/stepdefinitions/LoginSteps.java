package jpvu.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jpvu.pages.AccountPage;
import jpvu.pages.HomePage;
import jpvu.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static jpvu.stepdefinitions.runner.CucumberHooks.getDriver;

public class LoginSteps {
    protected WebDriverWait wait;


    @Given("^user is on the login page$")
    public void iNavigateToLoginPage() {
        HomePage hp = new HomePage(getDriver());

        hp.clickLoginBtn();
    }

    @When("^user enters username and password$")
    public void iEnterUsernameAndPassword() {
        LoginPage lp = new LoginPage(getDriver());

        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginFrm_loginname"))).sendKeys("sirtestsalot");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginFrm_password"))).sendKeys("1234iTestMore!");

//        lp.setUsername("sirtestsalot").setPassword("1234iTestMore!");
    }

    @And("user clicks Login button")
    public void iClickLogin() {
        LoginPage lp = new LoginPage(getDriver());

        lp.clickLoginBtn();
    }

    @Then("^user is logged into account page$")
    public void iAmLoggedIn() {
        AccountPage ap = new AccountPage(getDriver());

        Assert.assertTrue(ap.hdrMyAccount.isDisplayed());
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/account");
    }

    @Then("^user is not logged in$")
    public void iAmNotLoggedIn() {
        LoginPage lp = new LoginPage(getDriver());

        Assert.assertTrue(lp.lblLoginError.isDisplayed());
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/login");
    }

    @When("user enters {string} and {string}")
    public void iEnterBadCredentials(String username, String password) {
        LoginPage lp = new LoginPage(getDriver());

        lp.setUsername(username).setPassword(password);
    }
}
