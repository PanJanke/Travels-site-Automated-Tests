package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultsPage;

import java.util.List;

public class HotelSearchTest extends BaseTest {

//poodejscie fluent
    @Test
    public void searchHotelTest(){

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        List<String> hotelNames = hotelSearchPage
                .setCity("Dubai")
                .setDates("27/04/2021","29/04/2021")
                .setTravelers(1,2)
                .performSearch().getHotelNames();


        Assert.assertEquals("Jumeirah Beach Hotel",hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower",hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana",hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth",hotelNames.get(3));

    }

    @Test
    public void NoCitySearchTest(){
        ResultsPage resultsPage  = new HotelSearchPage(driver)
        .setDates("25/04/2021","30/04/2021")
        .setTravelers(0,1)
        .performSearch();

        Assert.assertTrue(resultsPage.resultHeading.isDisplayed());
        Assert.assertEquals(resultsPage.getHeadingText(),"No Results Found");
    }
}
