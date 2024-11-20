package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test(testName = "Login into the application", description = "Check positive login", priority = 1)
    public void checkLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getPageTitle(),
                "Products",
                "The page Products was not opened");
    }

    @DataProvider(name="LoginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"standard_user", "123456", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test (dataProvider = "LoginData")
    public void test(String user, String password, String expectedError){
        loginPage.open();
        loginPage.login(user, password);
        String errorMessage = loginPage.getErrorMessage();
        assertEquals(loginPage.getErrorMessage(), expectedError,
                "The Username is not valid");
    }
}
