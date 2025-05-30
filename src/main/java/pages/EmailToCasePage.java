package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EmailToCasePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Example locators — adjust as needed for your org
    private By emailToCaseSetupLink = By.linkText("Email-to-Case Setup");
    private By newEmailToCaseButton = By.cssSelector("button[title='New Email-to-Case']");
    private By routingAddressInput = By.id("routingAddress");
    private By saveButton = By.cssSelector("button[title='Save']");
    private By successMessage = By.cssSelector(".message.success");

    public EmailToCasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void navigateToEmailToCaseSetup() {
        wait.until(ExpectedConditions.elementToBeClickable(emailToCaseSetupLink)).click();
    }

    public void clickNewEmailToCase() {
        wait.until(ExpectedConditions.elementToBeClickable(newEmailToCaseButton)).click();
    }

    public void enterRoutingAddress(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(routingAddressInput)).sendKeys(email);
    }

    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public boolean isSuccessMessageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).isDisplayed();
    }
}
