package pl.seleniumdemo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultsPage;
import pl.seleniumdemo.utils.ExcelReader;
import pl.seleniumdemo.utils.SeleniumHelper;

import java.io.IOException;
import java.util.List;

public class HotelSearchTest extends BaseTest {
    @Test
    public void searchHotelTest() {


        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        List<String> hotelNames = hotelSearchPage
                .setCity("Dubai")
                .setDates("27/04/2021", "29/04/2021")
                .setTravelers(1, 2)
                .performSearch().getHotelNames();


        Assert.assertEquals("Jumeirah Beach Hotel", hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower", hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana", hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth", hotelNames.get(3));

    }

    @DataProvider
    public Object[][] data() throws IOException {
        return ExcelReader.readExcel("testData.xlsx");
    }

    @Test(dataProvider = "data")
    public void searchHotelTestWithDataProvider(String city, String hotel) {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        List<String> hotelNames = hotelSearchPage
                .setCity(city)
                .setDates("27/04/2021", "29/04/2021")
                .setTravelers(1, 2)
                .performSearch().getHotelNames();


        Assert.assertEquals(hotel, hotelNames.get(0));

    }


    @Test
    public void NoCitySearchTest() throws IOException {
        ExtentTest test = extentReports.createTest("NoCitySearchTest");

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setDates("25/04/2021", "30/04/2021");
        hotelSearchPage.setTravelers(0, 1);
        test.log(Status.PASS, "Setting dates and travelers done.", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.performSearch();
        test.log(Status.PASS, "perform search", SeleniumHelper.getScreenshot(driver));


        ResultsPage resultsPage = new ResultsPage(driver);

        Assert.assertTrue(resultsPage.resultHeading.isDisplayed());
        Assert.assertEquals(resultsPage.getHeadingText(), "No Results Found");


    }
}
