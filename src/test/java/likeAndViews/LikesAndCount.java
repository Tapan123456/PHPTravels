package likeAndViews;

import baseClass.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LikesAndCount extends TestBase {
    SoftAssert softassert = new SoftAssert();
    WebDriver driver = launchBrowser("Chrome");

    @BeforeTest
    public void open() {
        driver.get("https://phptravels.com/blog/");
    }

    @Test(priority=0)
    public void openLastPost() {
        //-----------------------------------------------open last post-------------------------------------------------------------------------
        driver.findElement(By.xpath("//section[@id='main']//div[@class='post-item-horizontal'][last()]//a[2]")).click();
    }

    @Test(priority=1)
    public void lastPostOperations() {
        //-----------------------------------------------title and date-------------------------------------------------------------------------
        String titleLastPost = driver.findElement(By.xpath("//h1")).getText();
        String titleDate = driver.findElement(By.xpath("//div[@class='post-title']//following-sibling::div[@class='post-meta']//span[1]")).getText();
        System.out.println("Last post is "+titleLastPost+" and Date is "+titleDate);

        //-----------------------------------------------like and number of likes----------------------------------------------------------------
        driver.findElement(By.xpath("//span[text()='Like']//parent::button")).click();
        String likes=driver.findElement(By.xpath("//span[text()='Like']/parent::button/parent::div/preceding-sibling::div[1]//span")).getText();
        System.out.println("Likes to the last post are "+likes);

        //-----------------------------------------------views count-----------------------------------------------------------------------------
        String views = driver.findElement(By.xpath("//div[@class='post-title']//following-sibling::div[@class='post-meta']//span[3]")).getText();
        int oldViews = stringToInteger(views);
        System.out.println("View to the last post are "+oldViews);

        //-----------------------------------------------views count verification----------------------------------------------------------------
        driver.navigate().back();
        driver.navigate().forward();
        views = driver.findElement(By.xpath("//div[@class='post-title']//following-sibling::div[@class='post-meta']//span[3]")).getText();
        int newViews = stringToInteger(views);
        softassert.assertEquals(newViews,oldViews+1);
    }

    public int stringToInteger(String str){
        str=str.replaceAll(" ","");
        return Integer.parseInt(str);
    }
    @AfterTest
    public void finalOperation(){
        softassert.assertAll();
        System.out.println("Likes and Views Test Cases Executed Successfully");
        driver.quit();
    }
}
