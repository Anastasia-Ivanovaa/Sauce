package tests;

import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;

import static org.testng.Assert.assertEquals;

public class ProductsTest extends BaseTest {

    @Test(testName = "Remove button", description = "Check that Remove button appears for added to the cart product",
            priority = 2, retryAnalyzer = Retry.class)
    @Description("Remove button appears for added to the cart product")
    public void checkRemoveButtonAppears() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Bolt T-Shirt");
        String removeButton = productsPage.getRemoveButton("Sauce Labs Bolt T-Shirt");
        assertEquals(removeButton, "Remove", "Remove button is NOT available.");
    }

    @Test(testName = "Remove product using Remove button on Products page", description = "Check that Remove button delete added to the cart product",
            priority = 1)
    @Description("Remove product using Remove button on Products page")
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

    @DataProvider(name = "dropdownData")
    public Object[][] dropdownData() {
        return new Object[][]{
                {"Name (Z to A)", "za"},
                {"Name (A to Z)", "az"},
                {"Price (low to high)", "lohi"},
                {"Price (high to low)", "hilo"}
        };
    }

    @Test(dataProvider = "dropdownData")
    @Description("Option can be set in dropdown")
    public void checkDropdown(String option, String optionValue) {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.setOptionInSortedDropdown(option);
        String setOption = productsPage.getDropdownValue();
        assertEquals(setOption, optionValue, "The set option is incorrect.");
    }

    @Test(testName = "Check Products Sorting Z to A", description = "Check that products name are sorted descending")
    @Description("Products Sorting Z to A")
    public void checkSortZtoA() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        ArrayList<String> productsListBeforeSort = new ArrayList<>(productsPage.getAllProductNamesOnPage());
        productsPage.setOptionInSortedDropdown("Name (Z to A)");
        ArrayList<String> productsListAfterSort = new ArrayList<>(productsPage.getAllProductNamesOnPage());
        ArrayList<String> checkedSortedList = productsPage.sortProductsName(productsListBeforeSort, "Name (Z to A)");
        assertEquals(checkedSortedList, productsListAfterSort, "The sorted list is incorrect");
    }

    @Test(testName = "Check Products Sorting A to Z", description = "Check that products name are sorted ascending")
    @Description("Products Sorting A to Z")
    public void checkSortAtoZ() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        ArrayList<String> productsListBeforeSort = new ArrayList<>(productsPage.getAllProductNamesOnPage());
        productsPage.setOptionInSortedDropdown("Name (A to Z)");
        ArrayList<String> productsListAfterSort = new ArrayList<>(productsPage.getAllProductNamesOnPage());
        ArrayList<String> checkedSortedList = productsPage.sortProductsName(productsListBeforeSort, "Name (A to Z)");
        System.out.println(checkedSortedList);
        System.out.println(productsListAfterSort);
        assertEquals(checkedSortedList, productsListAfterSort, "The sorted list is incorrect");
    }

    @Test(testName = "Check Products Sorting by price (low to high)", description = "Check that products prices are sorted ascending")
    @Description("Products Sorting by price (low to high)")
    public void checkSortPriceLowToHigh() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        ArrayList<Double> productsListBeforeSort = new ArrayList<>(productsPage.getAllProductPricesOnPage());
        productsPage.setOptionInSortedDropdown("Price (low to high)");
        ArrayList<Double> productsListAfterSort = new ArrayList<>(productsPage.getAllProductPricesOnPage());
        ArrayList<Double> checkedSortedList = productsPage.sortProductsPrice(productsListBeforeSort, "Price (low to high)");
        assertEquals(checkedSortedList, productsListAfterSort, "The sorted list is incorrect");
    }

    @Test(testName = "Check Products Sorting by price (high to low)", description = "Check that products prices are sorted descending")
    @Description("Products Sorting by price (high to low)")
    public void checkSortPriceHighToLow() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        ArrayList<Double> productsListBeforeSort = new ArrayList<>(productsPage.getAllProductPricesOnPage());
        productsPage.setOptionInSortedDropdown("Price (high to low)");
        ArrayList<Double> productsListAfterSort = new ArrayList<>(productsPage.getAllProductPricesOnPage());
        ArrayList<Double> checkedSortedList = productsPage.sortProductsPrice(productsListBeforeSort, "Price (high to low)");
        assertEquals(checkedSortedList, productsListAfterSort, "The sorted list is incorrect");
    }
}
