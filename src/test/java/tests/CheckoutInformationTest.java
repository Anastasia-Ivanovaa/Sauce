package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CheckoutInformationTest extends BaseTest {

    @Test(testName = "Blank First Name in checkout info", description = "Check user cannot link to 'Checkout: Overview' page if FS is blank",
            priority = 1)
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
