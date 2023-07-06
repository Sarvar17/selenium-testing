import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleTests {
    private String url = "https://orlovrs.github.io/time-tracker/";
    private WebDriver driver;
    private int estimation = 15;
    private String taskName = "Selenium test";
    private String initialTaskStatus = "waiting";

    @BeforeEach
    void prepareTest() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
    }

    @AfterEach
    void clearTest() {
        driver.quit();
    }

    @Test
    void simpleTest() {
        driver.findElement(By.id("taskName")).sendKeys(taskName);
        driver.findElement(By.cssSelector("#estimation")).sendKeys(String.valueOf(estimation));
        driver.findElement(By.xpath("//button[contains(text(), 'Create Task')]")).click();

        assertTrue(driver.findElement(By.cssSelector("div.card")).isDisplayed(),
                "Card didn't appear");
        assertEquals(taskName, driver.findElement(By.cssSelector("div.card-header")).getText(),
                String.format(
                        "Task name is different from the passed. Actual value: %s",
                        driver.findElement(By.cssSelector("div.card-header")).getText()
                )
        );
        assertTrue(driver.findElement(By.cssSelector(".card h5")).getText().contains(initialTaskStatus),
                String.format(
                        "The status is different from waiting: %s",
                        driver.findElement(By.cssSelector(".card h5")).getText()
                )
        );
        assertTrue(driver.findElement(By.xpath("//div[contains(@class, 'card-body')]//p[1]"))
                        .getText().contains(String.valueOf(estimation)),
                String.format(
                        "The estimation time is different from %s: %s",
                        estimation,
                        driver.findElement(By.xpath("//div[contains(@class, 'card-body')]//p[1]")).getText()
                ));
    }
}
