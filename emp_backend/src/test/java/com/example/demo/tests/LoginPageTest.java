package com.example.demo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration; // Importation de la classe Duration
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.testng.Assert.assertTrue;

public class LoginPageTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {


        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:4200/login");
    }

    @Test
    public void testLoginForm() {
        // Attendre que la page soit complètement chargée
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));  // Utilisation de Duration
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("form2Example3"))); // Attendre l'élément du checkbox

        // Trouver les éléments du formulaire
        WebElement usernameField = driver.findElement(By.name("fname"));
        WebElement passwordField = driver.findElement(By.name("lname"));
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

        // Remplir le formulaire
        usernameField.sendKeys("testuser");
        passwordField.sendKeys("password123");

        // Soumettre le formulaire
        loginButton.click();

        // Vérifier que la redirection se produit (par exemple, rediriger vers une page de tableau de bord)
        wait.until(ExpectedConditions.urlContains("/dashboard"));
        assertTrue(driver.getCurrentUrl().contains("/dashboard"), "La page de tableau de bord devrait être affichée après la connexion.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
