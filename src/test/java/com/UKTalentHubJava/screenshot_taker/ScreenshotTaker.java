package com.UKTalentHubJava.screenshot_taker;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ScreenshotTaker {

    private int counter = 0;
    String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(Calendar.getInstance().getTime());

    public ScreenshotTaker(WebDriver driver) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String filePath = System.getProperty("user.dir") + "\\screenshots\\"
                + timeStamp + "-" + counter + ".png";
        checkFileString(screenshot, filePath);
    }

    public ScreenshotTaker(WebDriver driver, String path, String currentStepName) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String updatedCurrentStepName = currentStepName.replaceAll("[^a-zA-Z0-9\\-]", "_");
        System.out.println(updatedCurrentStepName);
        String fileName = updatedCurrentStepName + " - " + timeStamp + "-" + counter + ".png";
        String filePath = path + fileName;
        checkFileString(screenshot, filePath);
    }

    public ScreenshotTaker(WebDriver driver, String screenshotDestination) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        checkFileString(screenshot, screenshotDestination);
        System.out.println("Screenshot has been taken.");
        System.out.println("Screenshot stored in " + screenshotDestination);
    }

    public void checkFileString(File screenshot, String filePath) throws IOException {
        counter++;

        // SAFETY CHECK DO NOT REMOVE
        if (counter > 15) {
            System.exit(1);
        }
        if (new File(filePath).exists()) {
            String updatedFilePath = filePath.split("\\.(?=[^\\.]+$)")[0] + counter + ".png";
            checkFileString(screenshot, updatedFilePath);
        } else {
            FileUtils.copyFile(screenshot, new File(filePath));
            System.out.println("Screenshot has been taken.");
            System.out.println("Screenshot stored in " + filePath);
        }
    }
}