package com.qaautoman.runner

import io.cucumber.testng.CucumberOptions

@CucumberOptions(
    features = ["./src/test/resources/features"],
    glue = ["com.qaautoman.stepDefinitions", "com/qaautoman/pageObjects"],
    plugin = ["pretty", "html:target/reports/html/htmlreport", "json:target/reports/jsonreports/index.json", "junit:target/reports/xmlreport.xml", "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"]
)
class TestRunner : BaseRunner()
