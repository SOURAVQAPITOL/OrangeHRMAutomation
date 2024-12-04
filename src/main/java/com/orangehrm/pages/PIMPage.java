package com.orangehrm.pages;

import com.orangehrm.base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Random;

public class PIMPage extends BaseTest {

    public WebDriver driver;
    GenericMethod genericMethod;
    Random random;

    public PIMPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[text()='PIM']")
    WebElement pimTab;

    @FindBy(css = ".oxd-text.oxd-text--h6.oxd-topbar-header-breadcrumb-module")
    WebElement pimHeaderText;

    @FindBy(xpath = "//a[text()='Employee List']")
    WebElement employeeList;

    @FindBy(xpath = "//a[text()='Add Employee']")
    WebElement addEmployee;

    @FindBy(xpath = "//a[text()='Reports']")
    WebElement reports;

    @FindBy(xpath = "//h6[text()='Add Employee']")
    WebElement addEmployeeText;

    @FindBy(xpath = "//input[@placeholder='First Name']")
    WebElement firsttName;

    @FindBy(xpath = "//input[@placeholder='Middle Name']")
    WebElement middleeName;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    WebElement lasttName;

    @FindBy(xpath = "//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']")
    WebElement employeeID;

    @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[3]")
    WebElement userNameForAddEmp;

    @FindBy(xpath = "(//input[@type='password'])[1]")
    WebElement passwordForAddEmp;

    @FindBy(xpath = "(//input[@type='password'])[2]")
    WebElement confirmPasswordForAddEmp;

    @FindBy(xpath = "//span[@class='oxd-switch-input oxd-switch-input--active --label-right']")
    WebElement toggleButton;

    @FindBy(xpath = "//button[contains(text(),Save) and @type='submit']")
    WebElement saveButton;

    @FindBy(css = ".oxd-text.oxd-text--p.oxd-text--toast-message.oxd-toast-content-text")
    WebElement successMsgForAddEmp;

    @FindBy(xpath = "//h5[text()='Employee Information']")
    WebElement employeeInformationText;

    @FindBy(xpath = "(//input[@placeholder='Type for hints...'])[1]")
    WebElement employeeName;

    @FindBy(xpath = "(//div[@class='oxd-autocomplete-option'])[1]")
    WebElement selectFirstEmp;

    @FindBy(css = ".oxd-button.oxd-button--medium.oxd-button--secondary.orangehrm-left-space")
    WebElement searchButton;

    @FindBy(xpath = "//span[text()='(1) Record Found']")
    WebElement searchRecord;

    @FindBy(xpath = "(//span[text()='Required'])[1]")
    WebElement requiredField;

    public void navigateToPIMTab() {
        genericMethod = new GenericMethod();
        genericMethod.isElementPresent(pimTab);
        pimTab.click();
    }

    public boolean verifyThePimHeaderText() {
        return pimHeaderText.isDisplayed();
    }

    public void clickOnAddEmployee() {
        addEmployee.click();
    }

    public boolean verifyTheAddEmployeePage() {
        return addEmployeeText.isDisplayed();
    }

    public void addDetailsForTheEmployee(String firstName, String middleName, String lastName, String empID,
                                         String userName, String password, String confirmPassword) {
        firsttName.click();
        firsttName.sendKeys(firstName);
        middleeName.click();
        middleeName.sendKeys(middleName);
        lasttName.click();
        lasttName.sendKeys(lastName);
        employeeID.clear();
        if (toggleButton.isDisplayed()) {
            toggleButton.click();
        } else {
            toggleButton.click();
        }
        userNameForAddEmp.sendKeys(userName);
        passwordForAddEmp.click();
        passwordForAddEmp.sendKeys(password);
        confirmPasswordForAddEmp.click();
        confirmPasswordForAddEmp.sendKeys(confirmPassword);
        saveButton.submit();
    }

    public boolean verifyTheSuccessMessage() {
        return successMsgForAddEmp.isDisplayed();
    }

    public void clickOnEmployeeList() {
        genericMethod.isElementPresent(employeeList);
        employeeList.click();
    }

    public boolean verifyTheEmployeeInformationPage() {
        return employeeInformationText.isDisplayed();
    }

    public void searchTheEmployeeName(String employeName) {
        employeeName.isDisplayed();
        employeeName.click();
        employeeName.sendKeys(employeName);
        selectFirstEmp.click();
        searchButton.submit();
    }

    public boolean verifyTheRecordIsPresent() {
        genericMethod.scrollTillTheElementToVisible(searchRecord);
        return searchRecord.isDisplayed();
    }

    public void addDetailsForTheEmployeeWithoutFirstAndLastNAme() {
        genericMethod.isElementPresent(middleeName);
        middleeName.click();
        middleeName.sendKeys(prop.getProperty("middleName"));
        lasttName.click();
        employeeID.clear();
        if (toggleButton.isDisplayed()) {
            toggleButton.click();
        } else {
            toggleButton.click();
        }
        userNameForAddEmp.sendKeys(prop.getProperty("userName"));
        passwordForAddEmp.click();
        passwordForAddEmp.sendKeys(prop.getProperty("passwordForAddEmp"));
        confirmPasswordForAddEmp.click();
        confirmPasswordForAddEmp.sendKeys(prop.getProperty("confirmPassword"));
        saveButton.submit();
    }

    public boolean verifyTheErrorMessage(){return requiredField.isDisplayed();}
}
