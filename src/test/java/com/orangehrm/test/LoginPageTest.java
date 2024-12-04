package com.orangehrm.test;

import com.orangehrm.base.BaseTest;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    public LoginPage loginPage;
    public HomePage homePage;

    @BeforeTest
    public void setUp() {
        initialization();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

    @AfterTest
    public void tearDown() {
        quitTheDriver();
    }

    @Test
    public void loginToTheApplication() throws InterruptedException {
        loginPage.loginToTheApplication();
        Assert.assertTrue(homePage.verifyTheHomePage());
    }
}
