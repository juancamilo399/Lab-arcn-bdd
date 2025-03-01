package com.eci.myproject.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class ContextMenuSteps {

    private WebDriver driver;

    @Before
    public void setUp() {
        try {
            System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize ChromeDriver", e);
        }
    }

    @Given("I am on the context menu page")
    public void i_am_on_the_context_menu_page() {
        driver.get("https://the-internet.herokuapp.com/context_menu");
    }

    @When("I right-click on the box")
    public void i_right_click_on_the_box() {
        WebElement contextMenuBox = driver.findElement(By.id("hot-spot"));
        Actions actions = new Actions(driver);
        actions.contextClick(contextMenuBox).perform();
    }

    @Then("I should see an alert with the message {string}")
    public void i_should_see_an_alert_with_the_message(String string) {
        Alert alert = driver.switchTo().alert();
        String alertMessage = alert.getText();
        assertTrue("Alert message doesn't match", alertMessage.contains(string));
    }

    @Then("I should be able to accept the alert")
    public void i_should_be_able_to_accept_the_alert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

