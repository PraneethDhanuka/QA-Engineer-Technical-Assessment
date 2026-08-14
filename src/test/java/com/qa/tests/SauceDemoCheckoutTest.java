package com.qa.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class SauceDemoCheckoutTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {

EdgeOptions options = new EdgeOptions();

options.addArguments("--headless=new");
options.addArguments("--window-size=1920,1080");

driver = new EdgeDriver(options);

wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    @Test
    public void successfulLoginAndCheckout() {

        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        wait.until(ExpectedConditions.urlContains("inventory.html"));

        Assert.assertTrue(
                driver.findElements(By.cssSelector(".inventory_item")).size() > 0,
                "Product items should be visible on the inventory page."
        );

        driver.findElement(By.cssSelector(".inventory_item button")).click();

        wait.until(
                ExpectedConditions.textToBePresentInElementLocated(
                        By.cssSelector(".shopping_cart_badge"),
                        "1"
                )
        );

        driver.findElement(By.cssSelector(".shopping_cart_link")).click();

        wait.until(ExpectedConditions.urlContains("cart.html"));

        Assert.assertTrue(
                driver.findElements(By.cssSelector(".cart_item")).size() > 0,
                "Cart should contain the selected product."
        );

        driver.findElement(By.id("checkout")).click();

        wait.until(
                ExpectedConditions.urlContains("checkout-step-one.html")
        );

        driver.findElement(By.id("first-name")).sendKeys("Praneeth");
        driver.findElement(By.id("last-name")).sendKeys("Dhanuka");
        driver.findElement(By.id("postal-code")).sendKeys("10000");

        driver.findElement(By.id("continue")).click();

        wait.until(
                ExpectedConditions.urlContains("checkout-step-two.html")
        );

        Assert.assertTrue(
                driver.findElements(By.cssSelector(".cart_item")).size() > 0,
                "Checkout overview should contain the product."
        );

        driver.findElement(By.id("finish")).click();

        wait.until(
                ExpectedConditions.urlContains("checkout-complete.html")
        );

        Assert.assertTrue(
                driver.findElement(By.cssSelector(".complete-header"))
                        .getText()
                        .toLowerCase()
                        .contains("thank you"),
                "Successful checkout confirmation should be displayed."
        );

        // Bonus: logout and verify return to login page.
        driver.findElement(By.id("react-burger-menu-btn")).click();

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("logout_sidebar_link")
                )
        ).click();

        wait.until(
                ExpectedConditions.urlToBe("https://www.saucedemo.com/")
        );

        Assert.assertTrue(
                driver.findElement(By.id("login-button")).isDisplayed(),
                "Login button should be visible after logout."
        );
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}