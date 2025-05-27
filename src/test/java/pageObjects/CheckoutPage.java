package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {
    @FindBy(xpath = "//input[@id='first-name']")
    private WebElement txtFirstName;

    @FindBy(xpath = "//input[@id='last-name']")
    private WebElement txtLastName;

    @FindBy(xpath = "//input[@id='postal-code']")
    private WebElement txtPostalCode;

    @FindBy(xpath = "//a[contains(@class,'checkout_button')]")
    private WebElement btnCheckout;

    @FindBy(xpath = "//input[contains(@class,'cart_button')]")
    private WebElement btnCheckoutContinue;

    @FindBy(xpath = "//a[contains(@class, 'cart_button') and normalize-space() = 'FINISH']")
    private WebElement btnFinish;

    @FindBy(xpath = "//h2[@class='complete-header']")
    private WebElement lblCompleteHeader;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void fillCheckoutInfo(String firstName, String lastName, String postalCode) {
        type(txtFirstName, firstName);
        type(txtLastName, lastName);
        type(txtPostalCode, postalCode);
        click(btnCheckoutContinue);
    }

    public void finishOrder() {
        click(btnFinish);
    }

    public boolean isOrderComplete() {
        return isDisplayed(lblCompleteHeader) && getText(lblCompleteHeader).equalsIgnoreCase("Thank you for your order!");
    }
}
