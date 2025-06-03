package com.practicetestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ExceptionsPage extends BasePage{
    private By addButtonLocator = By.id("add_btn");
    private By editButtonLocator = By.xpath("//div[@id='row1']/button[@id='edit_btn']");
    private By row1InputFieldLocator = By.xpath("//div[@id='row1']/input[@class='input-field']");
    private By row2InputFieldLocator = By.xpath("//div[@id='row2']/input[@class='input-field']");
    private By row1SaveButtonLocator = By.xpath("//div[@id='row1']/button[@id='save_btn']");
    private By row2SaveButtonLocator = By.xpath("//div[@id='row2']/button[@id='save_btn']");
    private By successMessageLocator = By.id("confirmation");
    private By instructionsLocator = By.id("instructions");

    public ExceptionsPage(WebDriver driver){
        super(driver);
    }

    public void visit(){
        super.visit("https://practicetestautomation.com/practice-test-exceptions/");
    }

    public void pushAddButton(){
        driver.findElement(addButtonLocator).click();
    }

    public void pushEditButton(){
        driver.findElement(editButtonLocator).click();
    }

    public boolean isRowTwoDisplayedAfterWait(){
        return waitForIsDisplayed(row2InputFieldLocator);
    }

    public void enterTextInRow1(String text){
        WebElement row1Input = driver.findElement(row1InputFieldLocator);
        row1Input.clear();
        row1Input.sendKeys(text);
    }

    public void enterTextInRow2(String text){
        driver.findElement(row2InputFieldLocator).sendKeys(text);
    }

    public void saveRow1(){
        driver.findElement(row1SaveButtonLocator).click();
    }

    public void saveRow2(){
        driver.findElement(row2SaveButtonLocator).click();
    }

    public String getSuccessMessage(){
        return waitForElement(successMessageLocator).getText();
    }

    public boolean isInstructionsElementHiddenAfterWait(){
        return waitForIsHidden(instructionsLocator);
    }
}
