package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public class CartTest extends BaseTest {

    @Test(testName = "End-to-end test", description = "Add products to the cart>remove products from the cart>check the cart is empty",
            priority = 2)
    @Description("Add products to the cart>remove products from the cart>check the cart is empty")
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

    @Test(testName = "Add product to the cart", description = "Check that product is added to the cart",
            priority = 1)
    @Description("Add product to the cart")
    public void checkAddingProductToTheCart() {
        String productName = "Sauce Labs Backpack";
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Backpack");
        productsPage.openCart();
        String addedProductNameInTheCart = shoppingCartPage.getAddedProductName(productName);
        assertEquals(addedProductNameInTheCart, productName, "Product names are NOT matched");
    }

    @Test(testName = "Remove product from cart", description = "Check that the product is removed from cart",
            priority = 1)
    @Description("Remove product from cart")
    public void checkRemovingProductFromCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Bike Light");
        productsPage.openCart();
        shoppingCartPage.clickRemoveButton("Sauce Labs Bike Light");
        assertTrue(shoppingCartPage.shoppingCartIsEmpty(), "Product was NOT removed");
    }

    @Test(testName = "Open Products page using Continue Shopping button", description = "Check that Continue Shopping opens Products page",
            priority = 1)
    @Description("Open Products page using Continue Shopping button")
    public void checkContinueShoppingOpensProducts() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Bike Light");
        productsPage.openCart();
        shoppingCartPage.clickOnContinueShoppingButton();
        String productsPageName = productsPage.getPageTitle();
        assertEquals(productsPageName, "Products", "Products page was NOT opened.");
    }

    @Test(testName = "Open Checkout page using Checkout button", description = "Check that Checkout button opens Checkout page",
            priority = 1)
    @Description("Open Checkout page using Checkout button")
    public void checkCheckoutOpensCheckoutPage() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Bike Light");
        productsPage.openCart();
        shoppingCartPage.clickOnCheckoutButton();
        String openedPageName = checkoutInformationPage.getPageTitle();
        assertEquals(openedPageName, "Checkout: Your Information", "'Checkout: Your Information' page was NOT opened");
    }

    @Test(testName = "Quantity of added to the Cart product", description = "Check that quantity of added products is correct in the shopping cart",
            priority = 2)
    @Description("Quantity of added to the Cart product")
    public void checkAddedToTheCartProductQuantity() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Bike Light");
        productsPage.openCart();
        String productQuantity = shoppingCartPage.getProductQuantity("Sauce Labs Bike Light");
        assertEquals(productQuantity, "1", "The quantity is not equal to 1.");
    }

    @Test(testName = "Shopping badge value", description = "Check that the value on shopping badge is correct",
            priority = 2)
    @Description("Shopping badge value")
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
