package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.InventoryPage;
import testBase.BaseClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TC005InventoryAscendingPriceSortingTest extends BaseClass {
    @Test
    public void verifyDefaultSortingIsAscendingByName() throws InterruptedException {
        logger.info("Logging into the application.");
        loginToApplication();  // Ensure user is logged in and on inventory page
        InventoryPage ip = new InventoryPage(driver);
        Assert.assertTrue(ip.isInventoryPageDisplayed(), "Inventory Page is not displayed.");

        logger.info("Selecting sort option: 'Price (low to high)'.");
        ip.selectSortOption("Price (low to high)");

        logger.info("Fetching product prices from the inventory.");
        List<Double> actualPrices = ip.getInventoryItemPrices();
        logger.info("Actual product prices: " + actualPrices);

        logger.info("Sorting product prices in ascending order for expected result.");
        List<Double> expectedSortedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedSortedPrices);
        logger.info("Expected sorted prices: " + expectedSortedPrices);

        Assert.assertEquals(actualPrices, expectedSortedPrices, "Products are not sorted in ascending order by price.");
        logger.info("Products are sorted correctly in ascending order by price.");
    }

}
