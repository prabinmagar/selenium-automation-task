package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC001UserInvalidLoginTest extends BaseClass {
    @Test
    public void verifyUserLogin() {
        logger.info("***** Starting TC001UserLoginTest *****");

        try {
            LoginPage lp = new LoginPage(driver);
            logger.info("LoginPage initialized");

            lp.loginAs("locked_out_user", "secret_sauce");
            logger.info("Clicked login with username: locked_out_user");

            logger.info("Validating error message for locked out user");
            String actualErrorMsg = lp.getErrorMsg();
            String expectedErrorMsg = "Epic sadface: Sorry, this user has been locked out.";

            Assert.assertEquals(actualErrorMsg, expectedErrorMsg,
                    "Error message does not match for locked out user.");

            logger.info("Error message validation passed");

        } catch (Exception e) {
            logger.error("Test case TC001UserLoginTest failed due to exception: " + e.getMessage(), e);
            Assert.fail("Test failed due to unexpected exception: " + e.getMessage());
        }

        logger.info("***** Finished TC001UserLoginTest *****");
    }
}
