package com.rusoko;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class EndToEnd {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new RemoteWebDriver(new URL("http://localhost:9515"), DesiredCapabilities.chrome());
        baseUrl = "http://localhost:4200/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testA() throws Exception {
        driver.get(baseUrl + "/user");
        driver.findElement(By.id("mat-input-0")).clear();
        driver.findElement(By.id("mat-input-0")).sendKeys("Adam");
        driver.findElement(By.id("mat-input-1")).clear();
        driver.findElement(By.id("mat-input-1")).sendKeys("Nowak");
        driver.findElement(By.id("mat-input-2")).clear();
        driver.findElement(By.id("mat-input-2")).sendKeys("abc@asdfasdf.pl");
        driver.findElement(By.cssSelector("mat-pseudo-checkbox.mat-pseudo-checkbox")).click();
        boolean enabled = driver.findElement(By.id("registerBtn")).isEnabled();
        assert(enabled);
    }

    @Test
    public void testB() throws Exception {
        driver.get(baseUrl + "/user");
        driver.findElement(By.id("mat-input-0")).clear();
        driver.findElement(By.id("mat-input-0")).sendKeys("Adam");
        driver.findElement(By.id("mat-input-1")).clear();
        driver.findElement(By.id("mat-input-1")).sendKeys("Nowak");
        driver.findElement(By.cssSelector("mat-pseudo-checkbox.mat-pseudo-checkbox")).click();
        boolean enabled = driver.findElement(By.id("registerBtn")).isEnabled();
        assert(!enabled);
    }


    @Test
    public void testC() throws Exception {
        driver.get(baseUrl + "/user");
        driver.findElement(By.id("mat-input-2")).clear();
        driver.findElement(By.id("mat-input-2")).sendKeys("abc");
        driver.findElement(By.cssSelector("mat-pseudo-checkbox.mat-pseudo-checkbox")).click();
        String text = driver.findElement(By.id("mat-error-3")).getText();
        System.out.println(text);
        assert(text.contains("nieprawidłowy"));
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
