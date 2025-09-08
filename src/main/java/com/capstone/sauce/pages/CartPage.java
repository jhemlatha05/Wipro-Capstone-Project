package com.capstone.sauce.pages;

import com.capstone.sauce.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    @FindBy(id = "checkout")
    private WebElement checkoutBtn;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getCartItemNames() {
        List<String> names = new ArrayList<>();
        for (WebElement el : driver.findElements(By.cssSelector(".cart_item .inventory_item_name"))) {
            names.add(el.getText().trim());
        }
        return names;
    }

    public void clickCheckout() {
        checkoutBtn.click();
        System.out.println("Clicked Checkout");
    }
}
