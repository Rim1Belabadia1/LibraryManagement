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

public class AddBookPageTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Configuration de WebDriver pour Chrome

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:4200/add-book"); // L'URL de votre page d'ajout de livre
    }

    @Test
    public void testAddBookForm() {
        // Attendre que la page soit complètement chargée
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(500));  // Utilisation de Duration
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))); // Attendre que le champ du formulaire soit visible

        // Trouver les éléments du formulaire
        WebElement nameField = driver.findElement(By.id("name"));
        WebElement typeField = driver.findElement(By.id("type"));
        WebElement descriptionField = driver.findElement(By.id("description"));
        WebElement editeurField = driver.findElement(By.id("editeur"));
        WebElement auteurField = driver.findElement(By.id("auteur"));
        WebElement dateDePublicationField = driver.findElement(By.id("dateDePublication"));
        WebElement availabilityField = driver.findElement(By.id("availability"));
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));

        // Remplir le formulaire
        nameField.sendKeys("Example Book");
        typeField.sendKeys("Fiction");
        descriptionField.sendKeys("This is an example description of a book.");
        editeurField.sendKeys("Example Publisher");
        auteurField.sendKeys("John Doe");
        dateDePublicationField.sendKeys("2023-12-01");  // Format de date requis
        availabilityField.sendKeys("In Stock");

        // Soumettre le formulaire
        submitButton.click();

        // Vérifier que l'ajout du livre a réussi (par exemple, rediriger vers une page de confirmation ou afficher un message)
        wait.until(ExpectedConditions.urlContains("/books"));
        assertTrue(driver.getCurrentUrl().contains("/books"), "La page des livres devrait être affichée après l'ajout.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
