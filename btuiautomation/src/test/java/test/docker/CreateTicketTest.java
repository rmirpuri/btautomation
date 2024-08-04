package test.docker;

import org.beyondtrust.com.pages.CreateTicketPage;
import org.beyondtrust.com.pages.FormPage;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.util.Constants;

public class CreateTicketTest extends BaseTest {
    private CreateTicketPage createTicketPage;
    private FormPage formPage;

    @BeforeTest
    public void setPageObjects(){
        this.createTicketPage = new CreateTicketPage(driver);
        this.formPage = new FormPage(driver);
    }

    @Test
    public void createTicket(){
        createTicketPage.goTo(Constants.CREATE_TICKET_PORTAL_URL);
        createTicketPage.createTicket();
        Assert.assertTrue(formPage.isAt());
    }

    @Test(dependsOnMethods = "createTicket")
    public void fillForm(){
        this.formPage.enterTitle("Issue with Dashboard APP");
        this.formPage.clickDropdownAndSelect();
        this.formPage.selectDashboard();
        this.formPage.enterDescription("Please help with dashboard app. Error code xyz");
        this.formPage.enterDate("12/20/2024");
    }

}
