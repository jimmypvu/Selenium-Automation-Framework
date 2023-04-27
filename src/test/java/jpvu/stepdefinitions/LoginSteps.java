package jpvu.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jpvu.pages.AccountPage;
import jpvu.pages.HomePage;
import jpvu.pages.LoginPage;
import jpvu.stepdefinitions.runner.CucumberHooks;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginSteps {
    @Given("^user is on the login page$")
    public void iNavigateToLoginPage() {
        HomePage hp = new HomePage(CucumberHooks.getDriver());
        hp.clickLoginBtn();
    }

    @When("^user enters username and password$")
    public void iEnterUsernameAndPassword() {
        LoginPage lp = new LoginPage(CucumberHooks.getDriver());
        lp.setUsername("sirtestsalot").setPassword("1234iTestMore!");
    }

    @And("user clicks Login button")
    public void iClickLogin() {
        LoginPage lp = new LoginPage(CucumberHooks.getDriver());
        lp.clickLoginBtn();
    }

    @Then("^user is logged into account page$")
    public void iAmLoggedIn() {
        AccountPage ap = new AccountPage(CucumberHooks.getDriver());

        Assert.assertTrue(ap.hdrMyAccount.isDisplayed());
        Assert.assertEquals(CucumberHooks.getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/account");
    }

    @Then("^user is not logged in$")
    public void iAmNotLoggedIn() {
        LoginPage lp = new LoginPage(CucumberHooks.getDriver());

        Assert.assertTrue(lp.lblLoginError.isDisplayed());
        Assert.assertEquals(CucumberHooks.getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=account/login");
    }

    @When("user enters {string} and {string}")
    public void iEnterBadCredentials(String username, String password) {
        LoginPage lp = new LoginPage(CucumberHooks.getDriver());
        lp.setUsername(username).setPassword(password);
    }
}
