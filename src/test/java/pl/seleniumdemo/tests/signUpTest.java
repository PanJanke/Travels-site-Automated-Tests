package pl.seleniumdemo.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.model.User;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;
import pl.seleniumdemo.utils.SeleniumHelper;

import java.util.List;

public class signUpTest extends BaseTest {

    @Test
    public void signUpTest(){

        String lastName = "Testowy";
        int randomNumber = (int) (Math.random()*1000);
        String email= "testowy"+randomNumber+"@gmail.com";

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        SignUpPage signUpPage = new SignUpPage(driver);

        signUpPage.fillSignUpForm("Jan",lastName,"123456789",email,"Test123");

        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);


        Assert.assertTrue(loggedUserPage.getHeadingText().contains(lastName));

    }

    @Test
    public void signUpTest2(){

        String lastName = "Testowy";
        int randomNumber = (int) (Math.random()*1000);
        String email= "testowy"+randomNumber+"@gmail.com";

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        SignUpPage signUpPage = new SignUpPage(driver);
        User user = new User();
        user.setFirstName("Jan");
        user.setLastName(lastName);
        user.setPhone("123456789");
        user.setEmail(email);
        user.setPassword("Test123");

        signUpPage.fillSignUpForm(user);

        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);


        Assert.assertTrue(loggedUserPage.getHeadingText().contains(lastName));

    }


    @Test
    public void signUpBlankTest(){
        String lastName = "Testowy";

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.signUp();


        List<String> alertList = signUpPage.getErrors();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals("The Email field is required.",alertList.get(0));
        softAssert.assertEquals("The Password field is required.",alertList.get(1));
        softAssert.assertEquals("The Password field is required.",alertList.get(2));
        softAssert.assertEquals("The First name field is required.",alertList.get(3));
        softAssert.assertEquals("The Last Name field is required.",alertList.get(4));

        softAssert.assertAll();

    }

    @Test
    public void signUpWrongEmailTest(){

        String email= "testowy.com";

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        SignUpPage signUpPage = new SignUpPage(driver);

        signUpPage.setFirstName("Jan");
        signUpPage.setLastName("Testowy");
        signUpPage.setPhone("123456789");
        signUpPage.setEmail(email);
        signUpPage.setPassword("test123");
        signUpPage.setConfirmpassword("test123");
        signUpPage.signUp();

        Assert.assertTrue(signUpPage.getErrors().contains("The Email field must contain a valid email address."));
    }

}
