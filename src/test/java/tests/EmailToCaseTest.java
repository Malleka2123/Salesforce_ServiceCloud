package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.EmailToCasePage;
import pages.HomePage;
import pages.LoginPage;
import utils.ConfigReader;

public class EmailToCaseTest extends BaseTest {

    @Test(description = "Configure Email-to-Case and verify success")
    public void configureEmailToCaseTest() {
        test.set(extent.createTest("configureEmailToCaseTest"));

        driver.get(ConfigReader.getProperty("url"));

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(ConfigReader.getProperty("username"));
        loginPage.enterPassword(ConfigReader.getProperty("password"));
        loginPage.clickLogin();

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageDisplayed(), "Home page is not displayed after login");

        // Email-to-Case Setup
        EmailToCasePage emailPage = new EmailToCasePage(driver);
        emailPage.navigateToEmailToCaseSetup();
        emailPage.clickNewEmailToCase();
        emailPage.enterRoutingAddress("testauto@example.com");
        emailPage.clickSave();

        Assert.assertTrue(emailPage.isSuccessMessageDisplayed(), "Email-to-Case setup failed");
    }
}
