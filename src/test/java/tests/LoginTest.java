package tests;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test
    public void checkLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getPageTitle(),
                "Products",
                "The page Products was not opened");
    }

    @Test
    public void checkNameBlank() {
        loginPage.open();
        loginPage.login("", "secret_sauce");
        String errorMessage = loginPage.getErrorMessage();
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username is required",
                "The Username is not valid");
    }

    @Test
    public void checkPasswordBlank() {
        loginPage.open();
        loginPage.login("standard_user", "");
        String errorMessage = loginPage.getErrorMessage();
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Password is required",
                "The Password is not valid");
    }

    @Test
    public void checkInvalidPassword() {
        loginPage.open();
        loginPage.login("standard_user", "123456");
        String errorMessage = loginPage.getErrorMessage();
        assertEquals(errorMessage,
                "Epic sadface: Username and password do not match any user in this service",
                "The message is not valid");
    }
}
