package com.rusoko

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URL
import java.util.concurrent.TimeUnit

class EndToEnd {
    private lateinit var driver: WebDriver
    private val baseUrl = "http://localhost:4200/"

    @Before
    fun setUp() {
        driver = RemoteWebDriver(URL("http://localhost:9515"), DesiredCapabilities.chrome())
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS)
    }

    @Test
    fun testA() {
        driver.get(baseUrl + "/user")
        driver.findElement(By.id("mat-input-0")).clear()
        driver.findElement(By.id("mat-input-0")).sendKeys("Adam")
        driver.findElement(By.id("mat-input-1")).clear()
        driver.findElement(By.id("mat-input-1")).sendKeys("Nowak")
        driver.findElement(By.id("mat-input-2")).clear()
        driver.findElement(By.id("mat-input-2")).sendKeys("abc@asdfasdf.pl")
        driver.findElement(By.cssSelector("mat-pseudo-checkbox.mat-pseudo-checkbox")).click()
        val enabled = driver.findElement(By.id("registerBtn")).isEnabled
        assert(enabled)
    }

    @Test
    fun testB() {
        driver.get(baseUrl + "/user")
        driver.findElement(By.id("mat-input-0")).clear()
        driver.findElement(By.id("mat-input-0")).sendKeys("Adam")
        driver.findElement(By.id("mat-input-1")).clear()
        driver.findElement(By.id("mat-input-1")).sendKeys("Nowak")
        driver.findElement(By.cssSelector("mat-pseudo-checkbox.mat-pseudo-checkbox")).click()
        val enabled = driver.findElement(By.id("registerBtn")).isEnabled
        assert(!enabled)
    }


    @Test
    fun testC() {
        driver.get(baseUrl + "/user")
        driver.findElement(By.id("mat-input-2")).clear()
        driver.findElement(By.id("mat-input-2")).sendKeys("abc")
        driver.findElement(By.cssSelector("mat-pseudo-checkbox.mat-pseudo-checkbox")).click()
        val text = driver.findElement(By.id("mat-error-3")).text
        println(text)
        assert(text.contains("nieprawidłowy"))
    }

    @Test
    fun testD() {
        driver.get(baseUrl + "/user")
        driver.findElement(By.id("mat-input-0")).clear()
        driver.findElement(By.id("mat-input-0")).sendKeys("Adam")
        driver.findElement(By.id("mat-input-1")).clear()
        driver.findElement(By.id("mat-input-1")).sendKeys("Nowak")
        driver.findElement(By.id("mat-input-2")).clear()
        driver.findElement(By.id("mat-input-2")).sendKeys("lubieplacki@gmail.com")
        driver.findElement(By.id("registerBtn")).click()
        val text = driver.findElement(By.id("mat-error-5")).text
        println(text)
        assert(text.contains("już istnieje"))
    }

    @After
    fun tearDown() {
        driver.quit()
    }
}
