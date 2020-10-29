package registerAndLogin;

import baseClass.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class RegisterLogin extends TestBase {
    SoftAssert softassert = new SoftAssert();
    WebDriver driver = launchBrowser("Chrome");
    String oldPassword="";

    @BeforeTest
    public void open() {
        driver.get("https://phptravels.com/demo/");
    }

    @Test(priority=0)
    public void register(){
        //------------------------------------------------Go to Blog -> Register-----------------------------------------------------------------
        //will open in NEW TAB
        driver.findElement(By.linkText("Blog")).click();
        Set handles = driver.getWindowHandles();
        Iterator<String> it = handles.iterator();
        String i = new ArrayList<String>(handles).get(1);
        driver.switchTo().window(i);

        /*Will open in SAME TAB
        WebElement href = driver.findElement(By.linkText("Blog"));
        driver.get(href.getAttribute("href"));*/

        //clicking on register
        driver.findElements(By.xpath("//li[@class='nav-item-right ']")).get(1).click();
        //sending data
        driver.findElement(By.name("username")).sendKeys("ghijklmnop");//at least 4 letters
        String username = driver.findElement(By.name("username")).getAttribute("value");
        softassert.assertTrue(username.length()>=4,"Username Length should be > 4");
        driver.findElement(By.name("email")).sendKeys("ghijklmnop@gmail.com");
        driver.findElement(By.name("password")).sendKeys("ghijklmnop@gmail.com");
        oldPassword = driver.findElement(By.name("password")).getAttribute("value");
        driver.findElement(By.name("confirm_password")).sendKeys("ghijklmnop@gmail.com");
        driver.findElement(By.xpath("//span[@class='checkbox-icon']/i")).click();
        //if(isAnyFieldEmpty("//input[@class='form-control']")==false){
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        //}

    }
    /*boolean isAnyFieldEmpty(String xpathOfRegisterForm) {
        List<WebElement> myFields = driver.findElements(By.xpath(xpathOfRegisterForm));
        for(WebElement field : myFields)
        {
            if (field.getAttribute("value") == " ") {
                return true;
            }
        }
        return false;
    }*/

    @Test(dependsOnMethods = { "register" },priority=1)
    public void changePassword(){
        //-----------------------------------------------Change old password-------------------------------------------------------------------
        driver.findElement(By.xpath("//ul[@class='nav']/li[3]")).click();
        driver.findElement(By.xpath("//input[@name='old_password']")).sendKeys(oldPassword);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("artofliving");
        driver.findElement(By.xpath("//input[@name='password_confirm']")).sendKeys("artofliving");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }
    @Test(dependsOnMethods = { "changePassword" },priority=2)
    public void logout(){
        //----------------------------------------------logout----------------------------------------------------------------------------------
        driver.findElements(By.xpath("//i[@class='icon-arrow-down']")).get(0).click();
        driver.findElement(By.xpath("//ul[@class='dropdown-menu top-dropdown']/li/a[contains(@href, 'https://phptravels.com/blog/logout')]")).click();
        //driver.findElement(By.xpath("//ul[@class='dropdown-menu top-dropdown']/li[4]")).click();
    }
    @AfterTest
    public void finalOperation(){
        softassert.assertAll();
        System.out.println("Register and Login Test Case Executed Successfully");
        driver.quit();
    }
}
