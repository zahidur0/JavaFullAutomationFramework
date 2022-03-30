package com.UKTalentHubJava.test_cases.rest_assured.step_definitions;

import com.UKTalentHubJava.utilities.Cucumber;
import io.cucumber.java.*;

public class CucumberHooks {
    private int currentStepDefIndex = 0;
    private BaseClass base;
    String currentStepDescr;

    // constructor for picocontainer
    public CucumberHooks(BaseClass base) {
        this.base = base;
    }

    @Before("@rest-assured")
    public void setup(Scenario scenario) {
        base.loggerSetup();
        base.logger.info("STARTED -- " + scenario.getName());
    }

    @BeforeStep("@rest-assured")
    public void beforeTest(Scenario scenario) throws NoSuchFieldException, IllegalAccessException {
        currentStepDescr = Cucumber.getStepName(scenario, currentStepDefIndex);
    }

    @AfterStep("@rest-assured")
    public void afterStepCheck(Scenario scenario) {
        if (scenario.isFailed()) {
            base.logger.error("FAILED -- " + currentStepDescr);
        }
        base.logger.info("PASSED -- " + currentStepDescr);
        currentStepDefIndex += 1;
    }
}
