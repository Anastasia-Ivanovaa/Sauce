package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class ShoppingCartPage extends BasePage {

    private final By PAGE_TITLE = By.cssSelector(".title");
    private final String ADDED_PRODUCT_NAME_PATTERN = "//div[text() = '%s']/ancestor::div[@class = 'cart_item']//div[@class='inventory_item_name']";
    private final String PRODUCT_PRICE_PATTERN = "//div[text() = '%s']/ancestor::div[@class = 'cart_item']//div[@class='inventory_item_price']";
    private final String PRODUCT_QUANTITY_PATTERN = "//div[text() = '%s']/ancestor::div[@class = 'cart_item']//div[@class='cart_quantity']";
    private final String PRODUCT_DESCRIPTION_PATTERN = "//div[text() = '%s']/ancestor::div[@class = 'cart_item']//div[@class='inventory_item_desc']";
    private final String REMOVE_FORM_CART_PATTERN = "//div[text() = '%s']/ancestor::div[@class = 'cart_item']//button";
    private final By CONTINUE_SHOPPING_BUTTON = By.id("continue-shopping");
    private final By CHECKOUT_BUTTON = By.id("checkout");
    private final By SHOPPING_CART_BADGE = By.xpath("//div//span[@class='shopping_cart_badge']");

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get the name of product that was added to the cart")
    public String getAddedProductName(String product) {
        log.info("Get the name of product in the cart");
        By productName = By.xpath(String.format(ADDED_PRODUCT_NAME_PATTERN, product));
        return driver.findElement(productName).getText();
    }

    @Step("Get the title of page")
    public String getPageTitle() {
        return driver.findElement(PAGE_TITLE).getText();
    }

    @Step("Get the product description")
    public String getProductDescription(String product) {
        log.info("Get the description of {} product", product);
        By productDescription = By.xpath(String.format(PRODUCT_DESCRIPTION_PATTERN, product));
        return driver.findElement(productDescription).getText();
    }

    @Step("Get the price of product {product}")
    public String getProductPrice(String product) {
        log.info("Get the price of {} product", product);
        By productPrice = By.xpath(String.format(PRODUCT_PRICE_PATTERN, product));
        return driver.findElement(productPrice).getText();
    }

    @Step("Get the quantity of product {product}")
    public String getProductQuantity(String product) {
        log.info("Get the quantity of {} product", product);
        By productQty = By.xpath(String.format(PRODUCT_QUANTITY_PATTERN, product));
        return driver.findElement(productQty).getText();
    }

    @Step("Get the quantity of product that was added")
    public String getAddedProductQtyToTheCart() {
        log.info("Get the qty products in the cart");
        return driver.findElement(SHOPPING_CART_BADGE).getText();
    }

    @Step("Click on Remove button")
    public void clickRemoveButton(String product) {
        log.info("Remove product {} from the cart", product);
        By removeFromCart = By.xpath(String.format(REMOVE_FORM_CART_PATTERN, product));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(removeFromCart)).click();
//          driver.findElement(removeFromCart).click();
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("Remove button isn't found");
        }
    }

    @Step("Click on Continue Shopping button")
    public void clickOnContinueShoppingButton() {
        log.info("Click on Continue Shopping button");
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
    }

    @Step("Click on Checkout button")
    public void clickOnCheckoutButton() {
        log.info("Click on Checkout button");
        driver.findElement(CHECKOUT_BUTTON).click();
    }

    @Step("Check that cart is empty")
    public boolean shoppingCartIsEmpty() {
        log.info("Check that cart is empty");
        return driver.findElements(By.cssSelector(".inventory_item_name")).isEmpty();
    }
}