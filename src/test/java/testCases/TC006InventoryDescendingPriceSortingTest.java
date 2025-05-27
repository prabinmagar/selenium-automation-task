package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.InventoryPage;
import testBase.BaseClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TC006InventoryDescendingPriceSortingTest extends BaseClass {
    @Test
    public void verifySortingIsDescendingByPrice() throws InterruptedException {
        logger.info("Logging into the application.");
        loginToApplication();  // Ensure user is logged in and on inventory page
        InventoryPage ip = new InventoryPage(driver);
        Assert.assertTrue(ip.isInventoryPageDisplayed(), "Inventory Page is not displayed.");

        logger.info("Selecting sort option: 'Price (high to low)'.");
        ip.selectSortOption("Price (high to low)");

        logger.info("Fetching product prices from the inventory.");
        List<Double> actualPrices = ip.getInventoryItemPrices();
        logger.info("Actual product prices: " + actualPrices);

        logger.info("Sorting product prices in descending order for expected result.");
        List<Double> expectedSortedPrices = new ArrayList<>(actualPrices);
        expectedSortedPrices.sort(Collections.reverseOrder());
        logger.info("Expected sorted prices: " + expectedSortedPrices);

        Assert.assertEquals(actualPrices, expectedSortedPrices, "Products are not sorted in descending order by price.");
        logger.info("Products are sorted correctly in descending order by price.");
    }
}
