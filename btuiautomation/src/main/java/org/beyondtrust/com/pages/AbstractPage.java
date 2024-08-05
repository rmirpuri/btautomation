package org.beyondtrust.com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public abstract class AbstractPage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    private final int maxWaitTimeForElementLoading = 5;
    private static final Logger log = LoggerFactory.getLogger(AbstractPage.class);


    public AbstractPage(WebDriver driver){
        log.info("Abstract Page: Loading drivers");
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(maxWaitTimeForElementLoading));
        PageFactory.initElements(driver, this);
    }

    public abstract boolean isAt();

}

