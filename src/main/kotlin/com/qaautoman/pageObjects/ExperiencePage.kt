package com.qaautoman.pageObjects

import org.openqa.selenium.By
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

    @FindAll(FindBy(css = "_cvkwaj"))
    private var tableCalendar: WebElement? = null

    @FindBy(css = "li[role='option']")
    var adressSuggestedList: List<WebElement>? = null

    @FindBy(className = "_37ivfdq")
    var guestsBtn: WebElement? = null

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
        //tableCalendar?.findElements()


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
        Thread.sleep(5000)

    }


}