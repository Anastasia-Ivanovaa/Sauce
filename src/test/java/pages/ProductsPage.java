package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;

@Log4j2
public class ProductsPage extends BasePage {

    private final By PAGE_TITLE = By.cssSelector(".title");
    private final By SHOPPING_CART = By.cssSelector(".shopping_cart_link");
    private final By PRODUCT_NAME = By.xpath("//div[@class='inventory_item_name ']");
    private final By PRODUCT_PRICE = By.xpath("//div[@class='inventory_item_price']");
    private final By PRODUCT_SORT_DROPDOWN = By.className("product_sort_container");

    private final String ADD_TO_CART_PATTERN = "//div[text() = '%s']/ancestor::div[@class = 'inventory_item']//button[text()='Add to cart']";
    private final String REMOVE_FROM_CART_PATTERN = "//div[text() = '%s']/ancestor::div[@class = 'inventory_item']//button[text()='Remove']";

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get page title")
    public String getPageTitle() {
        return driver.findElement(PAGE_TITLE).getText();
    }

    @Step("Get the name of Remove button")
    public String getRemoveButton(String product) {
        log.info("Find the remove button next to {}",product);
        By removeFromCart = By.xpath(String.format(REMOVE_FROM_CART_PATTERN, product));
        return driver.findElement(removeFromCart).getText();
    }

    @Step("Get the set value in dropdown")
    public String getDropdownValue() {
        log.info("Get the set value in dropdown");
        return driver.findElement(PRODUCT_SORT_DROPDOWN).getAttribute("value");
    }

    @Step("Add product {product} to the shopping cart")
    public void clickAddButton(String product) {
        log.info("Add product {} to the cart", product);
        By addToCart = By.xpath(String.format(ADD_TO_CART_PATTERN, product));
        driver.findElement(addToCart).click();
    }

    @Step("Click on Remove button")
    public void clickRemoveButton(String product) {
        log.info("Remove product {} from the cart", product);
        By removeFromCart = By.xpath((String.format(REMOVE_FROM_CART_PATTERN, product)));
        driver.findElement(removeFromCart).click();
    }

    @Step("Open Shipping cart")
    public void openCart() {
        log.info("Open the cart");
        driver.findElement(SHOPPING_CART).click();
    }

    @Step("Select option {value} in the dropdown")
    public void setOptionInSortedDropdown(String value) {
        log.info("Set option {} in the dropdown", value);
        WebElement sortedDropdown = driver.findElement(PRODUCT_SORT_DROPDOWN);
        Select dropdown = new Select(sortedDropdown);
        dropdown.selectByVisibleText(value);
    }

    @Step("Get the names of all products on page")
    public ArrayList<String> getAllProductNamesOnPage() {
        log.info("Get the name of all products on the page");
        ArrayList<WebElement> productsList = new ArrayList<>(driver.findElements(PRODUCT_NAME));
        ArrayList<String> productsName = new ArrayList<>();

        for (int i = 0; i < productsList.size(); i++) {
            String elementName = productsList.get(i).getText();
            productsName.add(elementName);
        }
        return productsName;
    }

    @Step("Get the prices of all products on page")
    public ArrayList<Double> getAllProductPricesOnPage() {
        log.info("Get the prices of all products on the page");
        ArrayList<WebElement> productsList = new ArrayList<>(driver.findElements(PRODUCT_PRICE));
        ArrayList<Double> productsPrice = new ArrayList<>();

        for (int i = 0; i < productsList.size(); i++) {
            String elementPrice = productsList.get(i).getText().replace("$", "");
            productsPrice.add(Double.valueOf(elementPrice));
        }
        return productsPrice;
    }

    @Step("Sort products by option {dropdownOption}")
    public ArrayList<String> sortProductsName(ArrayList<String> productsList, String dropdownOption) {
        log.info("Sort products by {}", dropdownOption);
        ArrayList<String> namesSortedAscending;
        ArrayList<String> namesSortedDescending;


        if (dropdownOption.equals("Name (A to Z)")) {
            Collections.sort(productsList);
            namesSortedAscending = productsList;
            return namesSortedAscending;
        } else {
            Collections.reverse(productsList);
            namesSortedDescending = productsList;
            return namesSortedDescending;
        }
    }

    @Step("Sort products by option {dropdownOption}")
    public ArrayList<Double> sortProductsPrice(ArrayList<Double> productsList, String dropdownOption) {
        log.info("Sort products prices by {}", dropdownOption);
        ArrayList<Double> pricesSortedAscending;
        ArrayList<Double> pricesSortedDescending;

        if (dropdownOption.equals("Price (low to high)")) {
            Collections.sort(productsList);
            pricesSortedAscending = productsList;
            return pricesSortedAscending;
        } else {
            Collections.sort(productsList, Collections.reverseOrder());
            pricesSortedDescending = productsList;
            return pricesSortedDescending;
        }
    }
}
