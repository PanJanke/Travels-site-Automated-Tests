package pl.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HotelSearchPage {
    private WebDriver driver;

    @FindBy(xpath = "//span[text()='Search by Hotel or City Name']")
    private WebElement searchHotelSpan;

    @FindBy(xpath = "//div[@id='select2-drop']//input")
    private WebElement searchHotelInput;

    @FindBy(name = "checkin")
    private WebElement checkinInput;

    @FindBy(name = "checkout")
    private WebElement checkoutInput;

    @FindBy(id = "travellersInput")
    private WebElement travelersInput;

    @FindBy(id = "adultPlusBtn")
    private WebElement adultPlusBtn;

    @FindBy(id = "childPlusBtn")
    private WebElement childPlusBtn;

    @FindBy(xpath = "//button[text()=' Search']")
    private WebElement searchButton;

    @FindBy(xpath = "//li[@id='li_myaccount']")
    private List<WebElement> myAccountLink;

    @FindBy(xpath = "//a[text()='  Sign Up']" )
    private List<WebElement> signUpLink;


    public HotelSearchPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver=driver;
    }


    public HotelSearchPage setCity(String cityName){
        searchHotelSpan.click();
        searchHotelInput.sendKeys(cityName);
        String xpath =String.format("//span[@class='select2-match' and text()='%s']",cityName);
        driver.findElement(By.xpath(xpath)).click();
        return this;
    }

    public HotelSearchPage setDates(String checkin, String checkout){
        checkinInput.sendKeys(checkin);
        checkoutInput.sendKeys(checkout);
        return this;
    }
    public HotelSearchPage setTravelers(int adultsToAdd,int childToAdd){
        travelersInput.click();
        addTraveler(adultPlusBtn,adultsToAdd);
        addTraveler(childPlusBtn,childToAdd);
        return this;
    }

    private void addTraveler(WebElement travelersBtn, int numberOfTravelers ){
        for(int i=0;i<numberOfTravelers;i++)
            travelersBtn.click();
    }

    public ResultsPage performSearch(){
        searchButton.click();
        return new ResultsPage(driver);
    }

    public void openSignUpForm(){
        myAccountLink.stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        //driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        signUpLink.get(1).click();
    }

}
