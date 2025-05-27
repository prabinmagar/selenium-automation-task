package testBase;

import net.bytebuddy.utility.RandomString;
import org.apache.logging.log4j.LogManager; // Log4j
import org.apache.logging.log4j.Logger; // Log4j
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.LoginPage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

public class BaseClass {
    protected WebDriver driver;
    protected WebDriverWait wait;
    public Logger logger;
    public Properties p;

    @BeforeClass
    @Parameters({"os", "browser"})
    public void setup(@Optional("windows") String os, @Optional("chrome") String browser) throws IOException {
        // Loading config.properties file
        FileReader file = new FileReader("./src//test//resources//config.properties");
        p = new Properties();
        p.load(file);

        logger = LogManager.getLogger(this.getClass());
        logger.info("Initializing WebDriver for browser: " + browser + " on OS: " + os);

        try {
            switch (browser.toLowerCase()) {
                case "chrome":
                    ChromeOptions chromeOptions = getChromeOptions();
                    driver = new ChromeDriver(chromeOptions);
                    break;

                case "edge":
                    driver = new EdgeDriver();
                    break;

                case "firefox":
                    driver = new FirefoxDriver();
                    break;

                default:
                    logger.error("Invalid browser name provided: " + browser);
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

            // Common configuration for all browsers
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            driver.manage().window().maximize();
            driver.get(p.getProperty("appURL")); // reading url from properties file
            logger.info("Navigated to application URL");

        } catch (Exception e) {
            logger.error("WebDriver setup failed: " + e.getMessage(), e);
            throw new RuntimeException("Failed to initialize WebDriver", e);
        }
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = getOptions();
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--disable-save-password-bubble");
        chromeOptions.addArguments("--disable-features=AutofillAssistant,PasswordManagerOnboarding");
        chromeOptions.addArguments("--incognito");
        return chromeOptions;
    }

    private static ChromeOptions getOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();

        // Disable password prompts, notifications, and other popups
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("profile.default_content_setting_values.automatic_downloads", 1);

        chromeOptions.setExperimentalOption("prefs", prefs);
        return chromeOptions;
    }

    @AfterClass
    public void tearDown(){
        if (driver != null) {
            driver.quit();
        }
    }

    public void loginToApplication() throws InterruptedException {
        try {
            LoginPage lp = new LoginPage(driver);
            String username = p.getProperty("username");
            String password = p.getProperty("password");
            logger.info("Logging in with valid credentials");
            lp.loginAs(username, password);

            Thread.sleep(2000);
            String expectedUrl = "https://www.saucedemo.com/v1/inventory.html";
            String actualUrl = driver.getCurrentUrl();
            Assert.assertEquals(actualUrl, expectedUrl, "Login failed: URL did not match.");
        } catch (Exception e) {
            logger.error("Login failed due to exception: " + e.getMessage(), e);
            Assert.fail("Login failed due to unexpected exception: " + e.getMessage());
        }
    }

    @AfterMethod
    public void waitAfterTestMethod() throws InterruptedException {
        logger.info("Waiting for 2 seconds before next test...");
        Thread.sleep(2000);
    }

    // RANDOM DATA GENERATOR - IF NEEDED
    public String randomString(int length){
        return RandomString.make(length);
    }

    public String randomNumericString(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10)); // 0-9
        }
        return sb.toString();
    }

    public String randomAlphaNumeric(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random rnd = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(rnd.nextInt(characters.length())));
        }
        return sb.toString();
    }

    public String randomEmail() {
        return "user_" + randomAlphaNumeric(6) + "@testmail.com";
    }

    public String randomPassword() {
        return "Pwd@" + randomAlphaNumeric(8);
    }
}
