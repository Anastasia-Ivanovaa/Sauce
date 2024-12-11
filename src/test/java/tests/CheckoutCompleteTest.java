package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CheckoutCompleteTest extends BaseTest {

    @Test(testName = "Check thank you message", description = "Check that Thank you message is shown after finishing checkout")
    @Description("Check thank you message is correct")
    public void checkThankYouMessage() {
        loginPage.open();
        loginPage.login(user, password);
        productsPage.clickAddButton("Sauce Labs Backpack");
        productsPage.openCart();
        shoppingCartPage.clickOnCheckoutButton();
        checkoutInformationPage.addCheckoutInfo("Ann", "Smith", "123456");
        checkoutOverviewPage.clickOnFinishButton();
        String thankYouMessage = checkoutCompletePage.getThankYouMessage();
        assertEquals(thankYouMessage, "Thank you for your order!", "Incorrect message is shown");
    }

    @Test(testName = "Back Home button returns to Products Page", description = "Check that Products page is opened when clicking on Back Home button")
    @Description("Back Home button returns to Products Page")
    public void openProductsPageUsingBackHome() {
        loginPage.open();
        loginPage.login(user, password);
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
