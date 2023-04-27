package jpvu.tests.ui;

import framework.BaseTest;
import jpvu.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartUiTests extends BaseTest {

    @Test(description = "verify cart item count badge is updated when items are added", groups = {"ui", "cart", "smoke", "regression"})
    public void verifyItemCountBadge(){
        HomePage hp = new HomePage(getDriver());

        Assert.assertTrue(hp.lblItemCount.isDisplayed());
        Assert.assertEquals(hp.lblItemCount.getText(), "0");

        hp.addFeaturedItemToCart();

        Assert.assertEquals(hp.lblItemCount.getText(), "1");
    }

    @Test(description = "verify cart total is updated correctly as more items are added", groups = {"ui", "cart", "smoke", "regression"})
    public void verifyCartTotal(){
        HomePage hp = new HomePage(getDriver());

        hp.addFeaturedItemToCart();
        hp.addFeaturedItemToCart();

        double itemPrice = hp.getItemPrice();
        double cartTotal = hp.getCartTotal();

        Assert.assertEquals(cartTotal, itemPrice*2);
    }


}
