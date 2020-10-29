package baseClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.concurrent.TimeUnit;

public class TestBase {
    WebDriver driver;
    public WebDriver launchBrowser(String browser){
        if(browser.equals("Mozilla")){
            FirefoxOptions fo=new FirefoxOptions();
            //run time profile making
            FirefoxProfile pro = new FirefoxProfile();
            pro.setPreference("dom.webnotifications.enabled","false");
            fo.setProfile(pro);
            driver = new FirefoxDriver(fo);
        }else if(browser.equals("Chrome")){
            //System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY,"Logs\\chrome.log");
            System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY,"true");
            ChromeOptions ops = new ChromeOptions();
            //disable notifications, maximize screen and ignore errors without profiling
            ops.addArguments("--disable-notifications");
            ops.addArguments("--start-maximized");
            driver = new ChromeDriver(ops);
        }
        //dynamic wait - it will search the element only for 10 seconds
        //global timeout - for all driver.findElement
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }
}
