package com.example.demo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.testng.Assert.assertTrue;

public class GestionEmpruntsTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
         // Configuration du driver Chrome
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:4200/liste-emprunts"); // URL de la page des emprunts
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    public void testAjouterEmprunt() {
        // Attendre que le bouton "Ajouter un emprunt" soit visible
        WebElement ajouterButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'Ajouter un emprunt')]")));
        ajouterButton.click();

        // Remplir le formulaire
        WebElement nomUtilisateur = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nomUtilisateur")));
        WebElement nomLivre = driver.findElement(By.id("nomLivre"));
        WebElement dateAcquisition = driver.findElement(By.id("dateAcquisition"));
        WebElement dateRetour = driver.findElement(By.id("dateRetour"));
        WebElement etat = driver.findElement(By.id("etat"));

        nomUtilisateur.sendKeys("John Doe");
        nomLivre.sendKeys("Livre Test");
        dateAcquisition.sendKeys("2024-12-01");
        dateRetour.sendKeys("2024-12-15");
        etat.sendKeys("Empreinté");

        // Soumettre le formulaire
        WebElement sauvegarderButton = driver.findElement(By.cssSelector("button.btn-success"));
        sauvegarderButton.click();

    }

    @Test
    public void testModifierEmprunt() {
        // Cliquer sur "Modifier" pour un emprunt existant
        WebElement modifierButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Modifier')]")));
        modifierButton.click();

        // Modifier les informations
        WebElement nomLivre = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nomLivre")));
        nomLivre.clear();
        nomLivre.sendKeys("Livre Modifié");

        // Sauvegarder les modifications
        WebElement sauvegarderButton = driver.findElement(By.cssSelector("button.btn-success"));
        sauvegarderButton.click();

    }

    @Test
    public void testSupprimerEmprunt() {
        // Cliquer sur "Supprimer" pour un emprunt existant
        WebElement supprimerButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Supprimer')]")));
        supprimerButton.click();

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Fermer le navigateur après chaque test
        }
    }
}
