package org.aqa.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SignUpTest {

    WebDriver driver;
    public static final String ZIP_CODE_ERROR = "Oops, error on page. ZIP code should have 5 digits";
    public static final String USER_INFO_ERROR = "Oops, error on page." +
            " Some of your fields have invalid data or email was previously used";

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void zipCodeShouldBeValid() {
        WebElement zip_code_field = driver.findElement(By.name("zip_code"));
        zip_code_field.sendKeys("67890");

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement continueButton = driver.findElement(By.cssSelector("input[value='Continue']"));
        jsExecutor.executeScript("arguments[0].click();", continueButton);

        WebElement registerButton = driver.findElement(By.cssSelector("input[value='Register']"));
        assertTrue(registerButton.isDisplayed(),
                "Redirection issue");
    }

    @Test
    public void userShouldNotBeSignedUpWithEmptyZipCode(){
        driver.findElement(By.cssSelector("input[value='Continue']")).click();
        WebElement errorMessage = driver.findElement(By.className("error_message"));
        assertTrue(errorMessage.isDisplayed(), "Error message is not displayed in case of empty zip code");
        assertEquals(errorMessage.getText(), ZIP_CODE_ERROR, "Error message should be correct");
    }

    @Test
    public void userValidInfoSignUp(){
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=67890");
        driver.findElement(By.cssSelector("input[name='first_name']")).sendKeys("Mark");
        driver.findElement(By.cssSelector("input[name='last_name']")).sendKeys("Tester");
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys("testadamsmith1@gmail.com");
        driver.findElement(By.cssSelector("input[name='password1']")).sendKeys("0234adam_smith");
        driver.findElement(By.cssSelector("input[name='password2']")).sendKeys("0234adam_smith");
        driver.findElement(By.cssSelector("input[value='Register")).click();

        assertTrue(driver.findElement(By.className("confirmation_message")).isDisplayed(),
                "The message\"Account is created\" is displayed");

    }

    @Test
    public void userInvalidInfo(){
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=67890");
        driver.findElement(By.cssSelector("input[value='Register")).click();
        WebElement errorMsg = driver.findElement(By.className("error_message"));
        assertTrue(errorMsg.isDisplayed(),"Error message is not displayed in case of registration with empty user info");
        assertEquals(errorMsg.getText(), USER_INFO_ERROR, "Error message should be correct");

    }

//    Email:	linda_rao@817.86.sharelane.com
//    Password:	1111
//    Email:	brian_gupta@220.46.sharelane.com
//    Password	1111
    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        //driver.quit();
    }
}
