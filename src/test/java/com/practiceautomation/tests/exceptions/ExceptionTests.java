package com.practiceautomation.tests.exceptions;

import com.practiceautomation.tests.BaseTest;
import com.practicetestautomation.pageobjects.ExceptionsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ExceptionTests extends BaseTest {
    /*
    * The below codes will be executed in BaseTest.java
    private WebDriver driver;
    private Logger logger; // Logger can be set up in class level

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
    */

    @Test
    public void noSuchElementException(){
        logger.info("Starting noSuchElementException test");

        ExceptionsPage exceptionsPage = new ExceptionsPage(driver);
        exceptionsPage.visit();
        exceptionsPage.pushAddButton();
        Assert.assertTrue(exceptionsPage.isRowTwoDisplayedAfterWait(), "Row 2 input field is not displayed");

        /*
        * The below is all done by four lines of code above

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait

        WebElement addButton = driver.findElement(By.id("add_btn"));
        addButton.click();

        // Since visibilityOfElementLocated returns WebElement, we can assign the WebElement straight with this condition
        WebElement row2InputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input[@class='input-field']")));
        /** Any of "(//input[@class='input-field'])[2]" or
         * "//input[@class='input-field'][1]" or
         * "//div[@id='row2']/input[@class='input-field']"
         *  will return the same element

        //WebElement row2InputField = driver.findElement(By.xpath("//div[@id='row2']/input[@class='input-field']")); // Look at visibilityOfElementLocated above
        Assert.assertTrue(row2InputField.isDisplayed(), "Row 2 input field is not displayed");
        */
    }

    @Test
    public void timeoutException(){
        logger.info("Starting timeoutException test");
        ExceptionsPage exceptionsPage = new ExceptionsPage(driver);
        exceptionsPage.visit();
        exceptionsPage.pushAddButton();
        Assert.assertTrue(exceptionsPage.isRowTwoDisplayedAfterWait(), "Row 2 input field is not displayed");

        /*
        * The below can be done with four lines above
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6)); // Explicit wait

        //Click Add button
        WebElement addButton = driver.findElement(By.id("add_btn"));
        addButton.click();

        //Wait for 3 seconds for the second input field to be displayed


        //Verify second input field is displayed
        //WebElement row2InputField = driver.findElement(By.xpath("//div[@id='row2']/input[@class='input-field']"));
        WebElement row2InputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input[@class='input-field']")));
        Assert.assertTrue(row2InputField.isDisplayed(), "Row 2 input field is not displayed");

         */
    }

    @Test
    public void elementNotInteractableException(){
        logger.info("Starting elementNotInteractableException test");
        ExceptionsPage exceptionsPage = new ExceptionsPage(driver);
        exceptionsPage.visit();
        exceptionsPage.pushAddButton();
        exceptionsPage.isRowTwoDisplayedAfterWait();
        exceptionsPage.enterTextInRow2("sushi");
        exceptionsPage.saveRow2();
        Assert.assertEquals(exceptionsPage.getSuccessMessage(), "Row 2 was saved", "Actual message does not match expected message");

        /*
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6)); // Explicit wait

        //Click Add button
        WebElement addButton = driver.findElement(By.id("add_btn"));
        addButton.click();

        //Wait for the second row to load
        WebElement row2InputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input[@class='input-field']")));
        Assert.assertTrue(row2InputField.isDisplayed(), "Row 2 input field is not displayed");

        //Type text into the second input field
        String textToSend = "test text";
        row2InputField.sendKeys(textToSend);

        //Push Save button using locator By.name(“Save”)
        WebElement saveButton = driver.findElement(By.xpath("//div[@id='row2']/button[@id='save_btn']"));
        //WebElement saveButton = driver.findElement(By.name("Save")); // This will cause ElementNotInteractableException
        saveButton.click();

        //Verify text saved
        //String actualText = row2InputField.getAttribute("value"); // .getText() is not the correct function to call here as in Selenium, getText() retrieves the innerText of a WebElement. For this input field, which lacks any inner text, the text content resides within the value attribute. Therefore, accessing it via getAttribute(“value”) is the appropriate method in this case.
        //Assert.assertEquals(actualText, textToSend); // actual, expected
        // Above assertion works, but below method is better

        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmation")));
        String actualMessage = successMessage.getText();
        String expectedMessage = "Row 2 was saved";

        Assert.assertEquals(actualMessage, expectedMessage, "Actual message does not match expected message");

         */
    }

    @Test
    public void invalidElementStateException(){
        logger.info("Starting invalidElementStateException test");
        ExceptionsPage exceptionsPage = new ExceptionsPage(driver);
        exceptionsPage.visit();
        exceptionsPage.pushEditButton();
        exceptionsPage.enterTextInRow1("sushi");
        exceptionsPage.saveRow1();
        exceptionsPage.getSuccessMessage();
        Assert.assertEquals(exceptionsPage.getSuccessMessage(), "Row 1 was saved", "Actual message does not match expected message");

        /*
        * The below can be done with above

        //Clear input field
        WebElement editButton = driver.findElement(By.xpath("//div[@id='row1']/button[@id='edit_btn']"));
        WebElement row1InputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row1']/input[@class='input-field']")));
        editButton.click();
        row1InputField.clear();

        //Type text into the input field
        row1InputField.sendKeys("example text");
        WebElement saveButton = driver.findElement(By.xpath("//div[@id='row1']/button[@id='save_btn']"));
        saveButton.click();

        //Verify text changed
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmation")));
        String actualMessage = successMessage.getText();
        String expectedMessage = "Row 1 was saved";

        Assert.assertEquals(actualMessage, expectedMessage, "Actual message does not match expected message");

        String actualText = row1InputField.getAttribute("value");
        System.out.println(actualText);

         */
    }

    @Test
    public void staleElementReferenceException(){
        logger.info("Starting staleElementReferenceException test");
        ExceptionsPage exceptionsPage = new ExceptionsPage(driver);
        exceptionsPage.visit();
        exceptionsPage.pushAddButton();
        Assert.assertTrue(exceptionsPage.isInstructionsElementHiddenAfterWait());

        /*
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6)); // Explicit wait

        //Push add button
        WebElement addButton = driver.findElement(By.id("add_btn"));
        addButton.click();

        //Verify instruction text element is no longer displayed
        //Either of the below assertions will work
        Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("instructions"))), "Instructions text is still displayed");
        //Assert.assertTrue(wait.until(ExpectedConditions.stalenessOf(instructionsText)));
        */
    }

}
