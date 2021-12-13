package com.qaautoman.config

import com.qaautoman.Enum.Browser
import com.qaautoman.Enum.Device
import com.qaautoman.Enum.Urls
import java.util.*

class PropertiesConfig private constructor() {
    private val prop = PropertiesLoader.of("config")
    val headless: Boolean
    var browser: Browser
        private set
    val proxy: String
    private val isProxy: Boolean
    val environment: Urls

    //
    var webDriverPath: String
        private set
    private val device: Device
    fun getIsProxy(): Boolean {
        return isProxy
    }

    val width: Int
        get() = device.width
    val length: Int
        get() = device.length
    val isReal: Boolean
        get() = device.isReal

    fun setBrowser(b: String) {
        browser = Browser.valueOf(b.toUpperCase())
        webDriverPath = prop.getProperty(browser.browserName)
    }

    companion object {
        val instance = PropertiesConfig()
    }

    init {
        var tmp: String
        headless = java.lang.Boolean.valueOf(System.getProperty("headless", prop.getProperty("headless")))
        tmp = System.getProperty("browser", prop.getProperty("browser", "chrome"))
        browser = Browser.valueOf(tmp.toUpperCase())
        isProxy = prop.getProperty("isProxy", false)
        proxy = prop.getProperty("proxy")
        webDriverPath = prop.getProperty(browser.browserName)
        tmp = System.getProperty("env", prop.getProperty("env", "production")).toUpperCase()
        environment = Urls.valueOf(tmp)
        tmp = System.getProperty("device", prop.getProperty("device", "windows")).toUpperCase()
        device = Device.valueOf(tmp)
    }
}