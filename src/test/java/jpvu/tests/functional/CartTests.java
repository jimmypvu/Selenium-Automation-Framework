package jpvu.tests.functional;

import jpvu.pages.CartPage;
import jpvu.pages.HomePage;
import framework.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTests extends BaseTest {

    @Test(description = "a user should be able to add an item to their cart", groups = {"functional", "cart", "smoke", "regression"})
    public void addItemToCartFromHomePage(){
        HomePage hp = new HomePage(getDriver());

        hp.addFeaturedItemToCart();

        int itemCount = hp.getItemCount();

        Assert.assertEquals(itemCount, 1);
    }


    @Test(description = "a user should be able to update item quantities in their cart", groups = {"functional", "cart", "smoke", "regression"})
    public void updateItemQuantity(){
        HomePage hp = new HomePage(getDriver());

        hp.addFeaturedItemToCart()
            .addFeaturedItemToCart()
            .addFeaturedItemToCart();

        int itemCount = hp.getItemCount();

        Assert.assertEquals(itemCount, 3);

        CartPage cp = hp.clickCartBtn();
        cp.updateQuantity(1);

        int newItemCount = cp.getQuantity();

        Assert.assertEquals(newItemCount, 1);
        Assert.assertTrue(newItemCount < itemCount);
    }
}
