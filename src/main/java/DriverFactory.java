import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class DriverFactory {

    private WebDriver driver;



    static final Map<String, Supplier<WebDriver>> MAP = new HashMap<>();

    static final Function<String, WebDriver> webDriver = (browser) -> {
        if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        } else {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        }
    };



    public static WebDriver getWebDriver(final String browserName) {

        return webDriver.apply(browserName);
    }
}

