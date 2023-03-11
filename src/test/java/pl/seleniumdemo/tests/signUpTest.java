package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.model.User;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;

import java.util.List;

public class signUpTest extends BaseTest {

    @Test @Ignore
    public void signUpTest(){

        String lastName = "Testowy";
        int randomNumber = (int) (Math.random()*1000);
        String email= "testowy"+randomNumber+"@gmail.com";

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        SignUpPage signUpPage = new SignUpPage(driver);

       // signUpPage.fillSignUpForm("Jan",lastName,"123456789",email,"Test123");

        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);


        Assert.assertTrue(loggedUserPage.getHeadingText().contains(lastName));

    }

    @Test
    public void signUpTest2(){

        String lastName = "Testowy";
        int randomNumber = (int) (Math.random()*1000);

        LoggedUserPage loggedUserPage = new HotelSearchPage(driver)
        .openSignUpForm()
        .setFirstName("Jan")
        .setLastName(lastName)
        .setPhone("123456789")
        .setEmail("testowy"+randomNumber+"@gmail.com")
        .setPassword("Test123")
                .setConfirmpassword("Test123")
                .signUp();


        Assert.assertTrue(loggedUserPage.getHeadingText().contains(lastName));

    }


    @Test
    public void signUpBlankTest(){
        String lastName = "Testowy";

        SignUpPage signUpPage = new HotelSearchPage(driver)
        .openSignUpForm();
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


        SignUpPage signUpPage = new HotelSearchPage(driver)
                .openSignUpForm()
                .setFirstName("Jan")
                .setLastName("Testowy")
                .setPhone("123456789")
                .setEmail("email")
                .setPassword("Test123")
                .setConfirmpassword("Test123");

        signUpPage.signUp();

        Assert.assertTrue(signUpPage.getErrors().contains("The Email field must contain a valid email address."));
    }

}
