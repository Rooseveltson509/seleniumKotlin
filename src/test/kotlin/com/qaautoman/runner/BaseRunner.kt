package com.qaautoman.runner

//import com.qaautoman.config.Properties.Companion.Config

import com.qaautoman.Enum.Browser
import com.qaautoman.config.Properties
import com.qaautoman.manager.WebDriverManager
import io.cucumber.java.AfterStep
import io.cucumber.java8.Scenario
import io.cucumber.testng.AbstractTestNGCucumberTests
import io.qameta.allure.Attachment
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver
import org.testng.ITestResult
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeTest
import org.testng.annotations.Optional
import org.testng.annotations.Parameters
import java.io.File
import java.io.IOException
import java.util.*


open class BaseRunner : AbstractTestNGCucumberTests() {
    @Parameters("browser")
    @BeforeTest
    open fun setUp(@Optional browser: String?) {
        WebDriverManager
                .instance
                .setDriver(
                    if (Objects.isNull(browser)) Properties.Config.browser else Browser.valueOf(
                        browser!!.toUpperCase()
                    )
                )
    }

    @Attachment(value = "Page screenShot", type = "image/png")
    fun saveScreenShoot(screenShot: ByteArray): ByteArray {
        return screenShot
    }

    @AfterStep
    fun `as`(scenario: Scenario?) {
        //scenario.attach(GenericFunctions);
    }
    @AfterMethod
    open fun tearDown(result: ITestResult) {
        // Here will compare if test is failing then only it will enter into if condition
        val screenshot = File("screenshots" + File.separator + System.currentTimeMillis()
            .toString() + "_" + result.name.toString() + ".jpg")
        if (!screenshot.exists()) {
            File(screenshot.getParent()).mkdirs()
            try {
                screenshot.createNewFile()
                println("Screenshot Taken")
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        try {
            val ts = WebDriverManager.instance.getDriver() as TakesScreenshot
            val source: File = ts.getScreenshotAs(OutputType.FILE)
        } catch (e: Exception) {
            println("Exception while taking screenshot " + e.message)
        }
        System.out.println("Written screenshot to " + screenshot.getAbsolutePath())
        //System.out.println("-------------------------------IN TEAR SOWN--------------------------------------------------n");
        WebDriverManager.instance.quit()
        //System.out.println("-------------------------------INIT--------------------------------------------------n");
    }

}