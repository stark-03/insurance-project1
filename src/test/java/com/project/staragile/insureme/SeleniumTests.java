package com.project.staragile.insureme;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SeleniumTests {
    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        // Set up WebDriver (ChromeDriver as an example)
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void testHomePageTitle() {
        driver.get("http://18.212.69.204:8084"); // Replace with your app URL
        String pageTitle = driver.getTitle();
        assertTrue(pageTitle.contains("Test"), "Page title should contain 'Test'"); 
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
