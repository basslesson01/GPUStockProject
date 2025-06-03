package ca.bestbuy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResultsPage extends BasePage{

    // By Locators
    /** Figure out how to load all items on page and put all 'RTX 5090' items in an array, then find out which models are in stock */
    private By modelButtonLocator = By.xpath("//h3[text()='Model']");

    public ResultsPage(WebDriver driver){
        super(driver);
    }

    public void clickmodelButtonButton(){
        driver.findElement(modelButtonLocator).click();
    }

}
