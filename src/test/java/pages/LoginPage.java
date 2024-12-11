package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private final By USER_INPUT = By.id("user-name");
    private final By PASSWORD_INPUT = By.id("password");
    private final By LOGIN_BUTTON = By.id("login-button");
    private final By ERROR_MESSAGE = By.xpath("//h3[@data-test = 'error']");

    @Step("Open Login page")
    public void open() {
        log.info("Login page is opened");
        driver.get("https://www.saucedemo.com/");
    }

    @Step("Log into system using {user} and password {password}")
    public void login(String user, String password) {
        log.info("Log into application using credentials '{}' '{}'", user, password);
        driver.findElement(USER_INPUT).sendKeys(user);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

    @Step("Get the error message")
    public String getErrorMessage() {
        log.info("Get the error message");
        return driver.findElement(ERROR_MESSAGE).getText();
    }
}
