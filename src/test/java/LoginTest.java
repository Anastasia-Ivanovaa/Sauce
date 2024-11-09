import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test
    public void checkLogin() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String title = driver.findElement(By.cssSelector(".title")).getText();
        assertEquals(title, "Products", "The page Products was not opened");
    }

    @Test
    public void checkNameBlank() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String correctErrorMessage = "Epic sadface: Username is required";
        String currentErrorMessage = driver.findElement(By.cssSelector("[data-test = error]")).getText();
        assertEquals(currentErrorMessage, correctErrorMessage, "The message is not valid");
    }

    @Test
    public void checkPasswordBlank() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("");
        driver.findElement(By.id("login-button")).click();
        String correctErrorMessage = "Epic sadface: Password is required";
        String currentErrorMessage = driver.findElement(By.cssSelector("[data-test=error]")).getText();
        assertEquals(currentErrorMessage, correctErrorMessage, "The message is not valid");
    }

    @Test
    public void checkInvalidPassword() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("123456");
        driver.findElement(By.id("login-button")).click();
        String correctErrorMessage = "Epic sadface: Username and password do not match any user in this service";
        String currentErrorMessage = driver.findElement(By.cssSelector("[data-test=error]")).getText();
        assertEquals(currentErrorMessage, correctErrorMessage, "The message is not valid");
    }
}
