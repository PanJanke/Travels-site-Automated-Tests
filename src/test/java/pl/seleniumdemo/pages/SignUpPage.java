package pl.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.seleniumdemo.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class SignUpPage {
    @FindBy(name = "firstname")
    private WebElement firstNameInput;

    @FindBy(name = "lastname")
    private WebElement lastNameInput;

    @FindBy(name = "phone")
    private WebElement phoneInput;

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(name = "confirmpassword")
    private WebElement confirmpasswordInput;

    @FindBy(xpath = "//button[@type='submit' and text()=' Sign Up']" )
    private WebElement signupButton;

    @FindBy(xpath = "//div[@class='alert alert-danger']//p")
    private List<WebElement> errors;

    private WebDriver driver;

    public SignUpPage setFirstName(String firstName){
        firstNameInput.sendKeys(firstName);
        return this;
    }

    public SignUpPage setLastName(String lastName){
        lastNameInput.sendKeys(lastName);
        return this;
    }

    public SignUpPage setPhone(String phone){
        phoneInput.sendKeys(phone);
        return this;
    }

    public SignUpPage setEmail(String email){
        emailInput.sendKeys(email);
        return this;
    }

    public SignUpPage setPassword(String password){
        passwordInput.sendKeys(password);
        return this;
    }

    public SignUpPage setConfirmpassword(String confirmpassword){
        confirmpasswordInput.sendKeys(confirmpassword);
        return this;
    }


    public LoggedUserPage signUp(){
        signupButton.click();
        return new LoggedUserPage(driver);
    }
    public List<String> getErrors(){
        return errors
                .stream()
                .map(el->el.getAttribute("textContent"))
                .collect(Collectors.toList());

    }

    /*
    public void fillSignUpForm(String firstName,String lastName, String phone, String email, String password){
        setFirstName(firstName);
        setLastName(lastName);
        setPhone(phone);
        setEmail(email);
        setPassword(password);
        setConfirmpassword(password);
        signUp();
    }

    public void fillSignUpForm(User user){
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
        setPhone(user.getPhone());
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        setConfirmpassword(user.getPassword());
        signUp();
    }
*/

    public SignUpPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver=driver;
    }
}
