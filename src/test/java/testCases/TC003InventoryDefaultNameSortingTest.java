package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.InventoryPage;
import testBase.BaseClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TC003InventoryDefaultNameSortingTest extends BaseClass {
    @Test
    public void verifyDefaultSortingIsAscendingByName() throws InterruptedException {
        logger.info("Logging into the application.");
        loginToApplication();  // Ensure user is logged in and on inventory page
        InventoryPage ip = new InventoryPage(driver);

        Assert.assertTrue(ip.isInventoryPageDisplayed(), "Inventory Page is not displayed.");

        logger.info("Retrieving selected sort option from the dropdown.");
        String selectedOption = ip.getSelectedSortOption();
        logger.info("Selected sort option: " + selectedOption);
        Assert.assertEquals(selectedOption, "Name (A to Z)", "Default sort option is not 'Name (A to Z)'.");
        logger.info("Verified that the default sort option is 'Name (A to Z)'.");

        logger.info("Fetching product names from the inventory.");
        List<String> actualNames = ip.getInventoryItemNames();
        logger.info("Actual product names: " + actualNames);

        logger.info("Sorting product names in ascending order for expected result.");
        List<String> expectedSortedNames = new ArrayList<>(actualNames);
        Collections.sort(expectedSortedNames);
        logger.info("Expected sorted names: " + expectedSortedNames);

        Assert.assertEquals(actualNames, expectedSortedNames, "Products are not sorted in ascending order by name.");
        logger.info("Products are sorted correctly in ascending order by name.");
    }
}
