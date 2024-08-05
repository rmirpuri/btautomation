package test.docker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.remote.RemoteWebDriver;
import test.util.Constants;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import test.util.Constants;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverManager {
    private WebDriver driver;
    private String browser;

    public WebDriverManager(String browser) {
        this.browser = browser.toLowerCase();
        initializeDriver();
    }

    private void initializeDriver() {
        try {
            switch (browser) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    driver = new RemoteWebDriver(new URL(Constants.SELENIUM_HUB), chromeOptions);
                    break;
                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    driver = new RemoteWebDriver(new URL(Constants.SELENIUM_HUB), edgeOptions);
                    break;
                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    driver = new RemoteWebDriver(new URL(Constants.SELENIUM_HUB), firefoxOptions);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
