package com.example.my_maven_project;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.Test;


public class WebAppTest {
    @Test
    public void testLoginPage() {
        // Set up WebDriver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // Open the web application
        driver.get("https://example.com");

        // Locate elements and perform actions
        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login"));

        username.sendKeys("testuser");
        password.sendKeys("testpassword");
        loginButton.click();

        // Validate results
        String expectedUrl = "https://example.com/dashboard";
        assert driver.getCurrentUrl().equals(expectedUrl);

        // Close browser
        driver.quit();
    }
}