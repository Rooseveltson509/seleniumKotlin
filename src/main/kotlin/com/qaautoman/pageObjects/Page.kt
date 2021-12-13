package com.qaautoman.pageObjects

import com.qaautoman.manager.WebDriverManager
import org.apache.log4j.Logger
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration
import java.util.function.Function

abstract class Page protected constructor() {
    /***
     * Driver
     */
    protected var driver: WebDriver

    /**
     * Waiter
     */
    protected var wait: WebDriverWait
    protected var shortWait: WebDriverWait
    protected var middleWait: WebDriverWait
    protected var longWait: WebDriverWait

    /**
     * JS
     */
    protected var js: JavascriptExecutor

    /***
     * Actions
     */
    protected var action: Actions
    fun clickOn(element: WebElement) {
        wait.until(ExpectedConditions.visibilityOf(element))
        wait.until(ExpectedConditions.elementToBeClickable(element))
        element.click()
    }

    fun clickJS(element: WebElement) {
        try {
            js.executeScript("arguments[0].click();", element)
        } catch (e: Exception) {
            element.click()
        }
    }

    fun maximize() {
        driver.manage().window().maximize()
    }

    protected fun waitForPageLoading() {
        if (!middleWait.until { condition: WebDriver? ->
                js.executeScript("return document.readyState") == "complete" || js.executeScript("return document.readyState") == "interactive"
            }) {
            LOG.warn("Page not completely loaded after a loading wait")
            return
        }
        LOG.debug("Page loading wait successfull")
    }

    fun <V> waitUntil(isTrue: Function<in WebDriver?, V>): Boolean {
        return try {
            wait.until(isTrue)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun <V> shortWaitUntil(isTrue: Function<in WebDriver?, V>): Boolean {
        return try {
            shortWait.until(isTrue)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun <V> middleWaitUntil(isTrue: Function<in WebDriver?, V>): Boolean {
        return try {
            shortWait.until(isTrue)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun <V> longWaitUntil(isTrue: Function<in WebDriver?, V>): Boolean {
        return try {
            shortWait.until(isTrue)
            true
        } catch (e: Exception) {
            false
        }
    }

    companion object {
        /**
         * logger
         */
        private val LOG = Logger.getLogger(Page::class.java)
    }

    init {
        driver = WebDriverManager.instance.getDriver()!!
        PageFactory.initElements(driver, this)
        wait = WebDriverWait(driver, Duration.ofSeconds(5))
        shortWait = WebDriverWait(driver, Duration.ofSeconds(10))
        middleWait = WebDriverWait(driver, Duration.ofSeconds(15))
        longWait = WebDriverWait(driver, Duration.ofSeconds(30))
        js = driver as JavascriptExecutor
        action = Actions(driver)
    }
}