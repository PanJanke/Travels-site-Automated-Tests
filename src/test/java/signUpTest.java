import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class signUpTest {


    @Test
    public void signUp(){
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");

        String lastName = "Testowy";

        driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();
        driver.findElement(By.name("firstname")).sendKeys("Jan");
        driver.findElement(By.name("lastname")).sendKeys(lastName);
        driver.findElement(By.name("phone")).sendKeys("123456789");
        int randomNumber = (int) (Math.random()*1000);
        String email= "testowy"+randomNumber+"@gmail.com";
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("test123");
        driver.findElement(By.name("confirmpassword")).sendKeys("test123");
        driver.findElement(By.xpath("//button[@type='submit' and text()=' Sign Up']")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement heading =driver.findElement(By.xpath("//h3[@class='RTL']"));
        Assert.assertTrue(heading.getText().contains(lastName));
        driver.quit();
    }

    @Test
    public void signUpBlank(){
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(5l,TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");

        String lastName = "Testowy";

        driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();
        driver.findElement(By.xpath("//button[@type='submit' and text()=' Sign Up']")).click();


        List<String> alertList = driver.findElements(By.xpath("//div[@class='alert alert-danger']//p"))
                .stream()
                .map(el->el.getAttribute("textContent"))
                .collect(Collectors.toList());

 //tu moze lepiej asercjem miekkie
        Assert.assertEquals("The Email field is required.",alertList.get(0));
        Assert.assertEquals("The Password field is required.",alertList.get(1));
        Assert.assertEquals("The Password field is required.",alertList.get(2));
        Assert.assertEquals("The First name field is required.",alertList.get(3));
        Assert.assertEquals("The Last Name field is required.",alertList.get(4));

        driver.quit();


    }

    @Test
    public void signUpWrongEmail(){
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(5l,TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");

        String lastName = "Testowy";

        driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();
        driver.findElement(By.name("firstname")).sendKeys("Jan");
        driver.findElement(By.name("lastname")).sendKeys(lastName);
        driver.findElement(By.name("phone")).sendKeys("123456789");
        int randomNumber = (int) (Math.random()*1000);
        String email= "testowy"+randomNumber+".com";
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("test123");
        driver.findElement(By.name("confirmpassword")).sendKeys("test123");
        driver.findElement(By.xpath("//button[@type='submit' and text()=' Sign Up']")).click();

        WebElement emailAlert=driver.findElement(By.xpath("//div[@class='alert alert-danger']//p"));
        Assert.assertEquals(emailAlert.getText(),"The Email field must contain a valid email address.");
        driver.quit();
    }

}
