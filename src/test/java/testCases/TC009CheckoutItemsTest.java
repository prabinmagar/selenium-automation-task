package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CheckoutPage;
import pageObjects.InventoryPage;
import testBase.BaseClass;

public class TC009CheckoutItemsTest extends BaseClass {
    @Test
    public void verifyCheckoutWithValidDetails() throws InterruptedException {
        logger.info("Starting test: verifyCheckoutWithValidDetails");
        loginToApplication();
        logger.info("Logged in successfully.");

        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertTrue(inventoryPage.isInventoryPageDisplayed(), "Inventory page not loaded.");
        logger.info("Inventory page loaded successfully.");

        inventoryPage.addFirstNProductsToCart(2);
        logger.info("Added first 2 products to the cart.");

        double expectedSubtotal = inventoryPage.getExpectedSubTotalAmount();
        logger.info("Expected subtotal calculated: $" + expectedSubtotal);

        inventoryPage.clickCartIcon();
        logger.info("Clicked on cart icon to proceed to cart page.");

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.clickCheckoutButton();
        checkoutPage.enterFirstName("John");
        checkoutPage.enterLastName("Doe");
        checkoutPage.enterPostalCode("12345");
        logger.info("Filled in checkout form with valid details.");

        checkoutPage.clickContinue();
        logger.info("Clicked Continue to proceed to checkout summary.");

        String subtotalText = checkoutPage.getSubtotalText().replace("Item total: $", "").trim();
        double actualSubtotal = Double.parseDouble(subtotalText);
        logger.info("Actual subtotal retrieved from UI: $" + actualSubtotal);

        Assert.assertEquals(actualSubtotal, expectedSubtotal, "Subtotal mismatch.");
        logger.info("Verified that actual subtotal matches expected subtotal.");

        checkoutPage.finishOrder();
        logger.info("Clicked Finish to complete the order.");

        Assert.assertTrue(checkoutPage.isOrderComplete(), "Order was not completed successfully.");
        logger.info("Order completed successfully. Thank you page verified.");

        logger.info("Test completed: verifyCheckoutWithValidDetails");
    }
}
