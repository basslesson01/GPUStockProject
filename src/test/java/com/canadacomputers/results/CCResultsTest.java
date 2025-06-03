package com.canadacomputers.results;

import canadacomputers.LandingPage;
import canadacomputers.ResultsPage;
import com.canadacomputers.BaseTest;
import org.testng.annotations.Test;

public class CCResultsTest extends BaseTest {

    @Test
    public void searchGPUTest(){
        logger.info("Starting searchGPUTest()");
        LandingPage landingPage = new LandingPage(driver); //com.canadacomputers.LandingPage
        landingPage.visit();
        ResultsPage resultsPage = landingPage.searchGpu("RTX 5080");
        resultsPage.clickCategory();
        resultsPage.loadMoreItems();
        resultsPage.countInStockItems();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
