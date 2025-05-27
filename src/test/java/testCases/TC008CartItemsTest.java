package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CartPage;
import pageObjects.InventoryPage;
import testBase.BaseClass;

public class TC008CartItemsTest extends BaseClass {
    @Test
    public void verifyCartAfterAddingProducts() throws InterruptedException {
        logger.info("Logging into the application.");
        loginToApplication();
        InventoryPage ip = new InventoryPage(driver);
        Assert.assertTrue(ip.isInventoryPageDisplayed(), "Inventory Page is not displayed.");

        logger.info("Adding first 3 products to the cart.");
        ip.addFirstNProductsToCart(3);

        logger.info("Clicking on the cart icon to navigate to the cart page.");
        ip.clickCartIcon();

        CartPage cartPage = new CartPage(driver);

        logger.info("Verifying that the Cart Page is loaded.");
        Assert.assertTrue(cartPage.isCartPageLoaded(), "Cart page is not loaded properly.");
        logger.info("Cart Page is loaded successfully.");

        logger.info("Verifying cart items against expected product names and prices.");
        cartPage.verifyCartItems(ip.getExpectedProductNames(), ip.getExpectedProductPrices());
        logger.info("Cart items match the expected product names and prices.");
    }

}
