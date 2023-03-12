package pl.seleniumdemo.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;

public class DriverFactory {
    public static WebDriver getDriver() throws IOException {
        String name = PropertiesLoader.loadProperty("browser.name");

        if(name.equals("firefox"))
            return  new FirefoxDriver();
        else
            return  new ChromeDriver();
    }
}
