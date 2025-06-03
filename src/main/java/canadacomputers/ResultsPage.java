package canadacomputers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ResultsPage extends BasePage{

    //By locators
    private By categoryLocator = By.xpath("//a[@class='category-sub-link  ' and contains(text(),'Graphics Cards')]");
    private By loadMoreItemsButtonLocator = By.linkText("LOAD MORE ITEMS");
    //private By inStockItems = By.partialLinkText("Online - Available to Ship");
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
