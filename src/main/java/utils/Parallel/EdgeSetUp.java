package utils.Parallel;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.proxy.ProxyConfig;

import java.net.MalformedURLException;
import java.net.URL;

import static utils.Browser.*;


public class EdgeSetUp {
    private EdgeOptions edgeOptions = new EdgeOptions();

    public EdgeOptions getEdgeOptions() {
        return edgeOptions;
    }

    public void setEdgeOptions(EdgeOptions edgeOptions) {
        this.edgeOptions = edgeOptions;
    }

    public static WebDriver driver;

    public WebDriver driver() throws MalformedURLException {
        WebDriverManager.edgedriver().setup();
        if (getBrowserVersion() == null) {
            driver = new EdgeDriver(edgeOptions);
        } else {
            edgeOptions.setCapability("proxy", ProxyConfig.dvsaProxy());
            edgeOptions.setPlatformName(getPlatform());
            edgeOptions.setCapability("browser_version", getBrowserVersion());
            driver = new RemoteWebDriver(new URL(hubURL()), edgeOptions);
        }
        return driver;
    }
}