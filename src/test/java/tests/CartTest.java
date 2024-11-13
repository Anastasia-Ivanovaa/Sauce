package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public class CartTest extends BaseTest {

    @Test
    public void checkEndToEndCase() {
        SoftAssert softAssert = new SoftAssert();
        Map<String, String> products = new HashMap<>();
        products.put("Sauce Labs Backpack", "$29.99");
        products.put("Sauce Labs Bike Light", "$9.99");
        products.put("Sauce Labs Onesie", "$7.99");

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Backpack");
        productsPage.clickAddButton("Sauce Labs Bike Light");
        productsPage.clickAddButton("Sauce Labs Onesie");
        productsPage.openCart();

        softAssert.assertEquals(shoppingCartPage.getProductPrice("Sauce Labs Backpack"), products.get("Sauce Labs Backpack"),
                "Sauce Labs Backpack item is NOT found");
        softAssert.assertEquals(shoppingCartPage.getProductPrice("Sauce Labs Bike Light"), products.get("Sauce Labs Bike Light"),
                "Sauce Labs Bike Light item is NOT found");
        softAssert.assertEquals(shoppingCartPage.getProductPrice("Sauce Labs Onesie"), products.get("Sauce Labs Onesie"),
                "Sauce Labs Onesie item is NOT found");


        shoppingCartPage.clickRemoveButton("Sauce Labs Backpack");
        shoppingCartPage.clickRemoveButton("Sauce Labs Bike Light");
        shoppingCartPage.clickRemoveButton("Sauce Labs Onesie");

        softAssert.assertTrue(shoppingCartPage.shoppingCartIsEmpty(), "Cart contains products");
        softAssert.assertAll();
    }

    @Test
    public void checkAddingProductToTheCart() {
        String productName = "Sauce Labs Backpack";
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Backpack");
        productsPage.openCart();
        String addedProductNameInTheCart = shoppingCartPage.getAddedProductName(productName);
        assertEquals(addedProductNameInTheCart, productName, "Product names are NOT matched");
    }

    @Test
    public void checkRemovingProductFromCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Bike Light");
        productsPage.openCart();
        shoppingCartPage.clickRemoveButton("Sauce Labs Bike Light");
        assertTrue(shoppingCartPage.shoppingCartIsEmpty(), "Product was NOT removed");
    }

    @Test
    public void checkContinueShoppingOpensProducts() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Bike Light");
        productsPage.openCart();
        shoppingCartPage.clickOnContinueShoppingButton();
        String productsPageName = productsPage.getPageTitle();
        assertEquals(productsPageName, "Products", "Products page was NOT opened.");
    }

    @Test
    public void checkCheckoutOpensCheckoutPage() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Bike Light");
        productsPage.openCart();
        shoppingCartPage.clickOnCheckoutButton();
        String openedPageName = checkoutInformationPage.getPageTitle();
        assertEquals(openedPageName, "Checkout: Your Information", "'Checkout: Your Information' page was NOT opened");
    }

    @Test
    public void checkAddedToTheCartProductQuantity() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Bike Light");
        productsPage.openCart();
        String productQuantity = shoppingCartPage.getProductQuantity("Sauce Labs Bike Light");
        assertEquals(productQuantity, "1", "The quantity is not equal to 1.");
    }

    @Test
    public void checkShoppingBadgeValue() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Bike Light");
        productsPage.clickAddButton("Sauce Labs Backpack");
        productsPage.clickAddButton("Sauce Labs Bolt T-Shirt");
        productsPage.openCart();
        String valueOnBadge = shoppingCartPage.getAddedProductQtyToTheCart();
        assertEquals(valueOnBadge, "3", "The added quantity of products was not matched.");
    }
}
