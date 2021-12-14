package com.qaautoman.pageObjects

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindAll
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.ExpectedConditions
import java.time.LocalDateTime


class ExperiencePage : Page() {

    @FindBy(css = "button[data-testid='header-tab-search-block-tab-true-EXPERIENCES']")
    var experienceBtn: WebElement? = null

    @FindBy(className = "_rj7nx")
    var nearby: WebElement? = null

    @FindAll(FindBy(css = "div[data-testid='menuItemButton-experiences_guest_picker'] > ._1wp3mhe > ._w37zq5"))
    private var experiencesGuestPicker: WebElement? = null

    @FindAll(FindBy(css = "div[data-testid='menuItemButton-price_range'] > ._1wp3mhe"))
    private var priceRange: WebElement? = null

    @FindAll(FindBy(css = "button[data-testid='filterItem-experiences_guest_picker-stepper-adults-0-increase-button']"))
    private var increaseBtnAdults: WebElement? = null

    @FindAll(FindBy(css = "button[data-testid='filterItem-experiences_guest_picker-stepper-children-0-increase-button']"))
    private var increaseBtnChildren: WebElement? = null

    @FindAll(FindBy(css = "button[data-testid='filterItem-experiences_guest_picker-stepper-infants-0-increase-button']"))
    private var increaseBtnInfants: WebElement? = null

    @FindAll(FindBy(css = "span[data-testid='filterItem-experiences_guest_picker-stepper-adults-0-value']"))
    private var initialValueGuestAdults: WebElement? = null

    @FindAll(FindBy(css = "span[data-testid='filterItem-experiences_guest_picker-stepper-children-0-value']"))
    private var initialValueGuestChildrenn: WebElement? = null

    @FindAll(FindBy(css = "span[data-testid='filterItem-experiences_guest_picker-stepper-infants-0-value']"))
    private var initialValueGuestInfants: WebElement? = null

    @FindBy(css = "button[data-testid='filter-panel-save-button']")
    private var btnSave: WebElement? = null

    @FindBy(css = "button[data-testid='filter-panel-save-button']")
    private var btnSavedPrice: WebElement? = null

    @FindBy(css = "#price_filter_min")
    private var btnPriceFilterMinus: WebElement? = null

    @FindBy(css = "#price_filter_max")
    private var btnPriceFilterPlus: WebElement? = null

    @FindBy(css = "#bigsearch-query-location-input")
    var whereAreYouGoing: WebElement? = null


    @FindBy(className = "_m9v25n")
    var searchBtn: WebElement? = null

    @FindBy(css = "div[data-section-id='EXPLORE_WIDE_FILTER_BAR']")
    var filterBar: WebElement? = null

    @FindBy(css = "div[data-testid='main-cookies-banner-container']")
    var cookiesWindow: WebElement? = null

    @FindBy(css = "button[data-testid='accept-btn']")
    var acceptBtn: WebElement? = null


    fun iNavigateTo() {
        driver["https://www.airbnb.fr/"]
        waitForPageLoading()
        if (waitUntil { driver -> ExpectedConditions.visibilityOf(cookiesWindow) }) {
            clickOn(acceptBtn!!)
        }
    }

    @Throws(InterruptedException::class)
    fun setExperience() {
        wait.until(ExpectedConditions.visibilityOf(experienceBtn))
        experienceBtn?.click()
        wait.until(ExpectedConditions.visibilityOf(whereAreYouGoing))
        whereAreYouGoing!!.click()
        wait.until(ExpectedConditions.visibilityOf(nearby))
        nearby!!.click()

        val allDates = driver.findElements(By.xpath("//table[@class='_cvkwaj']//td"))
        // now get datetime
        val current = LocalDateTime.now()

        // now we will iterate all values and will capture the text. We will select current day
        for (ele in allDates) {
            val date = ele.text

            // once date is current day then click and break
            if (date.equals("${current.dayOfMonth}", ignoreCase = true)) {
                ele.click()
                break
            }
        }
        val currentPlusOneWeek = current.dayOfMonth + 7

        // now we will iterate all values and will capture the text. We will select when current day + one week
        for (ele in allDates!!) {
            val date = ele.text
            // once date is one week then click and break
            if (date.equals("$currentPlusOneWeek", ignoreCase = true)) {
                ele.click()
                break
            }
        }
        searchBtn?.click()
        this.chooseGuest()

    }

    private fun chooseGuest() {
        Thread.sleep(5000)
        experiencesGuestPicker?.click()
        Thread.sleep(3000)
        println(initialValueGuestAdults?.text)

        if (waitUntil { ExpectedConditions.visibilityOf(initialValueGuestAdults) }) {
            if (initialValueGuestAdults?.text == "0") {
                increaseBtnAdults?.click()
            }
        }

        if (waitUntil { ExpectedConditions.visibilityOf(initialValueGuestChildrenn) }) {
            if (initialValueGuestChildrenn?.text == "0") {
                increaseBtnChildren?.click()
            }
        }

        if (waitUntil { ExpectedConditions.visibilityOf(initialValueGuestInfants) }) {
            if (initialValueGuestInfants?.text == "0") {
                increaseBtnInfants?.click()
            }
        }
        btnSave?.click()
        priceRange?.click()
        Thread.sleep(3000)
        val j = driver as JavascriptExecutor

        wait.until(ExpectedConditions.visibilityOf(btnPriceFilterMinus))
        j.executeScript("arguments[0].value='50';", btnPriceFilterMinus)

        wait.until(ExpectedConditions.visibilityOf(btnPriceFilterPlus))
        j.executeScript("arguments[0].value='100';", btnPriceFilterPlus)
        btnSavedPrice?.click()
    }

}