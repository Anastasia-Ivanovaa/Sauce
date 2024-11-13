package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProductsTest extends BaseTest {

    @Test
    public void checkRemoveButtonAppears() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Bolt T-Shirt");
        String removeButton = productsPage.getRemoveButton("Sauce Labs Bolt T-Shirt");
        assertEquals(removeButton, "Remove", "Remove button is NOT available.");
    }

    @Test
    public void checkRemovingProductFromCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Bolt T-Shirt");
        productsPage.openCart();
        String addedToTheCartProductName = shoppingCartPage.getProductName();
        assertEquals(addedToTheCartProductName, "Sauce Labs Bolt T-Shirt", "Product is NOT found.");
        shoppingCartPage.clickOnContinueShoppingButton();
        productsPage.clickRemoveButton("Sauce Labs Bolt T-Shirt");
        productsPage.openCart();
        assertTrue(shoppingCartPage.shoppingCartIsEmpty(), "Product was NOT deleted.");
    }
}
