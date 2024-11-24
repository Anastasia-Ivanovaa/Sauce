package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CheckoutCompleteTest extends BaseTest {

    @Test(testName = "Check thank you message", description = "Check that Tahnk you message is shown after finishing checkout")
    public void checkThankYouMessage() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Backpack");
        productsPage.openCart();
        shoppingCartPage.clickOnCheckoutButton();
        checkoutInformationPage.addCheckoutInfo("Ann", "Smith", "123456");
        checkoutOverviewPage.clickOnFinishButton();
        String thankYouMessage = checkoutCompletePage.getThankYouMessage();
        assertEquals(thankYouMessage, "Thank you for your order!", "Incorrect message is shown");
    }

    @Test(testName = "Back Home button returns to Products Page", description = "Check that Products page is opened when clicking on Back Home button")
    public void openProductsPageUsingBackHome() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Backpack");
        productsPage.openCart();
        shoppingCartPage.clickOnCheckoutButton();
        checkoutInformationPage.addCheckoutInfo("Ann", "Smith", "123456");
        checkoutOverviewPage.clickOnFinishButton();
        checkoutCompletePage.clickBackHomeButton();
        String pageTitle = productsPage.getPageTitle();
        assertEquals(pageTitle, "Products", "Products page is not opened");
    }
}
