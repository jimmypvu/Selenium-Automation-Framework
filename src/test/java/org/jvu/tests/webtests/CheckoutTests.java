package org.jvu.tests.webtests;

import org.framework.BaseTest;
import org.jvu.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTests extends BaseTest {
    @Test(description = "registered and logged in user should be able to checkout", groups = {"web", "checkout", "smoke", "regression"})
    public void validUserCheckout(){
        HomePage hp = new HomePage(getDriver());
        LoginPage lp = hp.clickLoginBtn();
        AccountPage ap = lp.login("sirtestsalot", "1234iTestMore!");
        hp = ap.clickHomeBtn();
        hp.addAllItemsToCart();

        CartPage ctp = hp.clickCartBtn();

        CheckoutPage cop = ctp.clickCheckout();
        cop.clickCheckoutConfirm();

        Assert.assertTrue(cop.waitAndGet(CheckoutPage.byCheckoutSuccessHdr).isDisplayed());
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=checkout/success");
    }

    @Test(description = "user should be able to checkout as a guest", groups = {"web", "checkout", "regression"})
    public void validGuestCheckout(){
        HomePage hp = new HomePage(getDriver());
        hp.addAllItemsToCart();

        CartPage ctp = hp.clickCartBtn();
        LoginPage lp = ctp.clickCheckoutBtn();
        CheckoutPage cop = lp.clickGuestCheckout();
        cop.enterShippingInfo("guest", "thebest", "TheGuestEver@bmail.com",
                "321 faker street", "fakerton", "United States", "California", "54321")
                .clickContinue().clickCheckoutConfirm();

        Assert.assertTrue(cop.waitAndGet(CheckoutPage.byCheckoutSuccessHdr).isDisplayed());
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationteststore.com/index.php?rt=checkout/success");
    }


}
