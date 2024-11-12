package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ShoppingCartPage extends BasePage {

    private final By PAGE_TITLE = By.cssSelector(".title");
    private final By CONTINUE_SHOPPING_BUTTON = By.id("continue-shopping");
    private final By CHECKOUT_BUTTON = By.id("checkout");

    private final By PRODUCT_DESCRIPTION = By.xpath("//div[@class='inventory_item_desc']");
    private final By PRODUCT_QUANTITY = By.xpath("//div[@class='cart_quantity']");
    private final By PRODUCT_PRICE = By.xpath("//div[@class='inventory_item_price']");

    private final String REMOVE_FORM_CART_PATTERN = "//div[text() = '%s']/ancestor::div[@class = 'cart_item']//button";
    private final String PRODUCT_PRICE_PATTERN = "//div[text() = '%s']/parent::div[@class = 'item_pricebar']//div";
    private final String PRODUCT_NAME_PATTERN = "//div[text() = '%s']/ancestor::div[@class = 'cart_item']//div";

    public String getPRODUCT_NAME_PATTERN() {
        return PRODUCT_NAME_PATTERN;
    }

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return driver.findElement(PAGE_TITLE).getText();
    }

    public String getProductName() {
        return driver.findElement(By.cssSelector(".inventory_item_name")).getText();
    }

    public String getProductPrice() {
        return driver.findElement(PRODUCT_PRICE).getText();
    }

    public String getProductQuantity() {
        return driver.findElement(PRODUCT_QUANTITY).getText();
    }

    public String getProductDescription() {
        return driver.findElement(PRODUCT_DESCRIPTION).getText();
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