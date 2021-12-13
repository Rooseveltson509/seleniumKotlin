package com.qaautoman.config

import com.qaautoman.pageObjects.Page
import org.apache.log4j.Logger
import java.io.BufferedReader
import java.io.FileReader
import java.util.Properties
import java.util.function.BiConsumer

class PropertiesLoader internal constructor(configFile: String?) {
    private val prop = Properties()
    fun getProperty(key: String?): String {
        return prop.getProperty(key)
    }

    fun getProperty(key: String?, defaultValue: String?): String {
        return prop.getProperty(key, defaultValue)
    }

    fun getProperty(key: String?, defaultValue: Boolean): Boolean {
        return if (prop.getProperty(key) == null) defaultValue else java.lang.Boolean.valueOf(prop.getProperty(key))
    }

    fun forEach(biConsumer: BiConsumer<String?, String?>) {
        prop.forEach { key: Any?, value: Any? ->
            biConsumer.accept(
                key as String?,
                value as String?
            )
        }
    }

    companion object {
        private const val CONFIG_FILE = "config/%s.properties"

        /**
         * logger
         */
        private val LOG: Logger = Logger.getLogger(Page::class.java)
        fun of(configFile: String?): PropertiesLoader {
            return PropertiesLoader(configFile)
        }
    }

    init {
        try {
            val reader = BufferedReader(FileReader(String.format(CONFIG_FILE, configFile)))
            LOG.info("le fichier de configuration a été charger correctement.")
            prop.load(reader)
        } catch (e: Exception) {
            LOG.error("le fichier de configuration n'a pas pu être charger.")
            e.printStackTrace()
        }
    }
}
