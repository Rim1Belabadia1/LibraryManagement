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
import static org.testng.Assert.assertEquals;

public class SignupPageTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:4200/signup"); // Naviguer vers la page d'inscription
    }

    @Test
    public void testUserSignup() {
        // Attendre que la page soit complètement chargée
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))); // Vérifier que le champ "username" est visible

        // Trouver les éléments du formulaire
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement signupButton = driver.findElement(By.cssSelector("button[type='submit']"));

        // Remplir le formulaire avec des informations valides
        usernameField.sendKeys("newuser");
        passwordField.sendKeys("newpassword123");

        // Cliquer sur le bouton "S'inscrire"
        signupButton.click();

        // Vérifier la redirection vers la page de connexion
        wait.until(ExpectedConditions.urlContains("/login")); // Attendre que l'URL contienne "/login"
        assertTrue(driver.getCurrentUrl().contains("/login"), "L'utilisateur devrait être redirigé vers la page de connexion.");

        // Vérifier le message de succès
        WebElement successMessage = driver.findElement(By.className("alert-success")); // Supposez qu'un message d'alerte s'affiche
        wait.until(ExpectedConditions.visibilityOf(successMessage));
        assertEquals(successMessage.getText(), "Inscription réussie !", "Le message de succès ne correspond pas.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Fermer le navigateur après chaque test
        }
    }
}
