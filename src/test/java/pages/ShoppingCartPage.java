package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShoppingCartPage extends BasePage {

    private final By PAGE_TITLE = By.cssSelector(".title");
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

    public String getProductName() {
        return driver.findElement(By.cssSelector(".inventory_item_name")).getText();
    }

    public String getProductDescription(String product) {
        By productDescription = By.xpath(String.format(PRODUCT_DESCRIPTION_PATTERN, product));
        return driver.findElement(productDescription).getText();
    }

    public String getProductPrice(String product) {
        By productPrice = By.xpath(String.format(PRODUCT_PRICE_PATTERN, product));
        return driver.findElement(productPrice).getText();
    }

    public String getProductQuantity(String product) {
        By productQty = By.xpath(String.format(PRODUCT_QUANTITY_PATTERN, product));
        return driver.findElement(productQty).getText();
    }

    public String getAddedProductQtyToTheCart() {
        return driver.findElement(SHOPPING_CART_BADGE).getText();
    }

    public void clickRemoveButton(String product) {
        By removeFromCart = By.xpath(String.format(REMOVE_FORM_CART_PATTERN, product));
        driver.findElement(removeFromCart).click();
    }

    public void clickOnContinueShoppingButton() {
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
    }

    public void clickOnCheckoutButton() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }

    public boolean shoppingCartIsEmpty() {
        return driver.findElements(By.cssSelector(".inventory_item_name")).isEmpty();
    }
}