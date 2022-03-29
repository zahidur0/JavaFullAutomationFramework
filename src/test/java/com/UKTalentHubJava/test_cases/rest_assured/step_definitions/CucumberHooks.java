package com.UKTalentHubJava.test_cases.rest_assured.step_definitions;

import com.UKTalentHubJava.utilities.Cucumber;
import io.cucumber.java.*;

import java.io.IOException;

public class CucumberHooks {
    private int currentStepDefIndex = 0;
    String currentStepDescr;

    @BeforeStep("@rest-assured")
    public void beforeTest(Scenario scenario) throws NoSuchFieldException, IllegalAccessException {
        currentStepDescr = Cucumber.getStepName(scenario, currentStepDefIndex);
    }

    @AfterStep("@rest-assured")
    public void screenShot(Scenario scenario) throws IOException {
        if (!scenario.isFailed()) {
            System.out.println(currentStepDescr);
            System.out.println("Test passed");
        }
        currentStepDefIndex += 1;
    }
}
