package com.qaautoman.drivers

import com.qaautoman.config.Properties
import org.openqa.selenium.Proxy
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.firefox.FirefoxProfile

interface DriverFactoryImpl : Drivers {
    fun chromeOptions(): ChromeOptions? {
        val options = ChromeOptions()

        // SSL -
        options.setAcceptInsecureCerts(true)
        options.setHeadless(Properties.Config.headless)

        // Proxy
        if (Properties.Config.getIsProxy()) {
            options.setProxy(proxy)
        }
        //
        if (Properties.Config.headless) {
            options.addArguments("window-size=1200,1100")
            options.addArguments("--headless")
            options.addArguments("--disabled-gpu")
        }

        //
        options.addArguments("-no-sandbox")
        options.addArguments("--disabled-infobars")
        options.addArguments("--disabled-browser-side-navigation")
        return options
    }

    fun firefoxOptions(): FirefoxOptions? {
        val profile = FirefoxProfile()
        val options = FirefoxOptions()

        // SSL
        profile.setAcceptUntrustedCertificates(true)
        profile.setAssumeUntrustedCertificateIssuer(true)

        // Profile SSL - HeadLess
        // options.setProfile(profile);
        options.setAcceptInsecureCerts(true)
        options.setHeadless(Properties.Config.headless)
        options.setCapability("acceptSslCerts", true)
        return options
    }

    /***
     *
     * @return
     */
    val proxy: Proxy?
        get() {
            val proxy = Proxy()
            proxy.sslProxy = Properties.Config.proxy
            proxy.httpProxy = Properties.Config.proxy
            proxy.noProxy = "127.0.0.1;::1"
            return proxy
        }
}