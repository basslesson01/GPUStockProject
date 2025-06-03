package com.practiceautomation.tests;

import com.practiceautomation.tests.exceptions.ExceptionTests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseTest {
    protected WebDriver driver;
    protected Logger logger; // Logger can be set up in class level

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser){
        logger = Logger.getLogger(ExceptionTests.class.getName()); // (className.class.getName())
        logger.setLevel(Level.INFO);

        //System.out.println("Running test in " + browser); // Logger can do a better job than system.out
        logger.info("Running test in " + browser);
        switch (browser.toLowerCase()){
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                logger.warning("No configuration and/or incorrect name was given for a driver. Running test in Chrome by default.");
                driver = new ChromeDriver();
                break;
        }
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait


        //driver = new ChromeDriver();
        //URL is opened in ExceptionsPage class
        //driver.get("https://practicetestautomation.com/practice-test-exceptions/");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
        logger.info("Browser is closed");
    }


}
