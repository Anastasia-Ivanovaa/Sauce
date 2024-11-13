package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {

    private final By PAGE_TITLE = By.cssSelector(".title");
    private final By SHOPPING_CART = By.cssSelector(".shopping_cart_link");

    private final String ADD_TO_CART_PATTERN = "//div[text() = '%s']/ancestor::div[@class = 'inventory_item']//button[text()='Add to cart']";
    private final String REMOVE_FROM_CART_PATTERN = "//div[text() = '%s']/ancestor::div[@class = 'inventory_item']//button[text()='Remove']";

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return driver.findElement(PAGE_TITLE).getText();
    }

    public String getRemoveButton(String product){
        By addToCart = By.xpath(String.format(ADD_TO_CART_PATTERN, product));
        return driver.findElement(addToCart).getText();
    }

    public void clickAddButton(String product) {
        By addToCart = By.xpath(String.format(ADD_TO_CART_PATTERN, product));
        driver.findElement(addToCart).click();
    }

    public void clickRemoveButton(String product) {
        By removeFromCart = By.xpath((String.format(REMOVE_FROM_CART_PATTERN, product)));
        driver.findElement(removeFromCart).click();
    }

    public void openCart() {
        driver.findElement(SHOPPING_CART).click();
    }
}
