package org.jvu.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.jvu.pages.AccountPage;
import org.jvu.pages.HomePage;
import org.jvu.pages.LoginPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static org.jvu.stepdefinitions.CucumberHooks.getDriver;
public class LoginSteps {
    @Given("^user is on the login page$")
    public void iNavigateToLoginPage() {
        HomePage hp = new HomePage(getDriver());
        hp.waitFor(HomePage.btnLoginRegister);
        hp.clickLoginBtn();
    }
    @When("^user enters username and password$")
    public void iEnterUsernameAndPassword() {
        LoginPage lp = new LoginPage(getDriver());
        lp.waitFor(LoginPage.txtUsername);
        lp.setUsername("sirtestsalot").setPassword("1234iTestMore!");
    }
    @And("user clicks Login button")
    public void iClickLogin() {
        LoginPage lp = new LoginPage(getDriver());
        lp.waitFor(LoginPage.btnLogin);
        lp.clickLoginBtn();
    }
    @Then("^user is navigated to account page$")
    public void iAmLoggedIn() {
        AccountPage ap = new AccountPage(getDriver());
        ap.waitFor(AccountPage.hdrMyAccount);
        Assert.assertTrue(AccountPage.hdrMyAccount.isDisplayed());
    }

    @Then("^user is not logged in$")
    public void iAmNotLoggedIn() {
        LoginPage lp = new LoginPage(getDriver());
        WebElement error = lp.waitAndGet(LoginPage.lblLoginError);
        Assert.assertTrue(error.isDisplayed());
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/login");
    }

    @When("user enters {string} and {string}")
    public void iEnterBadCredentials(String username, String password) {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.waitFor(LoginPage.txtUsername);
        loginPage.setUsername(username).setPassword(password);
    }
}
