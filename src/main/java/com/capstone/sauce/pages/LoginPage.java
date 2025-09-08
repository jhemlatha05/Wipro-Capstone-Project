package com.capstone.sauce.pages;

import com.capstone.sauce.core.BasePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginBtn;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://www.saucedemo.com/");
        System.out.println("Opened SauceDemo login page");
    }

    public void login(String username, String password) {
        usernameInput.clear();
        usernameInput.sendKeys(username);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        loginBtn.click();
        System.out.println("Attempted login with user: " + username);
    }

 // Locator for error message
    

    public String getErrorMessage() {
        try {
            String msg = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
            System.out.println("Attempted login with wrong user, error shown: " + msg);
            return msg;
        } catch (Exception e) {
            System.out.println("No error message displayed");
            return "";
        }
    }


}