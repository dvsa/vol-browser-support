package utils.Parallel;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class FirefoxSetUp {


    private FirefoxOptions options = new FirefoxOptions();

    public FirefoxOptions getOptions() {
        return options;
    }

    public void setOptions(FirefoxOptions options) {
        this.options = options;
    }

    public static WebDriver driver;

    public WebDriver driver() throws MalformedURLException {
        WebDriverManager.firefoxdriver().setup();
        options.setCapability("marionette", true);
        if (getPlatform() == null) {
            driver = new FirefoxDriver(getOptions());
        } else {
            options.setCapability("proxy",ProxyConfig.dvsaProxy());
            options.setPlatformName(getPlatform());
            options.setCapability("browser_version", getBrowserVersion());
            driver = new RemoteWebDriver(new URL(hubURL()), getOptions());
        }
        return driver;
    }
}