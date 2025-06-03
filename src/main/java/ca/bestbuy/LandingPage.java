package ca.bestbuy;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

public class LandingPage extends BasePage {

    // By Locators
    private By privacyPaneCloseLocator = By.xpath("//div[@id='onetrust-close-btn-container']");

    // All the necessary dropdown buttons locators
    private By shopDropdownButtonLocator = By.id("//button[@id='hMenu-shop-0']");
    private By dropdownCategoryButtonLocator = By.id("//button[@id='hMenu-shop-0-1-0']");
    private By pcComponentButtonLocator = By.xpath("//button[@title='PC Components']");
    private By graphicsCardButtonLocator = By.xpath("//a[@title='Graphics Cards']");

    public LandingPage(WebDriver driver){
        super(driver);
    }

    public void visit(){
        super.visit("https://www.bestbuy.ca/en-ca");
    }

    public void closePrivacyPane(){
        waitForElement(privacyPaneCloseLocator);
        driver.findElement(privacyPaneCloseLocator).click();
    }

    public void showShopDropdown(){
        //WebElement shopDrop = driver.findElement(shopDropdownButtonLocator);
        int retry = 0;

        while(retry < 10){
            try{
                waitForElement(shopDropdownButtonLocator); //FIX
                driver.findElement(shopDropdownButtonLocator).click();
                break;
            }catch(StaleElementReferenceException e) {
                e.printStackTrace();
            }
            retry++;
        }
        //waitForElement(shopDropdownButtonLocator); //FIX. See above.
        //driver.findElement(shopDropdownButtonLocator).click(); //FIX. See above.
    }

    public void showCompTabAccDropdown(){
        driver.findElement(dropdownCategoryButtonLocator).click();
    }

    public void showPCCompDropdown(){
        driver.findElement(pcComponentButtonLocator).click();
    }

    public void clickGPUButton(){
        driver.findElement(graphicsCardButtonLocator).click();
    }

    /**
     * Manually searching 'RTX 5090' in the search bar results in BestBuy showing too many unrelated items.
     * I found it better when navigating dropdown buttons and menus to get to RTX 5090.
     * The below function will take care of all the steps to show just RTX 5090 results
     */
    public ResultsPage navigateToGPUListing(){
        showShopDropdown();
        showCompTabAccDropdown();
        showPCCompDropdown();
        clickGPUButton();
        return new ResultsPage(driver);
    }

}
