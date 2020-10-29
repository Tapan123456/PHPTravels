package footerTest;

import baseClass.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class TestingFooter extends TestBase {
    SoftAssert softassert = new SoftAssert();
    WebDriver driver = launchBrowser("Chrome");

    @BeforeTest
    public void open() {
        driver.get("https://phptravels.com/demo/");
    }

    @Test(priority=0)
    public void copyRightCheck(){
        //------------------------------------------------checking if copyright contains 2020-----------------------------------------------------------------
        String copyRight = driver.findElement(By.xpath("//div[@class='copyright hidden-xs']/p")).getText();
        softassert.assertTrue(copyRight.contains("2020"), "Doesn't contain 2020");
    }
    @Test(priority=1)
    public void socialMediaLinks(){
        //------------------------------------------------all social media links----------------------------------------------------------------
        List<WebElement> list = driver.findElements(By.cssSelector("ul.links-social > li > form"));
        System.out.println("Total Social Media Links :" + list.size());
        for (WebElement w : list) {
            System.out.println(w.getAttribute("action"));
        }
    }
    @AfterTest
    public void finalOperation(){
        softassert.assertAll();
        System.out.println("CopyRight and Social media Test Case Executed Successfully");
    }
}
