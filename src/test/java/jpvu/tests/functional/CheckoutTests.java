package jpvu.tests.functional;

import jpvu.pages.*;
import framework.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTests extends BaseTest {
    @Test(description = "a registered and logged in user should be able to successfully checkout", groups = {"functional", "checkout", "smoke", "regression"})
    public void validUserCheckout(){
        HomePage hp = new HomePage(getDriver());
        LoginPage lp = hp.clickLoginBtn();

        AccountPage ap = lp.login("sirtestsalot", "1234iTestMore!");

        hp = ap.clickHomeBtn();

        hp.addAllItemsToCart();

        CartPage crp = hp.clickCartBtn();
        CheckoutPage chp = crp.clickCheckout();
        chp.clickCheckoutConfirm();

        Assert.assertTrue(chp.hdrCheckoutSuccess.isDisplayed());
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=checkout/success");
    }

    @Test(description = "a guest user should be able to checkout successfully", groups = {"functional", "checkout", "regression"})
    public void validGuestCheckout(){
        HomePage hp = new HomePage(getDriver());

        hp.addAllItemsToCart();

        CartPage crp = hp.clickCartBtn();
        LoginPage lp = crp.clickCheckoutBtn();

        CheckoutPage chp = lp.clickGuestCheckout();

        chp.enterShippingInfo("guest", "thebest", "TheGuestEver@bmail.com",
                "321 faker street", "fakerton", "United States", "California", "54321")
                .clickContinue().clickCheckoutConfirm();

        Assert.assertTrue(chp.hdrCheckoutSuccess.isDisplayed());
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=checkout/success");
    }


}
