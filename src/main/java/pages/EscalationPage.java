package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EscalationPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Example locators — adjust as per your Salesforce org
    private By escalationRulesLink = By.linkText("Escalation Rules");
    private By newEscalationRuleButton = By.cssSelector("button[title='New Escalation Rule']");
    private By ruleNameInput = By.id("ruleName");
    private By saveButton = By.cssSelector("button[title='Save']");
    private By successMessage = By.cssSelector(".message.success");

    public EscalationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void navigateToEscalationRules() {
        wait.until(ExpectedConditions.elementToBeClickable(escalationRulesLink)).click();
    }

    public void clickNewEscalationRule() {
        wait.until(ExpectedConditions.elementToBeClickable(newEscalationRuleButton)).click();
    }

    public void enterRuleName(String ruleName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ruleNameInput)).sendKeys(ruleName);
    }

    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public boolean isSuccessMessageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).isDisplayed();
    }
}
