package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage extends BasePage {

    private final String ADDED_PRODUCT_NAME_PATTERN = "//div[text() = '%s']/ancestor::div[@class = 'cart_item']//div[@class='inventory_item_name']";
    private final String PRODUCT_PRICE_PATTERN = "//div[text() = '%s']/ancestor::div[@class = 'cart_item']//div[@class='inventory_item_price']";

    private final By PAGE_TITLE = By.cssSelector(".title");
    private final By CANCEL_BUTTON = By.id("cancel");
    private final By FINISH_BUTTON = By.id("finish");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get the title of page")
    public String pageTitle() {
        return driver.findElement(PAGE_TITLE).getText();
    }

    @Step("Get the product name")
    public String getProductName(String product) {
        By productName = By.xpath(String.format(ADDED_PRODUCT_NAME_PATTERN, product));
        return driver.findElement(productName).getText();
    }

    @Step("Click on Cancel button")
    public void clickOnCancelButton() {
        driver.findElement(CANCEL_BUTTON).click();
    }

    @Step("Click on Finish button")
    public void clickOnFinishButton() {
        driver.findElement(FINISH_BUTTON).click();
    }
}
