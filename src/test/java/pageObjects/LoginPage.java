package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends  BasePage{
    // LOCATORS
    @FindBy(xpath = "//input[@id='user-name' and @name='user-name']")
    protected WebElement txtUsername;
    @FindBy(xpath = "//input[@id='password' and @name='password']")
    protected WebElement txtPassword;
    @FindBy(xpath = "//input[@id='login-button' and @type='submit']")
    protected WebElement btnLogin;
    @FindBy(xpath = "//h3[normalize-space()='Epic sadface: Sorry, this user has been locked out.']")
    protected WebElement msgError;

    // CONSTRUCTOR
    public LoginPage(WebDriver driver){
        super(driver);
    }

    // ACTIONS
    public void setUsername(String username){
        type(txtUsername, username);
    }

    public void setPassword(String password){
        type(txtPassword, password);
    }

    public void clickLogin(){
        click(btnLogin);
    }

    public void loginAs(String username, String password){
        setUsername(username);
        setPassword(password);
        clickLogin();
    }

    public String getErrorMsg(){
        return isDisplayed(msgError) ? getText(msgError) : null;
    }
}
