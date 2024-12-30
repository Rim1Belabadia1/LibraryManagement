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

import static org.testng.Assert.*;

public class EmpruntHistoryPageTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Configuration de WebDriver pour Chrome

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:4200/liste-emprunts"); // L'URL de la page Historique des Emprunts
    }

    @Test
    public void testNoEmpruntMessage() {
        // Vérifier si le message "Aucun emprunt trouvé" est affiché quand la liste est vide
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".no-emprunt-message")));

        WebElement noEmpruntMessage = driver.findElement(By.cssSelector(".no-emprunt-message"));
        assertTrue(noEmpruntMessage.isDisplayed(), "Le message 'Aucun emprunt trouvé' devrait être visible.");
    }

    @Test
    public void testEmpruntTableDisplayed() {
        // Simuler la présence de données en remplissant la liste des emprunts
        // Assurez-vous d'avoir des données pré-remplies dans le système ou d'utiliser un mock
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".table.table-bordered")));

        WebElement empruntTable = driver.findElement(By.cssSelector(".table.table-bordered"));
        assertTrue(empruntTable.isDisplayed(), "La table des emprunts devrait être visible.");
    }

    @Test
    public void testModifierEmprunt() {
        // Vérifier que l'overlay pour modifier un emprunt est affiché
        WebElement modifierButton = driver.findElement(By.cssSelector("button.btn.btn-primary"));
        modifierButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modification-form-container")));

        WebElement modificationForm = driver.findElement(By.cssSelector(".modification-form-container"));
        assertTrue(modificationForm.isDisplayed(), "Le formulaire de modification de l'emprunt devrait être visible.");

        // Remplir les champs du formulaire de modification
        WebElement nomUtilisateurField = driver.findElement(By.id("nomUtilisateur"));
        WebElement nomLivreField = driver.findElement(By.id("nomLivre"));
        WebElement dateAcquisitionField = driver.findElement(By.id("dateAcquisition"));
        WebElement dateRetourField = driver.findElement(By.id("dateRetour"));
        WebElement etatSelect = driver.findElement(By.id("etat"));

        nomUtilisateurField.clear();
        nomUtilisateurField.sendKeys("Utilisateur Modifié");
        nomLivreField.clear();
        nomLivreField.sendKeys("Livre Modifié");
        dateAcquisitionField.clear();
        dateAcquisitionField.sendKeys("2024-01-01");
        dateRetourField.clear();
        dateRetourField.sendKeys("2024-02-01");
        etatSelect.sendKeys("Retourner");

        // Soumettre le formulaire
        WebElement saveButton = driver.findElement(By.cssSelector("button[type='submit']"));
        saveButton.click();

        // Vérifier que les modifications ont été sauvegardées
        WebElement updatedEmpruntRow = driver.findElement(By.xpath("//td[contains(text(), 'Utilisateur Modifié')]"));
        assertTrue(updatedEmpruntRow.isDisplayed(), "Les modifications de l'emprunt devraient être sauvegardées.");
    }

    @Test
    public void testSupprimerEmprunt() {
        // Simuler un emprunt existant
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement deleteButton = driver.findElement(By.cssSelector("button.btn.btn-danger"));
        deleteButton.click();

        // Vérifier la suppression
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".table.table-bordered")));
        WebElement deletedEmpruntRow = driver.findElement(By.xpath("//td[contains(text(), 'Nom de l\'utilisateur')]"));
        assertFalse(deletedEmpruntRow.isDisplayed(), "L'emprunt devrait être supprimé.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
