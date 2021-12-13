package com.qaautoman.Enum

import com.qaautoman.config.PropertiesLoader
import java.util.*

enum class Urls {
    PRODUCTION, PREPROD, INTEGRATION;

    private var environment: String? = null

    open fun getEnvironment(): String? {
        if (Objects.isNull(environment)) PropertiesLoader
            .of("environments")
            .forEach { key: String?, value: String? ->
                if (value != null) {
                    Urls.valueOf(key!!.toUpperCase()).setUrl(value)
                }
            }
        return environment
    }

    private fun setUrl(url: String) {
        environment = url
    }
}