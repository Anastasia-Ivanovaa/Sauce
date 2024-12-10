package pages;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Slf4j
public class CheckoutInformationPage extends BasePage {

    private final By PAGE_TITLE = By.cssSelector(".title");
    private final By FIRST_NAME_FIELD = By.id("first-name");
    private final By LAST_NAME_FIELD = By.id("last-name");
    private final By POSTAL_CODE_FIELD = By.id("postal-code");
    private final By ERROR_MESSAGE = By.xpath("//h3[@data-test = 'error']");
    private final By CONTINUE_BUTTON = By.id("continue");
    private final By CANCEL_BUTTON = By.id("cancel");

    public CheckoutInformationPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get the title of page")
    public String getPageTitle() {
        return driver.findElement(PAGE_TITLE).getText();
    }

    @Step("Add checkout information first name: {firstName}, last name{lastName}, zip code: {zipCode}")
    public void addCheckoutInfo(String firstName, String lastName, String zipCode) {
        log.info("Add checkout information first name: {}, last name{}, zip code: {}", firstName, lastName, zipCode);
        driver.findElement(FIRST_NAME_FIELD).click();
        driver.findElement(FIRST_NAME_FIELD).sendKeys(firstName);
        driver.findElement(LAST_NAME_FIELD).sendKeys(lastName);
        driver.findElement(POSTAL_CODE_FIELD).sendKeys(zipCode);
        driver.findElement(CONTINUE_BUTTON).click();
    }

    @Step("Click on Cancel button")
    public void returnToShoppingCart() {
        log.info("Return to the cart");
        driver.findElement(CANCEL_BUTTON).click();
    }

    @Step("Get the error message")
    public String getErrorMessage() {
        log.info("Get the error message");
        return driver.findElement(ERROR_MESSAGE).getText();
    }
}
