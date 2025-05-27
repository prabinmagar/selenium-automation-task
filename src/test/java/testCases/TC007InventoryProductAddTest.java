package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.InventoryPage;
import testBase.BaseClass;

public class TC007InventoryProductAddTest extends BaseClass {
    @Test
    public void addMultipleProductsToCart() throws InterruptedException {
        logger.info("Logging into the application.");
        loginToApplication();
        InventoryPage ip = new InventoryPage(driver);
        Assert.assertTrue(ip.isInventoryPageDisplayed(), "Inventory Page is not displayed.");

        int productsToAdd = 3;
        logger.info("Adding the first " + productsToAdd + " products to the cart.");
        ip.addFirstNProductsToCart(productsToAdd);

        logger.info("Retrieving actual cart item count.");
        int actualCartCount = ip.getCartItemCount();
        logger.info("Actual cart item count: " + actualCartCount);

        Assert.assertEquals(actualCartCount, productsToAdd, "Cart item count does not match expected number of added products.");
        logger.info("Cart item count matches expected number of added products.");
    }

}
