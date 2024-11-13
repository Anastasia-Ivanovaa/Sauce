package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;

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
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Bolt T-Shirt");
        productsPage.openCart();
        String addedToTheCartProductName = shoppingCartPage.getProductName();
        softAssert.assertEquals(addedToTheCartProductName, "Sauce Labs Bolt T-Shirt", "Product is NOT found.");
        shoppingCartPage.clickOnContinueShoppingButton();
        productsPage.clickRemoveButton("Sauce Labs Bolt T-Shirt");
        productsPage.openCart();
        softAssert.assertTrue(shoppingCartPage.shoppingCartIsEmpty(), "Product was NOT deleted.");
        softAssert.assertAll();
    }
}
