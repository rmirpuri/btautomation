package test.docker;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.beyondtrust.com.pages.CreateTicketPage;
import org.beyondtrust.com.pages.FormPage;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.util.Constants;
import test.util.DateTimeUtil;
import test.util.TestData;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;


public class CreateTicketTest extends BaseTest {
    private CreateTicketPage createTicketPage;
    private FormPage formPage;

    private String ticketTitle;

    @BeforeTest
    public void setPageObjects(){
        log.info("Set up Page Objects");
        this.createTicketPage = new CreateTicketPage(driver);
        this.formPage = new FormPage(driver);
        this.ticketTitle = TestData.TITLE;
    }

    @Test(priority = 1)
    public void createTicket(){
        createTicketPage.goTo(Constants.CREATE_TICKET_PORTAL_URL);
        createTicketPage.createTicket();
        Assert.assertTrue(formPage.isAt());
    }

    @Test(priority = 2)
    public void fillForm(){
        log.info("Running test fillform");
        String title = "Employee_Ticket_"+ DateTimeUtil.getCurrentDateTime();
        this.formPage.enterTitle(title);
        this.formPage.clickDropdownAndSelect();
        this.formPage.selectDashboard();
        this.formPage.enterDescription(TestData.DESCRIPTION);
        this.formPage.enterDate(TestData.DATE);
        this.formPage.clickSubmitButton();
        Assert.assertTrue(this.createTicketPage.isTicketCreated((title)));
        log.info("End of test.");

    }
//API Test
    @Test(priority = 3)
    public void validateAPI(){
        log.info("Running test Validate API");
        int statusCode = given().when().get(Constants.API_URL).getStatusCode();
        log.info("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, 200);

    }
    //API Test
    @Test(priority = 4)
    public void validateResponseBody(){
        log.info("Running test validateResponseBody");
        String responseBody = given().when().get(Constants.API_URL).then().log().body().toString();
        Assert.assertNotNull(responseBody);
        when().get(Constants.API_URL).then().extract().path("result.statements.AMOUNT") ;
    }

    //API Test
    @Test(priority = 5)
    public void validateRequestFormTitle() {
        // Set the base URI
        RestAssured.baseURI = Constants.API_URL;

        // Make the GET request
        Response response =
                given()
                        .when()
                        .get()
                        .then()
                        .statusCode(200)
                        .extract().response();

        // Parse the JSON response
        JsonPath jsonPath = new JsonPath(response.asString());
        List<String> titles = jsonPath.getList("title");
        for (String title : titles) {
            log.info(title);
        }
        boolean containsEmployeeTicket = titles.stream().anyMatch(title -> title.contains("Employee_Ticket"));
        Assert.assertTrue(containsEmployeeTicket, "None of the titles contain "+"Employee_Ticket");

    }

}
