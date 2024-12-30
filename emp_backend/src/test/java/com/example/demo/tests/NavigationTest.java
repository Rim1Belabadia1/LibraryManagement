package com.example.demo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class NavigationTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        // Initialiser le WebDriver et WebDriverWait
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:4200");
    }

    @Test
    public void testHomePageNavigation() {
        // Cliquer sur le lien "Home"
        WebElement homeLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@routerLink, 'home')]")));
        homeLink.click();

        // Vérifier que l'URL contient "home"
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("home"), "La redirection vers la page Home a échoué.");
    }

    @Test
    public void testHistoriqueNavigation() {
        // Cliquer sur le lien "Historique des prêts"
        WebElement historiqueLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@routerLink, 'liste-emprunts')]")));
        historiqueLink.click();

        // Vérifier que l'URL contient "liste-emprunts"
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("liste-emprunts"), "La redirection vers la page Historique a échoué.");
    }

    @Test
    public void testProfileNavigation() {
        // Cliquer sur le lien "Login" (similaire à "Profil" ici)
        WebElement loginLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@routerLink, 'login')]")));
        loginLink.click();

        // Vérifier que l'URL contient "login"
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("login"), "La redirection vers la page Login a échoué.");
    }

    @AfterClass
    public void tearDown() {
        // Fermer le navigateur
        if (driver != null) {
            driver.quit();
        }
    }
}
