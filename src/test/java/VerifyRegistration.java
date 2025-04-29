import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class VerifyRegistration extends BaseTest {

    @Test(priority = 1)
    public void verifyRegistrationWithFillingMandatoryFields() {

        WebElement MyAccount, Register, Continue,
                Username, FirstName, LastName, Email, Telephone, Password,
                PasswordConfirm, Suscribe, PrivacyPolicy, ExpectedMessage;

        driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();
        driver.findElement(By.xpath("//a[normalize-space()='Register']")).click();


        FirstName = driver.findElement(By.xpath("//input[@id='input-firstname']"));
        LastName = driver.findElement(By.xpath("//input[@id='input-lastname']"));
        Email = driver.findElement(By.xpath("//input[@id='input-email']"));
        Telephone = driver.findElement(By.xpath("//input[@id='input-telephone']"));
        Password = driver.findElement(By.xpath("//input[@id='input-password']"));
        PasswordConfirm = driver.findElement(By.xpath("//input[@id='input-confirm']"));
        Suscribe = driver.findElement(By.xpath("//label[normalize-space()='Yes']"));
        PrivacyPolicy = driver.findElement(By.xpath("//input[@name='agree']"));
        Continue = driver.findElement(By.xpath("//input[@value='Continue']"));

        FirstName.sendKeys("Britney");
        LastName.sendKeys("Spears");
        Email.sendKeys("britneyspear37@gmail.com");
        Telephone.sendKeys("010-6764237");
        Password.sendKeys("britspr123");
        PasswordConfirm.sendKeys("britspr123");
        Suscribe.click();
        PrivacyPolicy.click();
        Continue.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        ExpectedMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[normalize-space()='Your Account Has Been Created!']")));
        String ExpectedMessageString = ExpectedMessage.getText();
        Assert.assertEquals(ExpectedMessageString, "Your Account Has Been Created!", "Fail: The account failed to be registered");

        driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();
        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Logout']")));
        logoutButton.click();
    }

    @Test (priority =  2)
    public void verifyRegistrationWithMissingFields() {

        // TC03
        driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();
        driver.findElement(By.xpath("//a[normalize-space()='Register']")).click();

        driver.findElement(By.xpath("//input[@value='Continue']")).click(); // Without filling fields

        // Checking if error messages appear
        WebElement firstNameWarning = driver.findElement(By.xpath("//div[contains(text(),'First Name must be between 1 and 32 characters!')]"));
        WebElement lastNameWarning = driver.findElement(By.xpath("//div[contains(text(),'Last Name must be between 1 and 32 characters!')]"));
        WebElement emailWarning = driver.findElement(By.xpath("//div[contains(text(),'E-Mail Address does not appear to be valid!')]"));
        WebElement telephoneWarning = driver.findElement(By.xpath("//div[contains(text(),'Telephone must be between 3 and 32 characters!')]"));
        WebElement passwordWarning = driver.findElement(By.xpath("//div[contains(text(),'Password must be between 4 and 20 characters!')]"));

        Assert.assertTrue(firstNameWarning.isDisplayed(), "Fail: Error message for first name is not shown");
        Assert.assertTrue(emailWarning.isDisplayed(), "Fail : Error message for missing email is not shown");
        Assert.assertTrue(telephoneWarning.isDisplayed(), "Fail: Error message for missing telephone not shown");
        Assert.assertTrue(passwordWarning.isDisplayed(), "Fail: Error message for missing password not shown");

        driver.navigate().refresh();
    }

        @Test(priority = 3)
        public void verifyRegistrationWithSameEmail() {
            // TC02
            driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();
            driver.findElement(By.xpath("//a[normalize-space()='Register']")).click();

            driver.findElement(By.id("input-firstname")).sendKeys("Britney");
            driver.findElement(By.id("input-lastname")).sendKeys("Spears");
            driver.findElement(By.id("input-email")).sendKeys("britneyspear37@gmail.com"); // SAME email
            driver.findElement(By.id("input-telephone")).sendKeys("010-6764237");
            driver.findElement(By.id("input-password")).sendKeys("britspr123");
            driver.findElement(By.id("input-confirm")).sendKeys("britspr123");
            driver.findElement(By.xpath("//label[normalize-space()='Yes']")).click();
            driver.findElement(By.name("agree")).click();
            driver.findElement(By.xpath("//input[@value='Continue']")).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement WarningMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                               By.xpath("//div[@class='alert alert-danger alert-dismissible']")));
            String warningMessages = WarningMessage.getText();
            Assert.assertTrue(warningMessages.contains("Warning: E-Mail Address is already registered!"), "Fail: Duplicate email was accepted");
        }


    }


