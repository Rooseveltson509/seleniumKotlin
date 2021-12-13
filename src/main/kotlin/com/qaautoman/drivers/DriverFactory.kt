package com.qaautoman.drivers

import com.qaautoman.Enum.Browser
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver

class DriverFactory(browser: Browser) : DriverFactoryImpl {
    private val driver: WebDriver
    override fun close() {
        driver.close()
    }

    override fun quit() {
        driver.quit()
    }

    override fun getDriver(): WebDriver {
        return driver
    }

    private fun createDriver(browser: Browser): WebDriver {
        System.setProperty(browser.webDriverProp(), browser.webDriverPath())
        return if (Browser.FIREFOX.equals(browser)) FirefoxDriver(firefoxOptions()) else ChromeDriver(chromeOptions())
    }

    init {
        driver = createDriver(browser)
    }
}