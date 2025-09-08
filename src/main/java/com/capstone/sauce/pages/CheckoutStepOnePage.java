package com.capstone.sauce.pages;

import com.capstone.sauce.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutStepOnePage extends BasePage {

    @FindBy(id = "first-name")
    private WebElement firstName;

    @FindBy(id = "last-name")
    private WebElement lastName;

    @FindBy(id = "postal-code")
    private WebElement postalCode;

    @FindBy(id = "continue")
    private WebElement continueBtn;

    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
    }

    public void fillInformation(String f, String l, String zip) {
        firstName.clear();
        firstName.sendKeys(f);
        lastName.clear();
        lastName.sendKeys(l);
        postalCode.clear();
        postalCode.sendKeys(zip);
        System.out.println(String.format("Entered checkout info: %s %s, %s", f, l, zip));
    }

    public void clickContinue() {
        continueBtn.click();
        System.out.println("Clicked Continue");
    }
}
