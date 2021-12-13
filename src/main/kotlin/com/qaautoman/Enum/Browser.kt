package com.qaautoman.Enum

import com.qaautoman.config.Properties
import java.util.*

enum class Browser(browserType: String) {
    CHROME("chrome"), FIREFOX("gecko"), EDGE("edge"), OPERA("opera");

    private val webDriverProperty: String
    val browserName: String
        get() = if (webDriverProperty == "gecko") "firefox" else webDriverProperty

    fun webDriverProp(): String {
        return String.format("webdriver.%s.driver", webDriverProperty)
    }

    fun webDriverPath(): String {
        return Properties.Config.webDriverPath

    }

    init {
        webDriverProperty = Objects.requireNonNull(browserType)
    }
}
