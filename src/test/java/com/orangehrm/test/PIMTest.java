package com.orangehrm.test;

import com.orangehrm.base.BaseTest;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.pages.PIMPage;
import com.orangehrm.utility.ExcelDataDriven;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class PIMTest extends BaseTest {

    String filePath = System.getProperty("user.dir") + "//src//test//resources//Files//addEmployee.xlsx";
    ExcelDataDriven reader;
    public LoginPage loginPage;
    public HomePage homePage;
    public PIMPage pimPage;

    @BeforeTest
    public void setUp() {
        initialization();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        pimPage = new PIMPage(driver);
    }

    @AfterTest
    public void tearDown() {
        quitTheDriver();
    }

    @DataProvider(name = "testData")
    public Object[][] getMydata() throws IOException {
        reader = new ExcelDataDriven();
        return reader.getExcelData(filePath);
    }

    @Test(dataProvider = "testData")
    public void verifyTheUserIsAbleToAddTheEmployeeInPIMModule(String firstName,String middleName, String lastName, String empID, String userName, String password, String confirmPassword) {
        loginPage.loginToTheApplication();
        Assert.assertTrue(homePage.verifyTheHomePage());
        pimPage.navigateToPIMTab();
        Assert.assertTrue(pimPage.verifyThePimHeaderText());
        pimPage.clickOnAddEmployee();
        Assert.assertTrue(pimPage.verifyTheAddEmployeePage());
        pimPage.addDetailsForTheEmployee(firstName,middleName,lastName,empID,userName,password,confirmPassword);
        Assert.assertTrue(pimPage.verifyTheSuccessMessage());
    }

    @Test(dependsOnMethods = "verifyTheUserIsAbleToAddTheEmployeeInPIMModule")
    public void verifyTheUserIsAbleToSearchTheListedEmployee() throws IOException, InterruptedException {
        pimPage.clickOnEmployeeList();
        pimPage.verifyTheEmployeeInformationPage();
        Thread.sleep(2000);
        pimPage.searchTheEmployeeName(reader.getValueAtIndex(filePath,0,0));
        Thread.sleep(3000);
        Assert.assertTrue(pimPage.verifyTheRecordIsPresent());
    }

    @Test
    public void verifyTheUserIsUnableToAddTheEmployeeWithoutFirstAndLastName() throws InterruptedException {
        loginPage.loginToTheApplication();
        Assert.assertTrue(homePage.verifyTheHomePage());
        pimPage.navigateToPIMTab();
        Assert.assertTrue(pimPage.verifyThePimHeaderText());
        pimPage.clickOnAddEmployee();
        Assert.assertTrue(pimPage.verifyTheAddEmployeePage());
        Thread.sleep(2000);
        pimPage.addDetailsForTheEmployeeWithoutFirstAndLastNAme();
        Thread.sleep(3000);
        Assert.assertTrue(pimPage.verifyTheErrorMessage());
    }

    @Test
    public void verifyThe(){
        
    }
}
