package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;
import java.util.Objects;

public class CartPage extends BasePage{
    @FindBy(xpath = "//div[@class='cart_item']")
    protected List<WebElement> lstCartItems;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCartPageLoaded() {
        return Objects.requireNonNull(driver.getCurrentUrl()).contains("/cart");
    }

    public void verifyCartItems(List<String> expectedNames, List<String> expectedPrices) {
        Assert.assertEquals(lstCartItems.size(), expectedNames.size(), "Cart items count mismatch.");

        for (int i = 0; i < expectedNames.size(); i++) {
            WebElement item = lstCartItems.get(i);
            String name = item.findElement(By.className("inventory_item_name")).getText().trim();
            String price = item.findElement(By.className("inventory_item_price")).getText().trim();

            Assert.assertEquals(name, expectedNames.get(i), "Cart item name mismatch.");
            Assert.assertEquals(price, expectedPrices.get(i), "Cart item price mismatch.");
        }
    }
}
