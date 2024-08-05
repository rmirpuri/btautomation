package test.docker;

import org.beyondtrust.com.pages.CreateTicketPage;
import org.beyondtrust.com.pages.FormPage;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.util.Constants;
import test.util.DateTimeUtil;

public class CreateTicketTest extends BaseTest {
    private CreateTicketPage createTicketPage;
    private FormPage formPage;

    @BeforeTest
    public void setPageObjects(){
        log.info("Set up Page Objects");
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
        String title = "Employee_Ticket_"+ DateTimeUtil.getCurrentDateTime();
        this.formPage.enterTitle(title);
        this.formPage.clickDropdownAndSelect();
        this.formPage.selectDashboard();
        this.formPage.enterDescription("Please help with dashboard app. Error code xyz");
        this.formPage.enterDate("12/20/2024");
        this.formPage.clickSubmitButton();
        Assert.assertTrue(this.createTicketPage.isTicketCreated((title)));

    }

}
