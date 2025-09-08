
Feature: Sauce Demo End-to-End Flow

  Scenario: Login should fail with wrong credentials
    Given I am on the saucedemo login page
    When I login with username "wrong_user" and password "wrong_pass"
    Then login should fail with an error message

  Scenario: Successful purchase flow with valid credentials
    Given I am on the saucedemo login page
    When I login with username "standard_user" and password "secret_sauce"
    And I sort products by "Price (low to high)"
    And I add "Sauce Labs Onesie" and "Sauce Labs Bike Light" to the cart
    Then cart count should be "2"
    When I open the cart and checkout using checkout data from excel
    Then I should see order completion message "THANK YOU FOR YOUR ORDER"
