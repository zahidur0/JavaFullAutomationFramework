package com.UKTalentHubJava.test_cases.rest_assured.cucumber_options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/com/UKTalentHubJava/test_cases/rest_assured/features",
        glue = {"com/UKTalentHubJava/test_cases/rest_assured/step_definitions"},
        plugin = {"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"})
public class TestRunner {
}
