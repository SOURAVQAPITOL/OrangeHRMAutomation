package com.orangehrm.pages;

import com.orangehrm.base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseTest {

    public WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@placeholder='Username']")
    WebElement userName;

    @FindBy(xpath = "//input[@placeholder='Password']")
    WebElement password;

    @FindBy(xpath= "//button[@type='submit']")
    WebElement loginButton;

    public void loginToTheApplication(){
        userName.sendKeys(prop.getProperty("username"));
        password.click();
        password.sendKeys(prop.getProperty("password"));
        loginButton.submit();
    }
}
