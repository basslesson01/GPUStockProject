package memoryexpress;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ResultsPage extends BasePage {

    //By locators
    private By categoryLocator = By.xpath("//a[@href='/Category/VideoCards?Search=rtx+5090']"); // Category button for "Graphics Cards"
    //private By inStockItems = By.partialLinkText("Online - Available to Ship");
    private By displayMoreItems = By.xpath("//select[@data-role='filter-display']"); // Display dropdown button
    private By numItemsDisplayed = By.xpath("(//option[@value='120'])[1]"); // Two Display buttons exist. Grabbing whichever one because they both work.
    private List<WebElement> inStockItems;

    public ResultsPage(WebDriver driver){
        super(driver);
    }

    public void clickCategory(){
        driver.findElement(categoryLocator).click();
    }

    // Keep clicking Load More Items button until all the items are on one page
    public void loadMoreItems(){
        while(true) {
            try {
                driver.findElement(loadMoreItemsButtonLocator).click();
                Thread.sleep(1000);
            } catch (NoSuchElementException e) {
                // Element no longer found, exit the loop
                break;
            } catch (InterruptedException e) {
                // Handle interruption
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    // Without the Explicit wait, the method returns incorrect amount of in-stock count
    public void countInStockItems(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.partialLinkText("Online - Available to Ship")));
        inStockItems = driver.findElements(By.partialLinkText("Online - Available to Ship"));
        int count = inStockItems.size();
        System.out.println("Currently in stock: " + count);
    }
}
