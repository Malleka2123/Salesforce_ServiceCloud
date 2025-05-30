package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CasePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By newCaseButton = By.cssSelector("button[title='New Case']");
    private By caseOriginDropdown = By.id("cas7");  // example id for Origin field
    private By caseSubjectInput = By.id("cas14");   // example id for Subject field
    private By caseDescriptionInput = By.id("cas15"); // example id for Description
    private By saveButton = By.cssSelector("button[title='Save']");

    private By caseNumberLabel = By.cssSelector("span.caseNumber"); // example selector for created case number

    public CasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void clickNewCase() {
        wait.until(ExpectedConditions.elementToBeClickable(newCaseButton)).click();
    }

    public void selectCaseOrigin(String origin) {
        WebElement originDropdown = wait.until(ExpectedConditions.elementToBeClickable(caseOriginDropdown));
        originDropdown.click();
        WebElement option = driver.findElement(By.xpath("//option[text()='" + origin + "']"));
        option.click();
    }

    public void enterCaseSubject(String subject) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(caseSubjectInput)).sendKeys(subject);
    }

    public void enterCaseDescription(String description) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(caseDescriptionInput)).sendKeys(description);
    }

    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public String getCaseNumber() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(caseNumberLabel)).getText();
    }

    public boolean isCaseCreated() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(caseNumberLabel)).isDisplayed();
    }
}
