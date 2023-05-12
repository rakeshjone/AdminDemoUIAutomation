package Framework.Util;

import java.sql.Time;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import static java.util.concurrent.TimeUnit.*;

public class DriverManager {
	private WebDriver webDriver = null;
    public SearchContext Driver = null;
    private static DriverManager manager = null;

    private DriverManager() {
    }

    public Wait<WebDriver> fluentwait(){
        Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver)
                .withTimeout(Duration.ofSeconds(Integer.parseInt(ConfigurationManager.getInstance().getProperty("Timeout"))))
                .pollingEvery(Duration.ofMillis(Integer.parseInt(ConfigurationManager.getInstance().getProperty("Polling"))))
                .ignoring(NoSuchElementException.class);
        return wait;
    }

    public void pageReady(){
        Wait wait = fluentwait();
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }

    public static final DriverManager getInstance(){
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
               try{
               setWebDriver(new ChromeDriver());}
               catch (Exception e){}
               break;
       }
       webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(
               Integer.parseInt(ConfigurationManager.getInstance().getProperty("Timeout"))));
       webDriver.manage().window().maximize();
   }

    public void CloseDriver(){
        if (webDriver !=null){
        	webDriver.close();
        }
    }

    public void QuitDriver(){
        if (webDriver !=null){
            webDriver.quit();
        }
    }

    public void killSession(){
        webDriver = null;
        Driver = null;
    }

    public void navigateToURL(String URL){
        webDriver.get(URL);
    }

    private void setWebDriver(WebDriver webDriver) {
        if (this.webDriver==null){
            this.webDriver = webDriver;
            Driver = webDriver;
        }
    }

    public String getPageTitle(){
        return webDriver.getTitle();
    }

}
