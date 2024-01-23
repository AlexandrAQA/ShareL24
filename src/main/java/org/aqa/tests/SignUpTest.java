package org.aqa.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SignUpTest {
         /*
       1. Open browser
       2. Go to main page https://www.sharelane.com/cgi-bin/register.py
       3. Click on the zip code field
       4. 12345
       5. click on Continue

        */
    @Test
    public void zipCodeShouldBeValid(){
        WebDriver driver = new ChromeDriver();

    }
}
