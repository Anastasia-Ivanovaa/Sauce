package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CheckoutInformationTest extends BaseTest {

    @Test(testName = "Valid Checkout Info", description = "Check that Checkout: Overview page is opened")
    public void successfulCheckoutInfo() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Backpack");
        productsPage.openCart();
        shoppingCartPage.clickOnCheckoutButton();
        checkoutInformationPage.addCheckoutInfo("Ann", "Smith", "123456");
        String pageTitle = checkoutOverviewPage.pageTitle();
        assertEquals(pageTitle, "Checkout: Overview",
                "Incorrect page is opened.");
    }

    @DataProvider(name = "checkoutData")
    public Object[][] checkoutData() {
        return new Object[][]{
                {"", "Smith", "123456", "Error: First Name is required"},
                {"Ann", "", "123456", "Error: Last Name is required"},
                {"Ann", "Smith", "", "Error: Postal Code is required"}
        };
    }

    @Test(dataProvider = "checkoutData", testName = "Invalid Checkout Info", description = "Check that correct message is shown if field is blank")
    public void checkoutNegativeTests(String firstName, String lastName, String zipCode, String expectedMessage) {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Backpack");
        productsPage.openCart();
        shoppingCartPage.clickOnCheckoutButton();
        checkoutInformationPage.addCheckoutInfo(firstName, lastName, zipCode);
        String errorMessage = checkoutInformationPage.getErrorMessage();
        assertEquals(errorMessage, expectedMessage,
                "The text message is not valid");
    }

    @Test(testName = "Check Cancel Button", description = "Check that Cancel button opens Your Cart page ")
    public void openShoppingCartByCancelButton() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Backpack");
        productsPage.openCart();
        shoppingCartPage.clickOnCheckoutButton();
        checkoutInformationPage.returnToShoppingCart();
        String shoppingCartPageTitle = shoppingCartPage.getPageTitle();
        assertEquals(shoppingCartPageTitle, "Your Cart", "Your Cart page is not opened.");
    }
}
