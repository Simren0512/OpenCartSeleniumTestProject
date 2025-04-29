package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class TestNG {

    @Test
    public void GoogleSearch()
    {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/");

        WebElement searchbar = driver.findElement(By.xpath("//textarea[@id='APjFqb']"));
        searchbar.sendKeys("Tomatoes");
    }
}
