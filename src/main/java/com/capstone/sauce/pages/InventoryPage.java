package com.capstone.sauce.pages;

import com.capstone.sauce.core.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class InventoryPage extends BasePage {

    @FindBy(css = "select.product_sort_container")
    private WebElement sortDropdown;

    @FindBy(id = "shopping_cart_container")
    private WebElement cartContainer;

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAt() {
        return getCurrentUrl().contains("/inventory.html");
    }

    public void sortByPriceLowToHigh() {
        wait.until(ExpectedConditions.visibilityOf(sortDropdown));
        Select select = new Select(sortDropdown);
        select.selectByValue("lohi");
        System.out.println("Sorted products by Price (low to high)");
    }

    public void addItemToCartByName(String productName) {
        List<WebElement> items = driver.findElements(By.cssSelector(".inventory_item"));
        for (WebElement item : items) {
            String name = item.findElement(By.cssSelector(".inventory_item_name")).getText().trim();
            if (name.equalsIgnoreCase(productName)) {
                WebElement addBtn = item.findElement(By.cssSelector("button.btn_inventory"));
                addBtn.click();
                System.out.println("Added item to cart: " + productName);
                return;
            }
        }
        throw new NoSuchElementException("Product not found: " + productName);
    }

    public int getCartCount() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement badge = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("span.shopping_cart_badge")));
            return Integer.parseInt(badge.getText().trim());
        } catch (TimeoutException | NoSuchElementException | NumberFormatException e) {
            return 0;
        }
    }

    public void openCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartContainer)).click();
        System.out.println("Opened cart from header icon");
    }

	
}
