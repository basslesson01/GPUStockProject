package canadacomputers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage extends BasePage {
    //By locators and WebElements sometimes
    private By searchBarLocator = By.xpath("(//input[@type='text'])[1]"); // 4 instances of the exact same xpath showed up. Manually selecting the first instance which is the correct button
    private By submitButtonLocator = By.xpath("(//button[@type='submit'])[1]"); // 2 instances of the exact same xpath showed up. Manually selecting the first instance which is the correct button

    public LandingPage(WebDriver driver){
        super(driver);
    }

    public void visit(){
        super.visit("https://www.canadacomputers.com/en/");
    }

    public void searchBarType(String item){
        driver.findElement(searchBarLocator).sendKeys(item);
    }

    public void clickSearch(){
        driver.findElement(submitButtonLocator).click();
    }

    // Start gpu search
    public ResultsPage searchGpu(String item){
        searchBarType(item);
        clickSearch();
        return new ResultsPage(driver);
    }
}
