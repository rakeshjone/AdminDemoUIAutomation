package Framework.Util;

import java.sql.Time;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import static java.util.concurrent.TimeUnit.*;

public class DriverManager {
	private WebDriver webDriver;
    public static SearchContext Driver;
    private static DriverManager manager = null;

    private DriverManager() {
    }

    public Wait<WebDriver> iwait(){
        Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver)
                .withTimeout(Duration.ofSeconds(Integer.parseInt(ConfigurationManager.getInstance().getProperty("Timeout"))))
                .pollingEvery(Duration.ofMillis(Integer.parseInt(ConfigurationManager.getInstance().getProperty("Polling"))))
                .ignoring(NoSuchElementException.class);
        return wait;
    }

    public static DriverManager getInstance(){
        if (manager==null){
            manager = new DriverManager();
        }
        return manager;
    }

   public void LoadDriver(String browser) {
       switch (browser) {
           case "edge":
               setWebDriver(new EdgeDriver());
               break;
           default:
               setWebDriver(new ChromeDriver());
               break;
       }
       webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
       //webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
       webDriver.manage().window().maximize();
   }

    public void CloseDriver(){
        if (webDriver !=null){
        	webDriver.close();
        	webDriver.quit();
        }
    }

    public void navigateToURL(String URL){
        webDriver.get(URL);
    }

    private void setWebDriver(WebDriver webDriver) {
    	this.webDriver = webDriver;
        Driver = webDriver;
    }

    public String getPageTitle(){
        return webDriver.getTitle();
    }

}
