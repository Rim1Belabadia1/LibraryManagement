package com.example.demo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.testng.Assert.assertTrue;

public class RegistrationPageTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Initializing the Chrome driver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:4200/add-employee"); // The URL for the registration page
    }

    @Test
    public void testRegistrationForm() {
        // Wait until the page is fully loaded
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fname"))); // Wait for the first name field to be visible

        // Locate form elements
        WebElement firstNameField = driver.findElement(By.id("fname"));
        WebElement emailField = driver.findElement(By.id("email"));
        WebElement dobField = driver.findElement(By.id("joiningDate"));
        WebElement passwordField = driver.findElement(By.id("lname"));
        WebElement submitButton = driver.findElement(By.id("reg"));

        // Fill out the registration form
        firstNameField.sendKeys("John Doe");
        emailField.sendKeys("john.doe@example.com");
        dobField.sendKeys("1990-01-01");
        passwordField.sendKeys("password123");

        // Submit the form
        submitButton.click();

        // Wait for the form submission to be processed (e.g., redirect to a success page)
        wait.until(ExpectedConditions.urlContains("/success")); // Assuming a successful registration redirects to a "/success" page
        assertTrue(driver.getCurrentUrl().contains("/success"), "User should be redirected to the success page after registration.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
