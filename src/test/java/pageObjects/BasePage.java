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

    public void setup(){
        ChromeOptions options = new ChromeOptions();

        //Disable password save prompt, notifications and more
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("profile.default_content_setting_values.automatic_downloads", 1);

        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-features=AutofillAssistant,PasswordManagerOnboarding");
        options.addArguments("--incognito"); // ensures no cached profile or saved credentials

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/v1/");
    }

//    @AfterMethod
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
}
