package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class InventoryPage extends BasePage {
    // LOCATORS
    @FindBy(xpath = "//select[@class='product_sort_container']")
    protected WebElement ddlProductSort;

    @FindBy(xpath = "//div[@class='inventory_item']")
    protected List<WebElement> lstInventoryItems;

    @FindBy(xpath = "//a[contains(@class, 'shopping_cart_link')]")
    protected WebElement lnkShoppingCart;

    protected final List<String> expectedProductNames = new ArrayList<>();
    protected final List<String> expectedProductPrices = new ArrayList<>();
    protected double expectedSubTotalAmount = 0.0;

    // CONSTRUCTOR
    public InventoryPage(WebDriver driver){
        super(driver);
    }

    public boolean isInventoryPageDisplayed() {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl != null && currentUrl.contains("/inventory");
    }

    public void selectSortOption(String visibleText){
        Select dropdown = new Select(ddlProductSort);
        dropdown.selectByVisibleText(visibleText);
    }

    public String getSelectedSortOption(){
        Select dropdown = new Select(ddlProductSort);
        return dropdown.getFirstSelectedOption().getText().trim();
    }

    public List<String> getInventoryItemNames(){
        List<String> names = new ArrayList<>();
        for(WebElement item: lstInventoryItems){
            String name = item.findElement(By.className("inventory_item_name")).getText().trim();
            names.add(name);
        }

        return names;
    }

    public List<Double> getInventoryItemPrices(){
        List<Double> prices = new ArrayList<>();
        for(WebElement item: lstInventoryItems){
            Double price = Double.valueOf(item.findElement(By.className("inventory_item_price")).getText().trim().replace("$", ""));
            prices.add(price);
        }

        return prices;
    }

    public void addFirstNProductsToCart(int count) {
        wait.until(ExpectedConditions.visibilityOfAllElements(lstInventoryItems));

        if (lstInventoryItems == null || lstInventoryItems.isEmpty()) {
            throw new RuntimeException("No products found on the inventory page!");
        }

        for (int i = 0; i < count && i < lstInventoryItems.size(); i++) {
            WebElement product = lstInventoryItems.get(i);
            String name = product.findElement(By.className("inventory_item_name")).getText().trim();
            String price = product.findElement(By.className("inventory_item_price")).getText().trim().replace("$", "");

            expectedProductNames.add(name);
            expectedProductPrices.add(price);
            expectedSubTotalAmount += Double.parseDouble(price);

            WebElement addBtn = product.findElement(By.cssSelector("button.btn_inventory"));
            click(addBtn);
        }
    }

    public int getCartItemCount() {
        try {
            WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
            return Integer.parseInt(cartBadge.getText().trim());
        } catch (NoSuchElementException e) {
            return 0;
        }
    }

    public void clickCartIcon() {
        click(lnkShoppingCart);
    }

    public List<String> getExpectedProductNames() {
        return expectedProductNames;
    }

    public List<String> getExpectedProductPrices() {
        return expectedProductPrices;
    }

    public double getExpectedSubTotalAmount() {
        return expectedSubTotalAmount;
    }
}
