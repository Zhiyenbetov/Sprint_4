import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public abstract class AbstractUiTestParent {

    protected WebDriver driver;

    @Before
    public void init() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");

        FirefoxOptions firefoxOptions = new FirefoxOptions();

//        driver = new ChromeDriver(options);
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @After
    public void teardown() {
        driver.quit();
    }

}
