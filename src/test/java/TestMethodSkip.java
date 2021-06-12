import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({MethodListener.class})
public class TestMethodSkip {


    private WebDriver driver = DriverFactory.getWebDriver("chrome");

    @Test
    public void test1() {
        this.driver.manage().window().maximize();
        this.driver.get("https://www.google.com");
        System.out.println(driver.getTitle());
    }

    @Test
    @NotProduction
    public void test2() {
        this.driver.get("https://www.amazon.in");
        System.out.println(driver.getTitle());
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        this.driver.quit();
    }
}
