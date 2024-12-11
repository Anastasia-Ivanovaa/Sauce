package tests;

import io.qameta.allure.*;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Log4j2
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
        loginPage.login(user, password);
        assertEquals(productsPage.getPageTitle(),
                "Products",
                "The page Products was not opened");
    }

    @DataProvider(name = "LoginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"", password, "Epic sadface: Username is required"},
                {user, "", "Epic sadface: Password is required"},
                {user, "123456", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(dataProvider = "LoginData", testName = "Invalid login data", description = "Check that user cannot login with invalid data" )
    @Description("Negative login check")
    public void test(String user, String password, String expectedError) {
        loginPage.open();
        loginPage.login(user, password);
        String errorMessage = loginPage.getErrorMessage();
        assertEquals(errorMessage, expectedError,
                "The Username is not valid");
    }
}
