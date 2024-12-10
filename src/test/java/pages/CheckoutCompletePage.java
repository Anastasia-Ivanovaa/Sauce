package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
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
        log.info("Click on Back button");
        driver.findElement(BACK_HOME_BUTTON).click();
    }

    @Step("Get the text of thank you message")
    public String getThankYouMessage() {
        log.info("Get the message on the page after completing the checkout");
        return driver.findElement(THANK_YOU_MESSAGE).getText();
    }
}
