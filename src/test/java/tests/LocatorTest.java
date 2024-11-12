package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.util.List;

public class LocatorTest extends BaseTest {

    @Test
    public void locatorTest() {
        driver.get("https://www.saucedemo.com/");
        WebElement userNameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement logoOnLoginPage = driver.findElement(By.className("login_logo"));
        WebElement bodyOnLoginPage = driver.findElement(By.tagName("body"));
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        WebElement theFirstItemName = driver.findElement(By.linkText("Sauce Labs Backpack"));
        WebElement theSecondItemName = driver.findElement(By.partialLinkText("Bike"));

        /*xpath*/
        WebElement theFirstItemNameTwo = driver.findElement(By.xpath("//div/a[@id='item_4_title_link']"));
        WebElement theFirstItemDescription = driver.findElement(By.xpath("//div[text()='carry.allTheThings()"+
                        " with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.']"));
        WebElement logoOnProductsPage = driver.findElement(By.xpath("//div[contains(@class,'logo')]"));
        WebElement theSecondItemDescription = driver.findElement(By.xpath("//div[contains(text(),'1 AAA battery included.')]"));
        WebElement footerOnProductsPage = driver.findElement(By.xpath("//a//ancestor::footer[@class='footer']"));
        WebElement twitterElement = driver.findElement(By.xpath("//a//parent::li[@class='social_twitter']"));
        WebElement footerText = driver.findElement(By.xpath("//footer//child::div"));
        WebElement facebookLink = driver.findElement(By.xpath("//footer//descendant::a[text()='Facebook']"));
        WebElement theSecondItemDescription2 = driver.findElement(By.xpath("//div[text()='Sauce Labs Bike Light']//following::div[contains(text()," +
                "'riding your bike at night')]"));
        WebElement facebookElement = driver.findElement(By.xpath("//li[@class='social_linkedin']//preceding::li[@class='social_facebook']"));
        WebElement SauceLabsFleeceJacketItemName = driver.findElement(By.xpath("//div[@class='inventory_item_name ' and text()='Sauce Labs Fleece Jacket']"));

        /*cssSelector*/
        WebElement logoSwagLabs1 = driver.findElement(By.cssSelector(".app_logo"));
        WebElement logoSwagLabs2 = driver.findElement(By.cssSelector(".header_label .app_logo"));
        WebElement burgerButton = driver.findElement(By.cssSelector("#react-burger-menu-btn"));
        WebElement body = driver.findElement(By.cssSelector("body"));
        WebElement footer = driver.findElement(By.cssSelector("footer.footer"));
        WebElement addToCartButtonForSauceLabsOnesie = driver.findElement(By.cssSelector("[id=add-to-cart-sauce-labs-onesie]"));
        WebElement pageName = driver.findElement(By.cssSelector("[data-test~='title']"));
        WebElement headerOnProductsPage = driver.findElement(By.cssSelector("[data-test|='header']"));
        List<WebElement> socialNetworksButtons = driver.findElements(By.cssSelector("[rel^='noreferrer']"));
        List<WebElement> itemsPrices = driver.findElements(By.cssSelector("[data-test$='price']"));
        List<WebElement> imagesLinks = driver.findElements(By.cssSelector("[data-test*='img-link']"));
        driver.findElement(By.cssSelector("#react-burger-menu-btn")).click();
        WebElement closeButtonInMenuBlock= driver.findElement(By.cssSelector("[alt~='Close']"));
        driver.get("https://www.saucedemo.com/");
        WebElement loginButton = driver.findElement(By.cssSelector(".submit-button.btn_action"));
   }
}


