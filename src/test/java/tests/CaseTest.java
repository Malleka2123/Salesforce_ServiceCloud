package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CasePage;
import pages.HomePage;
import pages.LoginPage;
import utils.ConfigReader;

public class CaseTest extends BaseTest {

    @Test(description = "Create a new case and verify case creation")
    public void createCaseTest() {
        test.set(extent.createTest("createCaseTest"));

        driver.get(ConfigReader.getProperty("url"));

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(ConfigReader.getProperty("username"));
        loginPage.enterPassword(ConfigReader.getProperty("password"));
        loginPage.clickLogin();

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageDisplayed(), "Home page is not displayed after login");

        // Case creation
        CasePage casePage = new CasePage(driver);
        casePage.clickNewCase();
        casePage.selectCaseOrigin("Phone");
        casePage.enterCaseSubject("Test Case Subject");
        casePage.enterCaseDescription("This is a test case created by automation.");
        casePage.clickSave();

        Assert.assertTrue(casePage.isCaseCreated(), "Case creation failed");
        String caseNumber = casePage.getCaseNumber();
        Assert.assertNotNull(caseNumber, "Case number is null after creation");
    }
}
