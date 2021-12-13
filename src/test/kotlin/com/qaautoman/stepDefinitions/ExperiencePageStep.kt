package com.qaautoman.stepDefinitions

import com.qaautoman.pageObjects.ExperiencePage
import io.cucumber.java8.En
import org.openqa.selenium.WebDriver

class ExperiencePageStep(experiencePage: ExperiencePage) : En {
    private val driver: WebDriver? = null

    init {
        Given("^user is on the home page$") {
            experiencePage.maximize()
            experiencePage.iNavigateTo()
        }
        When("user is doing to his experience") {
            experiencePage.setExperience()
        }
        Then("he should be at the home page") {
            println("The then block")

        }
    }
}