package org.aqa.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SignUpTest {

    WebDriver driver;
    public static final String ZIP_CODE_ERROR = "Oops, error on page. ZIP code should have 5 digits";
    public static final String USER_INFO_ERROR = "Oops, error on page." + " Some of your fields have invalid data or email was previously used";

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
    }

    @Test
    public void zipCodeShouldBeValid(){
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value='Continue']")).click();
        final WebElement registerButton =
                driver.findElement(By.cssSelector("[value='Register']"));
        final boolean isRegisterButtonDisplayed = registerButton.isDisplayed();
        assertTrue(isRegisterButtonDisplayed,
                "User was not redirected to the registration page");
    }

    @Test
    public void userShouldNotBeRegisteredWithEmptyZipCode(){
            driver.findElement(By.cssSelector("input[value='Continue']")).click();
            WebElement errorMessage = driver.findElement(By.className("error_message"));
            assertTrue(errorMessage.isDisplayed(),
                    "Error message is not displayed in case of empty zip code");
            assertEquals(errorMessage.getText(), ZIP_CODE_ERROR,
                    "Error message should be correct");

    }


    @AfterMethod
    public void closeWebDr(){
        driver.quit();
    }
}
