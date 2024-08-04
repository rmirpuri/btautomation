package test.docker;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    protected WebDriver driver;
    private WebDriverManager webDriverManager;

    @BeforeTest
    public void setUp() {
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver();
    }

    @AfterClass
    public void tearDown() {
        webDriverManager.quitDriver();
    }
}

