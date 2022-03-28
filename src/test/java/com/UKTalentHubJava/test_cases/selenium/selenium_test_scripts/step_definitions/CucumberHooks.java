package com.UKTalentHubJava.test_cases.selenium.selenium_test_scripts.step_definitions;

import com.UKTalentHubJava.screenshot_taker.ScreenshotTaker;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.io.IOException;

public class CucumberHooks extends BaseClass {

    private BaseClass base;

    // This constructor is required to pass the driver from
    // the base class to this hook class (see cucumber picocontainer)
    public CucumberHooks(BaseClass base) {
        this.base = base;
    }

    @Before("@selenium")
    public void iInitialiseTheChromeBrowser() {
        base.driver = base.setup("chrome");
    }

    @After("@selenium")
    public void driverQuit() {
        base.driver.quit();
    }

    @AfterStep("@selenium")
    public void screenShot(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            new ScreenshotTaker(base.driver);
        }
    }
}
