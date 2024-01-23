package org.aqa.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class SignUpTest {
         /*
       1. Open browser
       2. Go to main page https://www.sharelane.com/cgi-bin/register.py
       3. Click on the zip code field
       4. 12345
       5. click on Continue
       6. check that we are on the registration page
        */
    @Test
    public void zipCodeShouldBeValid(){
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value='Continue']")).click();

        final WebElement registerButton =
                driver.findElement(By.cssSelector("[value='Register']"));
        final boolean isRegisterButtonDisplayed = registerButton.isDisplayed();

        assertTrue(isRegisterButtonDisplayed,
                "User was not redirected to the registration page");

        driver.quit();
    }
}
