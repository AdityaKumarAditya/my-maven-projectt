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
    	WebDriverManager.chromedriver().driverVersion("131.0.6778.265").setup();
        WebDriver driver = new ChromeDriver();
        System.out.println("WebDriver initialized.");

        // Open the web application
        driver.get("https://example.com");
        System.out.println("Navigating to the web application.");

        // Add a wait to ensure the page is fully loaded
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for a specific element on the page to be visible, indicating the page has loaded
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login")));
            System.out.println("Page loaded and login element found.");
        } catch (TimeoutException e) {
            System.out.println("Page did not load in time.");
            driver.quit();
            return;
        }

        // Locate elements
        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login"));

        // Perform valid login
        username.sendKeys("testuser");
        password.sendKeys("testpassword");

        try {
            wait.until(ExpectedConditions.elementToBeClickable(loginButton));
            loginButton.click();
            System.out.println("Login button clicked.");
        } catch (TimeoutException e) {
            System.out.println("Login button was not clickable in time.");
            driver.quit();
            return;
        }

        // Validate login success
        try {
            wait.until(ExpectedConditions.urlToBe("https://example.com/dashboard"));
        } catch (TimeoutException e) {
            System.out.println("Dashboard URL did not load in time.");
            driver.quit();
            return;
        }

        String expectedUrl = "https://example.com/dashboard";
        if (driver.getCurrentUrl().equals(expectedUrl)) {
            System.out.println("Login test passed with valid credentials.");
        } else {
            System.out.println("Expected URL: " + expectedUrl + ", but found: " + driver.getCurrentUrl());
        }

        // Close browser
        driver.quit();
        System.out.println("Browser closed.");
    }
}
