package org.beyondtrust.com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FormPage extends AbstractPage{

    @FindBy(xpath = "//a[contains(text(),'Go back')]")
    private WebElement goBackLink;

    @FindBy(xpath = "//input[@formcontrolname='title']")
    private WebElement titleInput;

    @FindBy(xpath = "(//mat-form-field)[2]")
    private WebElement applicationAreaDropdown;

    @FindBy(xpath = "//textarea")
    private WebElement enterDescription;

    @FindBy(xpath = "(//mat-option)[1]")
    private WebElement dashboard;

    @FindBy(css = "[formcontrolname='dateTime']")
    private WebElement date;

    public FormPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.goBackLink));
        return this.goBackLink.isDisplayed();
    }

    public void enterTitle(String title){
        this.titleInput.sendKeys(title);
    }

    public void clickDropdownAndSelect(){
        this.applicationAreaDropdown.click();

    }

    public void selectDashboard(){
        this.dashboard.click();
    }

    public void enterDescription(String text){
        this.enterDescription.sendKeys(text);
    }

    public void enterDate(String date){
        this.enterDate(date);
    }



}
