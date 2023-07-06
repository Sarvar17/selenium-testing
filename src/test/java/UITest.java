import edu.itpu.selenium.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UITest {
    private String url = "https://orlovrs.github.io/time-tracker/";
    protected WebDriver driver;
    protected MainPage mainPage;

    @BeforeEach
    void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
        mainPage = new MainPage(driver);
    }

    @AfterEach
    void quit() {
        driver.quit();
    }
}
