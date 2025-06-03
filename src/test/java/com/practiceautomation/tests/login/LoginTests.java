package com.practiceautomation.tests.login;

import com.practiceautomation.tests.BaseTest;
import com.practicetestautomation.pageobjects.LoginPage;
import com.practicetestautomation.pageobjects.SuccessfulLoginPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {
    /*
    * The below codes will be executed in BaseTest.java
    private WebDriver driver;
    private Logger logger; // Logger can be set up in class level

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser){
        logger = Logger.getLogger(LoginTests.class.getName()); // (className.class.getName())
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
        //driver = new ChromeDriver();
        //driver.get("https://practicetestautomation.com/practice-test-login/"); // This is called in LoginPage.java
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
        logger.info("Browser is closed");
    }
    */

    @Test(groups = {"positive", "regression", "smoke"})
    public void testLoginFunctionality(){
        logger.info("Starting testLoginFunctionality test");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();
        SuccessfulLoginPage successfulLoginPage = loginPage.executeLogin("student", "Password123");

        // Open page
        //WebDriver driver = new ChromeDriver();
        //WebDriver driver = new FirefoxDriver();
        //driver.get("https://practicetestautomation.com/practice-test-login/");

        /**
         * Typing in username, password and clicking Submit button is all done under executeLogin method above in successfulLoginPage

        // Type username student into Username field
        WebElement usernameInput = driver.findElement(By.id("username"));
        logger.info("Type username");
        usernameInput.sendKeys("student");
        //usernameInput.sendKeys("wrong_student");

        // Type password Password123 into Password field
        WebElement passwordInput = driver.findElement(By.id("password"));
        logger.info("Type password");
        passwordInput.sendKeys("Password123");

        // Push Submit button
        WebElement submitButton = driver.findElement(By.id("submit"));
        logger.info("Click Submit button");
        submitButton.click();

         * The below waits for SuccessfulLoginPage to load. We know that this page is loaded when the LogOut button becomes available
         * successfulLoginPage.load(); below is the method for checking that LogOut button is available
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
         **/
        successfulLoginPage.load();

        logger.info("Verify the login functionality");
        // Verify new page URL contains practicetestautomation.com/logged-in-successfully/
        //String expectedUrl = "https://practicetestautomation.com/logged-in-successfully/";
        //String actualUrl = driver.getCurrentUrl();
        //String actualUrl = successfulLoginPage.getCurrentUrl();
        /* Since expectedUrl and actualUrl is used only once, writing both in the assertion itself can save space */
        Assert.assertEquals(successfulLoginPage.getCurrentUrl(), "https://practicetestautomation.com/logged-in-successfully/");

        // Verify new page contains expected text ('Congratulations' or 'successfully logged in')
        //String pageSource = driver.getPageSource();
        Assert.assertTrue(successfulLoginPage.getPageSource().contains("Congratulations student. You successfully logged in!"));

        // Verify button Log out is displayed on the new page
        //WebElement logOutButton = driver.findElement(By.linkText("Log out"));
        //WebElement logOutButton = driver.findElement(By.linkText("Log in"));
        //Assert.assertTrue(logOutButton.isDisplayed());
        Assert.assertTrue(successfulLoginPage.isLogoutButtonDisplayed());

        //driver.quit();
    }

    @Parameters({"username", "password", "expectedErrorMessage"})
    @Test(groups = {"negative", "regression"})
    public void negativeLoginTest(String username, String password, String expectedErrorMessage){
        logger.info("Starting negativeLoginTest test");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();
        loginPage.executeLogin(username, password);
        Assert.assertEquals(loginPage.getErrorMessage(), expectedErrorMessage);

        //Open page
        //WebDriver driver = new ChromeDriver();
        //This is for manually setting up Edge driver. If auto driver function from Selenium does not work, try this method
        //System.setProperty("webdriver.edge.driver", "src/main/resources/msedgedriver.exe");
        //WebDriver driver = new EdgeDriver();
        //driver.get("https://practicetestautomation.com/practice-test-login/");

        /**
         * Below methods are executed by executeLogin above

        //Type username incorrectUser into Username field
        WebElement usernameInput = driver.findElement(By.id("username"));
        logger.info("Typing username: " + username);
        usernameInput.sendKeys(username);

        //Type password Password123 into Password field
        WebElement passwordInput = driver.findElement(By.id("password"));
        logger.info("Typing password");
        passwordInput.sendKeys(password);

        //Push Submit button
        WebElement submitButton = driver.findElement(By.id("submit"));
        logger.info("Pressing Submit button");
        submitButton.click();

         */

        /*
         * The below methods are executed by Assertion above

        //Verify error message is displayed
        WebElement errorMessage = driver.findElement(By.id("error"));
        logger.info("Verify errorMessage is displayed: " + expectedErrorMessage);
        Assert.assertTrue(errorMessage.isDisplayed());

        //Verify error message text is Your username is invalid!
        //String expectedErrorMessage = "Your username is invalid!";
        String actualErrorMessage = errorMessage.getText();
        logger.info("Verify errorMessage text: " + actualErrorMessage);
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);

        */
        //driver.quit();
    }

    /*
    *The below test is no longer necessary as the above test covers both incorrect username and password test by passing both values as @parameters through suite.xml file
    @Test(groups = {"negative", "regression"})
    public void incorrectPasswordTest(){

        //Open page
        WebDriver driver = new ChromeDriver();
        driver.get("https://practicetestautomation.com/practice-test-login/");

        //Type username student into Username field
        WebElement usernameInput = driver.findElement(By.id("username"));
        usernameInput.sendKeys("student");

        //Type password incorrectPassword into Password field
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("incorrectPassword");

        //Push Submit button
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        //Verify error message is displayed
        WebElement errorMessage = driver.findElement(By.id("error"));
        Assert.assertTrue(errorMessage.isDisplayed());

        //Verify error message text is Your password is invalid!
        String expectedErrorMessage = "Your password is invalid!";
        String actualErrorMessage = errorMessage.getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);

        driver.quit();
    }
    */

}
