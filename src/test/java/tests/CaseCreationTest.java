import org.testng.Assert;
import org.testng.annotations.Test;

public class CaseCreationTest extends BaseTest {

    @Test
    public void testCreateCase() {
        loginPage.login("admin", "password");
        dashboard.clickNewCase();
        caseForm.enterSubject("Test Issue");
        caseForm.submit();

        Assert.assertTrue(caseList.contains("Test Issue"));
    }
}
