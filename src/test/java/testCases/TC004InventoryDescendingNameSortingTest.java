package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.InventoryPage;
import testBase.BaseClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TC004InventoryDescendingNameSortingTest extends BaseClass {
    @Test
    public void verifyProductSortingDescendingByName() throws InterruptedException {
        logger.info("Logging into the application.");
        loginToApplication();  // Ensure user is logged in and on inventory page
        InventoryPage ip = new InventoryPage(driver);
        Assert.assertTrue(ip.isInventoryPageDisplayed(), "Inventory Page is not displayed.");

        logger.info("Selecting sort option: 'Name (Z to A)'.");
        ip.selectSortOption("Name (Z to A)");

        logger.info("Fetching product names from the inventory.");
        List<String> actualNames = ip.getInventoryItemNames();
        logger.info("Actual product names: " + actualNames);

        logger.info("Sorting product names in descending order for expected result.");
        List<String> expectedSortedNames = new ArrayList<>(actualNames);
        expectedSortedNames.sort(Collections.reverseOrder());
        logger.info("Expected sorted names: " + expectedSortedNames);

        Assert.assertEquals(actualNames, expectedSortedNames, "Products are not sorted in descending order by name.");
        logger.info("Products are sorted correctly in descending order by name.");
    }

}
