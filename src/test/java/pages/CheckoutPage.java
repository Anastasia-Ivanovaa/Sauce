package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    private final By PAGE_TITLE = By.cssSelector(".title");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return driver.findElement(PAGE_TITLE).getText();
    }
}
