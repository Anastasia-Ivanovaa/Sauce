package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CheckoutInformationTest extends BaseTest {

    @Test
    public void checkFirstNameBlank() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Backpack");
        productsPage.openCart();
        shoppingCartPage.clickOnCheckoutButton();
        checkoutInformationPage.addCheckoutInfo("", "Smith", "12345");
        String errorMessage = checkoutInformationPage.getErrorMessage();
        assertEquals(errorMessage,
                "Error: First Name is required",
                "The text message is not valid");
    }
}
