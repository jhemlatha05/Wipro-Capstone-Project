package com.capstone.sauce.tests;

import com.capstone.sauce.core.BaseTest;
import com.capstone.sauce.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class SauceDemoE2ETest extends BaseTest {

    @Test(description = "End-to-end flow: login, sort, add items, validate cart, checkout")
    public void completePurchaseFlow() {
        final String username = "standard_user";
        final String password = "secret_sauce";
        final String item1 = "Sauce Labs Onesie";
        final String item2 = "Sauce Labs Bike Light";
        final List<String> expectedItems = Arrays.asList(item1, item2);

        LoginPage login = new LoginPage(driver);
        login.open();
        login.login(username, password);

        InventoryPage inventory = new InventoryPage(driver);
        Assert.assertTrue(inventory.isAt(), "Should land on /inventory.html after login");

        inventory.sortByPriceLowToHigh();
        inventory.addItemToCartByName(item1);
        inventory.addItemToCartByName(item2);

        int count = inventory.getCartCount();
        Assert.assertEquals(count, 2, "Cart count should be 2 after adding two items");

        inventory.openCart();

        CartPage cart = new CartPage(driver);
        List<String> cartItems = cart.getCartItemNames();
        Assert.assertTrue(cartItems.containsAll(expectedItems), "Cart should contain both added items");

        cart.clickCheckout();

        CheckoutStepOnePage stepOne = new CheckoutStepOnePage(driver);
        stepOne.fillInformation("John", "Doe", "12345");
        stepOne.clickContinue();

        CheckoutStepTwoPage stepTwo = new CheckoutStepTwoPage(driver);
        stepTwo.clickFinish();

        CheckoutCompletePage complete = new CheckoutCompletePage(driver);
        String header = complete.getCompletionHeader();
        Assert.assertTrue(header.toLowerCase().contains("thank you for your order!"),
        	    "Expected completion message but got: " + header);
    }
}
