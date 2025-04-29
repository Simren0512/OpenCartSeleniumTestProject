import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class VerifyLogOutFunctionality {

    public void LogOutFunctionality() {
        WebDriver driver = new ChromeDriver();

        driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Logout']")));
        logoutButton.click();
    }
    //We used explicit wait to wait for the dropdown to load until the Logout is visible.
}
