package pageObjects;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BasePage {
    protected  WebDriver driver;
    protected WebDriverWait wait;

    // CONSTRUCTOR
    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // WAITS
    protected void waitForVisibility(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForClickability(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // ACTIONS
    protected void click(WebElement element){
        waitForClickability(element);
        element.click();
    }

    protected void type(WebElement element, String text){
        waitForVisibility(element);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(WebElement element){
        waitForVisibility(element);
        return element.getText();
    }

    protected boolean isDisplayed(WebElement element){
        try{
            waitForVisibility(element);
            return element.isDisplayed();
        } catch(TimeoutException e){
            return false;
        }
    }

    protected String getCurrentUrl(){
        return driver.getCurrentUrl();
    }
}
