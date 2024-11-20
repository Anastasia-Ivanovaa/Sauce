package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;
import pages.CheckoutInformationPage;
import pages.LoginPage;
import pages.ProductsPage;
import pages.ShoppingCartPage;

import java.time.Duration;

public class BaseTest {
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    ShoppingCartPage shoppingCartPage;
    CheckoutInformationPage checkoutInformationPage;

    @Parameters({"browser"})
    @BeforeMethod
    public void setUp(@Optional("firefox") String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            driver = new FirefoxDriver(options);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
        checkoutInformationPage = new CheckoutInformationPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
