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
    private val firstNameBox get() = driver.findElement(By.id("mat-input-0"))
    private val lastNameBox get() = driver.findElement(By.id("mat-input-1"))
    private val emailBox get() = driver.findElement(By.id("mat-input-2"))
    private val checkBox get() = driver.findElement(By.cssSelector("mat-pseudo-checkbox.mat-pseudo-checkbox"))

    @Before
    fun setUp() {
        driver = RemoteWebDriver(URL("http://localhost:9515"), DesiredCapabilities.chrome())
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS)
    }

    @Test
    fun `button should be enabled if data is correct`() {
        driver.get(baseUrl + "/user")
        firstNameBox.clear()
        firstNameBox.sendKeys("Adam")
        lastNameBox.clear()
        lastNameBox.sendKeys("Nowak")
        emailBox.clear()
        emailBox.sendKeys("abc@asdfasdf.pl")
        checkBox.click()
        val enabled = driver.findElement(By.id("registerBtn")).isEnabled
        assert(enabled)
    }

    @Test
    fun `button should not be enabled if email is empty`() {
        driver.get(baseUrl + "/user")
        firstNameBox.clear()
        firstNameBox.sendKeys("Adam")
        lastNameBox.clear()
        lastNameBox.sendKeys("Nowak")
        checkBox.click()
        val enabled = driver.findElement(By.id("registerBtn")).isEnabled
        assert(!enabled)
    }


    @Test
    fun `proper message should be displayed if email is in invalid format`() {
        driver.get(baseUrl + "/user")
        emailBox.clear()
        emailBox.sendKeys("abc")
        checkBox.click()
        val text = driver.findElement(By.id("mat-error-3")).text
        assert(text.contains("nieprawidłowy"))
    }

    @Test
    fun `proper message should be displayed if a user with given email exists`() {
        driver.get(baseUrl + "/user")
        firstNameBox.clear()
        firstNameBox.sendKeys("Adam")
        lastNameBox.clear()
        lastNameBox.sendKeys("Nowak")
        emailBox.clear()
        emailBox.sendKeys("lubieplacki@gmail.com")
        driver.findElement(By.id("registerBtn")).click()
        val text = driver.findElement(By.id("mat-error-5")).text
        assert(text.contains("już istnieje"))
    }

    @After
    fun tearDown() {
        driver.quit()
    }
}
