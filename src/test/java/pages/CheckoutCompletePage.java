package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage {

    private final By PAGE_TITLE = By.cssSelector(".title");
    private final By THANK_YOU_MESSAGE = By.className("complete-header");
    private final By BACK_HOME_BUTTON = By.id("back-to-products");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public String pageTitle() {
        return driver.findElement(PAGE_TITLE).getText();
    }

    public void clickBackHomeButton() {
        driver.findElement(BACK_HOME_BUTTON).click();
    }

    public String getThankYouMessage() {
        return driver.findElement(THANK_YOU_MESSAGE).getText();
    }
}
