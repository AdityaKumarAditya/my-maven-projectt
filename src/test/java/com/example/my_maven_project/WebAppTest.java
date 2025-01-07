package com.example.my_maven_project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.TimeoutException;
import java.time.Duration;

public class WebAppTest {
    @Test
    public void testLoginPage() {
        // Set up WebDriver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // Open the web application
        driver.get("https://example.com");

        // Initialize WebDriverWait with a timeout duration
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Locate elements and perform actions
        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login"));

        username.sendKeys("testuser");
        password.sendKeys("testpassword");

        // Wait for the login button to be clickable before clicking
        try {
            wait.until(ExpectedConditions.elementToBeClickable(loginButton));
            loginButton.click();
        } catch (TimeoutException e) {
            System.out.println("Login button was not clickable in time.");
            driver.quit();
            return; // Exit test if the element is not clickable
        }

        // Wait for the expected URL or a specific element in the next page
        try {
            wait.until(ExpectedConditions.urlToBe("https://example.com/dashboard"));
        } catch (TimeoutException e) {
            System.out.println("Dashboard URL did not load in time.");
            driver.quit();
            return; // Exit test if the page did not load
        }

        // Validate results
        String expectedUrl = "https://example.com/dashboard";
        assert driver.getCurrentUrl().equals(expectedUrl);

        // Close browser
        driver.quit();
    }
}
