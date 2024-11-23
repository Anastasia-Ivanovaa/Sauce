package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CheckoutOverviewTest extends BaseTest {

    @Test(testName = "addedProductIsShownOnPage", description = "Check that product is displayed on page",
            priority = 1)
    public void checkAddedProduct() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Backpack");
        productsPage.openCart();
        shoppingCartPage.clickOnCheckoutButton();
        checkoutInformationPage.addCheckoutInfo("Ann", "Smith", "123456");
        String addedProductName = checkoutOverviewPage.getProductName("Sauce Labs Backpack");
        assertEquals(addedProductName, "Sauce Labs Backpack", "There is no item in the cart.");
    }

    @Test(testName = "returnToProductsPage", description = "Check that Products page is opened when click on Cancel button", priority = 1)
    public void openProductsPage() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Backpack");
        productsPage.openCart();
        shoppingCartPage.clickOnCheckoutButton();
        checkoutInformationPage.addCheckoutInfo("Ann", "Smith", "123456");
        checkoutOverviewPage.clickOnCancelButton();
        String pageName = productsPage.getPageTitle();
        assertEquals(pageName, "Products", "Incorrect page is opened.");
    }

    @Test(testName = "finishCheckout", description = "Check that Checkout: Complete! page is opened when clicking on Finish button")
    public void finishCheckout() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Backpack");
        productsPage.openCart();
        shoppingCartPage.clickOnCheckoutButton();
        checkoutInformationPage.addCheckoutInfo("Ann", "Smith", "123456");
        checkoutOverviewPage.clickOnFinishButton();
        String pageTitle = checkoutCompletePage.pageTitle();
        assertEquals(pageTitle, "Checkout: Complete!", "Invalid page is opened.");
    }
}
