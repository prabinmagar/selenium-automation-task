package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {
    @FindBy(xpath = "//input[@id='first-name']")
    protected WebElement txtFirstName;

    @FindBy(xpath = "//input[@id='last-name']")
    protected WebElement txtLastName;

    @FindBy(xpath = "//input[@id='postal-code']")
    protected WebElement txtPostalCode;

    @FindBy(xpath = "//a[contains(@class,'checkout_button')]")
    protected WebElement btnCheckout;

    @FindBy(xpath = "//input[contains(@class,'cart_button')]")
    protected WebElement btnCheckoutContinue;

    @FindBy(xpath = "//a[contains(@class, 'cart_button') and normalize-space() = 'FINISH']")
    protected WebElement btnFinish;

    @FindBy(xpath = "//h2[@class='complete-header']")
    protected WebElement lblCompleteHeader;

    @FindBy(xpath = "//div[@class='summary_subtotal_label']")
    protected WebElement lblSubtotal;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void clickCheckoutButton() {
        waitForClickability(btnCheckout);
        click(btnCheckout);
    }

    public void enterFirstName(String firstName) {
        waitForVisibility(txtFirstName);
        type(txtFirstName, firstName);
    }

    public void enterLastName(String lastName) {
        waitForVisibility(txtLastName);
        type(txtLastName, lastName);
    }

    public void enterPostalCode(String postalCode) {
        waitForVisibility(txtPostalCode);
        type(txtPostalCode, postalCode);
    }

    public void clickContinue() {
        waitForClickability(btnCheckoutContinue);
        click(btnCheckoutContinue);
    }

    public String getSubtotalText() {
        waitForVisibility(lblSubtotal);
        return getText(lblSubtotal);
    }

    public void finishOrder() {
        waitForClickability(btnFinish);
        click(btnFinish);
    }

    public boolean isOrderComplete() {
        return isDisplayed(lblCompleteHeader) && getText(lblCompleteHeader).equalsIgnoreCase("THANK YOU FOR YOUR ORDER");
    }
}
