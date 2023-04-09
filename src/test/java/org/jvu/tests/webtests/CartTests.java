package org.jvu.tests.webtests;

import org.jvu.pages.CartPage;
import org.jvu.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class CartTests extends BaseTest{

    @Test(description = "user should be able to add an item to the cart", groups = {"web", "cart", "smoke", "regression"})
    public void addItemToCartFromHomePage(){
        HomePage hp = new HomePage(getDriver());
        hp.addFeaturedItemToCart();
        int itemCount = hp.getItemCount();

        Assert.assertTrue(itemCount == 1);
    }

    @Test(description = "verify cart total updates as more items are added", groups = {"web", "cart", "smoke", "regression"})
    public void verifyCartTotal(){
        HomePage hp = new HomePage(getDriver());
        hp.addFeaturedItemToCart();
        hp.addFeaturedItemToCart();

        double itemPrice = hp.getItemPrice();
        double cartTotal = hp.getCartTotal();

        Assert.assertEquals(cartTotal, itemPrice*2);
    }

    @Test(description = "user should be able to update item quantities in cart", groups = {"web", "cart", "smoke", "regression"})
    public void updateItemQuantity(){
        HomePage hp = new HomePage(getDriver());
        hp.addFeaturedItemToCart().addFeaturedItemToCart().addFeaturedItemToCart();
        int itemCount = hp.getItemCount();

        Assert.assertEquals(itemCount, 3);

        CartPage cp = hp.clickCartBtn();
        int newItemCount = cp.updateQuantity(1).getQuantity();

        Assert.assertTrue(newItemCount == 1);
        Assert.assertTrue(newItemCount < itemCount);
    }
}
