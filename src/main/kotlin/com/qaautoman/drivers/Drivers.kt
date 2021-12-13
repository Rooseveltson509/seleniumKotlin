package com.qaautoman.drivers

import org.openqa.selenium.WebDriver

interface Drivers {

    fun close()

    fun quit()

    fun getDriver(): WebDriver?
}