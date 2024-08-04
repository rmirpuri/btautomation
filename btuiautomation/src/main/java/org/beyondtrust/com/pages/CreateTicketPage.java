package org.beyondtrust.com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreateTicketPage extends AbstractPage{

    @FindBy(xpath = "//a[contains(text(),'Create Ticket')]")
    private WebElement createTicket;

    public CreateTicketPage(WebDriver driver) {
        super(driver);
    }

    public void goTo(String url){
        this.driver.get(url);
    }



    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.createTicket));
        return this.createTicket.isDisplayed();
    }

    public void createTicket(){
        this.createTicket.click();
    }
}
