import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.lang.Thread;

import java.time.Duration;
import java.util.List;

public class VerifySearchFunctionality extends BaseTest {

    WebElement SearchInput, SearchIcon;

    // Initializing the locators in a @BeforeMethod setup method
    @BeforeMethod
    public void setUp() {
        // Initialize the locators inside setUp to ensure driver is ready
        SearchInput = driver.findElement(By.xpath("//input[@placeholder='Search']"));
        SearchIcon = driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']"));
    }

    @Test(priority = 1)
    public void VerifySearchValidKeyword() {
        // Clear any existing value and perform the search
        SearchInput.clear();
        SearchInput.sendKeys("iPod");
        SearchIcon.click();

        // Wait for the results to load (optional - if needed)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-thumb h4 a")));

        // Validate if search results are shown
        List<WebElement> productTitles = driver.findElements(By.cssSelector(".product-thumb h4 a"));

        // Assert that at least one result is displayed
        Assert.assertTrue(productTitles.size() > 0, "No products displayed for valid keyword.");

        // Display a message confirming that products are shown
        System.out.println("Search Results are shown for 'iPod':");

        // Print product titles
        for (WebElement title : productTitles) {
            System.out.println("Product Title: " + title.getText());
            // Optional: Check if the word "iPod" is in the titles of the products
            Assert.assertTrue(title.getText().toLowerCase().contains("ipod"),
                    "Product title doesn't contain the keyword 'iPod': " + title.getText());
        }

        // Additional message to indicate that search results were shown
        System.out.println("Total Products Found: " + productTitles.size());
    }

    @Test(priority = 2)
    public void VerifySearchInvalidKeyword() {
        // Clear any existing value and perform the search
        SearchInput.clear();
        SearchInput.sendKeys("abcxyz123");
        SearchIcon.click();

        // Step 3: Validate if search results are shown
        List<WebElement> productTitles = driver.findElements(By.cssSelector(".product-thumb h4 a"));

        // Assert that no products are displayed
        Assert.assertTrue(productTitles.size() == 0, "Products displayed for invalid keyword.");

        // Display a message confirming that no products are found for the invalid keyword
        System.out.println("No search results are shown for 'abcxyz123':");

        // Verify that no titles are returned
        System.out.println("Total Products Found: " + productTitles.size());
    }

    @Test(priority = 3)
    public void VerifyProductDetails() {
        SearchInput.clear();
        SearchInput.sendKeys("iPod");
        SearchIcon.click();

        // Wait for the results to load (optional - if needed)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-thumb h4 a")));

        // Validate if search results are shown
        List<WebElement> productTitles = driver.findElements(By.cssSelector(".product-thumb h4 a"));

        WebElement firstProduct = productTitles.get(0);
        String firstProductText = firstProduct.getText();
        System.out.println("The first product shown : " + firstProductText);

        firstProduct.click();

        WebElement productTitle = driver.findElement(By.xpath("//h1[normalize-space()='iPod Classic']"));
        WebElement productPrice=  driver.findElement(By.xpath("//h2[normalize-space()='$122.00']"));

        System.out.println("Product name :  " + productTitle.getText());
        System.out.println("Product price shown " + productPrice.getText() );

    }
}
