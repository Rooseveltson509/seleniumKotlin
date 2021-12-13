package com.qaautoman.manager

import com.qaautoman.Enum.Browser
import com.qaautoman.drivers.DriverFactory
import com.qaautoman.drivers.Drivers
import org.openqa.selenium.WebDriver

open class WebDriverManager protected constructor() : Drivers {
    private val driver = ThreadLocal<DriverFactory>()

    override fun getDriver(): WebDriver {
        return driver.get().getDriver()
    }

    override fun close() {
        driver.get().close()
    }

    override fun quit() {
        driver.get().quit()
    }

    fun setDriver(browser: Browser?) {
        driver.set(DriverFactory(browser!!))
    }

    fun setDriver(browser: String) {
        setDriver(Browser.valueOf(browser.toUpperCase()))
    }

    companion object {
        val instance = WebDriverManager()
    }
}
