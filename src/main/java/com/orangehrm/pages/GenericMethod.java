package com.orangehrm.pages;

import com.orangehrm.base.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class GenericMethod extends BaseTest {

    WebDriverWait wait;
    JavascriptExecutor script;

    public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File destFile = new File(System.getProperty("user.dir") + "//src//test//resources//Reports//" + testCaseName + ".png");
        FileUtils.copyFile(source, destFile);
        return System.getProperty("user.dir") + "//src//test//resources//Reports//" + testCaseName + ".png";
    }

    public void waitTillTheElementPresentBy(By by) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitTillTheElementToBeClickable(WebElement element) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean isElementPresent(WebElement element) {
        return element.isDisplayed();
    }

    public void scrollTillTheElementToVisible(WebElement element) {
        script = (JavascriptExecutor) driver;
        script.executeScript("arguments[0].scrollIntoView(true)", element);
    }
}
