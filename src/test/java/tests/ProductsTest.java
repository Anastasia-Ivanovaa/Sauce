package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;

public class ProductsTest extends BaseTest {

    @Test (testName = "Remove button", description = "Check that Remove button appears for added to the cart product",
            priority = 2, retryAnalyzer = Retry.class)
    public void checkRemoveButtonAppears() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Bolt T-Shirt");
        String removeButton = productsPage.getRemoveButton("Sauce Labs Bolt T-Shirt");
        assertEquals(removeButton, "Remove", "Remove button is NOT available.");
    }

    @Test(testName = "Remove product using Remove button on Products page", description = "Check that Remove button delete added to the cart product",
            priority = 1)
    public void checkRemovingProductFromCart() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Bolt T-Shirt");
        productsPage.openCart();
        String addedToTheCartProductName = shoppingCartPage.getAddedProductName("Sauce Labs Bolt T-Shirt");
        softAssert.assertEquals(addedToTheCartProductName, "Sauce Labs Bolt T-Shirt", "Product is NOT found.");
        shoppingCartPage.clickOnContinueShoppingButton();
        productsPage.clickRemoveButton("Sauce Labs Bolt T-Shirt");
        productsPage.openCart();
        softAssert.assertTrue(shoppingCartPage.shoppingCartIsEmpty(), "Product was NOT deleted.");
        softAssert.assertAll();
    }
}
