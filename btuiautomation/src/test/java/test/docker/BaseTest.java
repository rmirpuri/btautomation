package test.docker;

import org.beyondtrust.com.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.util.DockerManager;

import java.io.IOException;



public class BaseTest {
    protected WebDriver driver;
    private WebDriverManager webDriverManager;
    public static final Logger log = LoggerFactory.getLogger(BaseTest.class);


    @BeforeTest
    public void setUp() {
        log.info("Before Test: Setup");
        //setUpDocker();
        //webDriverManager = new WebDriverManager("firefox");
        webDriverManager = new WebDriverManager("chrome");
        //webDriverManager = new WebDriverManager("edge");
        driver = webDriverManager.getDriver();
    }

    public void setUpDocker(){
        DockerManager dockerManager = new DockerManager("/Users/rajiv/Documents/Resume/beyondtrust/uiautomationexercise/btautomation/btuiautomation");
        try {
            dockerManager.setupDockerEnvironment();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    @AfterClass
    public void tearDown() {
        webDriverManager.quitDriver();
    }
}

