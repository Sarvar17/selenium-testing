import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleTests extends UITest {
    private final String url = "https://orlovrs.github.io/time-tracker/";
    private WebDriver driver;
    private final int estimation = 15;
    private final String taskName = "Selenium test";
    private final String initialTaskStatus = "waiting";
    private final String taskStatusInProgress = "in progress";
    private final String taskDate = "09-07-2023";

    @Test
    void simpleTest() {
        mainPage.createTask(taskName, estimation);

        String actualTaskName = mainPage.getCreatedTaskName();
        String actualTaskStatus = mainPage.getCreatedTaskStatus();
        String actualEstimationTime = mainPage.getCreatedTaskEstimationTime();

        assertTrue(mainPage.isTaskCardVisible(), "Card didn't appear");
        assertEquals(taskName, actualTaskName,
                String.format("Task name is different from the passed. Actual value: %s",actualTaskName));
        assertTrue(actualTaskStatus.contains(initialTaskStatus),
                String.format("The status is different from waiting: %s", actualTaskStatus));
        assertTrue(actualEstimationTime.contains(String.valueOf(estimation)),
                String.format("The estimation time is different from %s: %s", estimation, actualEstimationTime));
    }

    @Test
    void testCheckAllStatuses() {
        mainPage.createTask(taskName, estimation);
        mainPage.startTask();

        String actualTaskStatus = mainPage.getCreatedTaskStatus();
        assertTrue(actualTaskStatus.contains(taskStatusInProgress),
                String.format("The status is different from 'in progress'. %s", actualTaskStatus));
    }


    @Test
    void testDeleteTask() {
        mainPage.createTask(taskName, estimation);
        mainPage.createTask(taskName, estimation);
        mainPage.deleteTask();

        assertTrue(mainPage.onlyOneTaskCardVisible(),
                "There are still 2 cards. One card supposed to be removed");
    }

    @Test
    void testCreateTaskWithDate() {
        mainPage.createTaskWithDate(taskName, estimation, taskDate);

        assertTrue(mainPage.isTaskCardDateVisible(), "Card didn't appear");
    }
}
