
package com.capstone.sauce.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import com.capstone.sauce.core.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class Hooks {

    private void initDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        BaseTest.driver = new FirefoxDriver(options);
        BaseTest.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        BaseTest.driver.manage().window().maximize();
        System.out.println("[Hooks] Started new FirefoxDriver for scenario");
    }

    @Before
    public void beforeScenario() {
        try {
            if (BaseTest.driver == null) {
                initDriver();
                return;
            }
            try {
                BaseTest.driver.getCurrentUrl(); // throws if invalid
                System.out.println("[Hooks] Reusing existing driver for scenario");
            } catch (WebDriverException wde) {
                System.out.println("[Hooks] Existing driver session invalid, re-initializing: " + wde.getMessage());
                try {
                    BaseTest.driver.quit();
                } catch (Exception ignore) {}
                BaseTest.driver = null;
                initDriver();
            }
        } catch (Exception e) {
            System.out.println("[Hooks] Error in beforeScenario(): " + e.getMessage());
            throw e;
        }
    }

    @After
    public void afterScenario() {
        try {
            if (BaseTest.driver != null) {
                try {
                    BaseTest.driver.quit();
                } catch (Exception e) { 
                    System.out.println("[Hooks] Error quitting driver: " + e.getMessage()); 
                }
                BaseTest.driver = null;
                System.out.println("[Hooks] Closed driver after scenario");
            }
        } catch (Exception e) {
            System.out.println("[Hooks] Error in afterScenario(): " + e.getMessage());
        }
    }
}
