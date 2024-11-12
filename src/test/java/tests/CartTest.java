package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CartTest extends BaseTest {

    @Test
    public void checkEndToEndPath() {
        String productName1 = "Sauce Labs Backpack";
        String productPrice1 = "$29.99";

        String productName2 = "Sauce Labs Bike Light";
        String productPrice2 = "$9.99";

        String productName3 = "Sauce Labs Onesie";
        String productPrice3 = "$7.99";

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Backpack");
        productsPage.clickAddButton("Sauce Labs Bike Light");
        productsPage.clickAddButton("Sauce Labs Onesie");
        productsPage.openCart();

        assertEquals(shoppingCartPage.getProductName(), productName1);
        assertEquals(shoppingCartPage.getProductPrice(), productPrice1);

        assertEquals(shoppingCartPage.getProductName(), productName2);
        assertEquals(shoppingCartPage.getProductPrice(), productPrice2);

        assertEquals(shoppingCartPage.getProductName(), productName3);
        assertEquals(shoppingCartPage.getProductPrice(), productPrice3);

        shoppingCartPage.clickRemoveButton("Sauce Labs Backpack");
        shoppingCartPage.clickRemoveButton("Sauce Labs Bike Light");
        shoppingCartPage.clickRemoveButton("Sauce Labs Onesie");

        assertTrue(shoppingCartPage.shoppingCartIsEmpty(), "Cart contains products");
    }

    @Test
    public void checkAddingProductToTheCart() {
        String productName = "Sauce Labs Backpack";
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Backpack");
        productsPage.openCart();
        String addedproductNameInTheCart = shoppingCartPage.getProductName();
        assertEquals(addedproductNameInTheCart, productName, "Product names are not matched");
    }

    @Test
    public void checkRemovingProductFromCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Bike Light");
        productsPage.openCart();
        shoppingCartPage.clickRemoveButton("Sauce Labs Bike Light");
        assertTrue(shoppingCartPage.shoppingCartIsEmpty(), "Product wasn't removed");
    }

    @Test
    public void checkContinueShoppingOpensProducts() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Bike Light");
        productsPage.openCart();
        shoppingCartPage.clickOnContinueShoppingButton();
        String productsPageName = productsPage.getPageTitle();
        assertEquals(productsPageName,"Products","Products page was NOT opened.");
    }

    @Test
    public void checkCheckoutOpensCheckoutPage(){
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Bike Light");
        productsPage.openCart();
        shoppingCartPage.clickOnCheckoutButton();
        String openedPageName = checkoutPage.getPageTitle();
        assertEquals(openedPageName,  "Checkout: Your Information", "Checkout: Your Information was NOT opened");
    }

}
