package ca.bestbuy.results;

import ca.bestbuy.BaseTest;
import ca.bestbuy.LandingPage;
import ca.bestbuy.ResultsPage;
import org.testng.annotations.Test;

public class ResultsTest extends BaseTest {

    @Test
    public void searchGPUTest(){
        logger.info("Starting searchGPUTest()");
        LandingPage landingPage = new LandingPage(driver);
        landingPage.visit();
        landingPage.closePrivacyPane();
        ResultsPage resultsPage = landingPage.navigateToGPUListing();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
