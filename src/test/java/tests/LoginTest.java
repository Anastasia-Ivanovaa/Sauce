package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test(testName = "Login into the application", description = "Check positive login", priority = 1)
    @Epic("Login into app module")
    @Feature("TMS-25")
    @Story("TMS-25.11")
    @Severity(SeverityLevel.CRITICAL)
    @Link("Https://www.google.com/")
    @Owner("Anastasia Ivanova")
    @Issue("link to bug tracking system")
    @TmsLink("Testcase-10")
    @Description("Check positive login")
    @Flaky
    public void checkLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getPageTitle(),
                "Products",
                "The page Products was not opened");
    }

    @DataProvider(name = "LoginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"", "secret_sauce", "Epic sadface: Username is require"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"standard_user", "123456", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(dataProvider = "LoginData", testName = "Invalid login data")
    @Description("Negative login check")
    public void test(String user, String password, String expectedError) {
        loginPage.open();
        loginPage.login(user, password);
        String errorMessage = loginPage.getErrorMessage();
        assertEquals(errorMessage, expectedError,
                "The Username is not valid");
    }
}
