package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.EscalationPage;
import pages.HomePage;
import pages.LoginPage;
import utils.ConfigReader;

public class EscalationTest extends BaseTest {

    @Test(description = "Create and verify a new escalation rule")
    public void createEscalationRuleTest() {
        test.set(extent.createTest("createEscalationRuleTest"));

        driver.get(ConfigReader.getProperty("url"));

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(ConfigReader.getProperty("username"));
        loginPage.enterPassword(ConfigReader.getProperty("password"));
        loginPage.clickLogin();

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageDisplayed(), "Home page is not displayed after login");

        // Escalation Rule Setup
        EscalationPage escalationPage = new EscalationPage(driver);
        escalationPage.navigateToEscalationRules();
        escalationPage.clickNewEscalationRule();
        escalationPage.enterRuleName("AutoEscalationRule_" + System.currentTimeMillis());
        escalationPage.clickSave();

        Assert.assertTrue(escalationPage.isSuccessMessageDisplayed(), "Escalation rule creation failed");
    }
}
