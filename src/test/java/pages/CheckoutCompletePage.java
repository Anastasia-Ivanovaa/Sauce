package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage {

    private final By PAGE_TITLE = By.cssSelector(".title");
    private final By THANK_YOU_MESSAGE = By.className("complete-header");
    private final By BACK_HOME_BUTTON = By.id("back-to-products");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    @Step("Get the title of page")
    public String pageTitle() {
        return driver.findElement(PAGE_TITLE).getText();
    }

    @Step("Click on Back Home button")
    public void clickBackHomeButton() {
        driver.findElement(BACK_HOME_BUTTON).click();
    }

    @Step("Get the text of thank you message")
    public String getThankYouMessage() {
        return driver.findElement(THANK_YOU_MESSAGE).getText();
    }
}
