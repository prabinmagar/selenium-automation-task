package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC002UserValidLoginTest extends BaseClass {
    @Test
    public void verifyUserLogin() {
        logger.info("***** Starting TC002UserLoginTest *****");

        try {
            LoginPage lp = new LoginPage(driver);
            logger.info("LoginPage initialized");

            String username = p.getProperty("username");
            String password = p.getProperty("password");
            logger.info("Logging in with valid credentials");
            lp.loginAs(username, password);

            Thread.sleep(2000);
            String expectedUrl = "https://www.saucedemo.com/v1/inventory.html";
            String actualUrl = driver.getCurrentUrl();
            Assert.assertEquals(actualUrl, expectedUrl, "Login failed: URL did not match.");
        } catch (Exception e) {
            logger.error("Test case TC002UserLoginTest failed due to exception: " + e.getMessage(), e);
            Assert.fail("Test failed due to unexpected exception: " + e.getMessage());
        }

        logger.info("***** Finished TC002UserLoginTest *****");
    }
}
