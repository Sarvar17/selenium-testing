package edu.itpu.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;

    private By inputTaskName = By.id("taskName");
    private By inputEstimationTime = By.id("estimation");
    private By inputTaskDate = By.id("plannedDate");
    private By buttonCreateTask = By.xpath("//button[contains(text(), 'Create Task')]");
    private By buttonStartTask = By.xpath("//button[contains(text(), 'Start')]");
    private By buttonDeleteTask = By.xpath("//button[contains(text(), 'Delete')]");
    private By cardTask = By.cssSelector(".card.mb-3");
    private By cardTaskDate = By.xpath("//p[contains(text(), '2023-09-07')]");
    private By cardTaskName = By.cssSelector("div.card-header");
    private By cardTaskStatus = By.cssSelector(".card h5");
    private By cardTaskEstimationTime = By.xpath("//div[contains(@class, 'card-body')]//p[1]");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillTaskName(String taskName) {
        driver.findElement(inputTaskName).clear();
        driver.findElement(inputTaskName).sendKeys(taskName);
    }

    public void fillEstimation(int estimationTime) {
        driver.findElement(inputEstimationTime).sendKeys(String.valueOf(estimationTime));
    }

    public void fillTaskDate(String taskDate) {
        driver.findElement(inputTaskDate).sendKeys(taskDate);
    }

    public void clickCreateTask() {
        driver.findElement(buttonCreateTask).click();
    }

    public void createTask(String name, int estimation) {
        fillTaskName(name);
        fillEstimation(estimation);
        clickCreateTask();
    }

    public void createTaskWithDate(String name, int estimation, String taskDate) {
        fillTaskName(name);
        fillEstimation(estimation);
        fillTaskDate(taskDate);
        clickCreateTask();
    }

    public String getCreatedTaskName() {
        return driver.findElement(cardTaskName).getText();
    }

    public String getCreatedTaskStatus() {
        return driver.findElement(cardTaskStatus).getText();
    }

    public String getCreatedTaskEstimationTime() {
        return driver.findElement(cardTaskEstimationTime).getText();
    }

    public void startTask() {
        driver.findElement(buttonStartTask).click();
    }

    public void deleteTask() {
        driver.findElement(buttonDeleteTask).click();
    }

    public boolean onlyOneTaskCardVisible() {
        if (driver.findElements(cardTask).size() > 1)
            return false;
        else
            return true;
    }

    public boolean isTaskCardVisible() {
        return driver.findElement(cardTask).isDisplayed();
    }

    public boolean isTaskCardDateVisible() {
        return driver.findElement(cardTaskDate).isDisplayed();
    }
}
