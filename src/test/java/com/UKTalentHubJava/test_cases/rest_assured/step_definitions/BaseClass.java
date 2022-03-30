package com.UKTalentHubJava.test_cases.rest_assured.step_definitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass {

    public Logger logger;
//    Uncomment if you want to explicitly set the path of log4j file
//    String log4JFilePath = System.getProperty("user.dir") + "/src/test/resources/log4j2-cucumber.xml";

    public void loggerSetup() {
//        Uncomment if you want to explicitly set the path of log4j file
//        LoggerContext loggerContext = (LoggerContext) LogManager.getContext(false);
//        File file = new File(log4JFilePath);
//        loggerContext.setConfigLocation(file.toURI());
        logger = LogManager.getLogger(BaseClass.class);
    }
}
