package de.phonebook.core;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseHelper {

    protected WebDriver driver;

    public BaseHelper(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    // создалось это
    public void type(By locator, String text) {
        click(locator);//выделили By.name("email) -> extract->parameter->replace all occurences-> назвали его locator
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text); //выделили "abcde12345@gmail.com"->extract->parameter->назвали text->enter->all
    }

    public void click(By locator) {
        driver.findElement(locator).click();// выбрали от By до ) нажали extract -> parametr и переименовали в locator и  выбрать all
    }

    public boolean isAlertPresent() {
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(45)).until(ExpectedConditions.alertIsPresent());
        if (alert == null) {
            return false;
        } else {
            driver.switchTo().alert().accept();
            return true;
        }
    }

    //прописываем паузу
    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String takeScreenshot(){
        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);// временный
        File screen = new File("screenshots/screen-" + System.currentTimeMillis() + ".png");//постоянный

        try {
            Files.copy(tmp,screen);
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
        return screen.getAbsolutePath();
    }

}
