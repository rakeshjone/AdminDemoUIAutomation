package Framework.Util;

import java.time.Duration;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverManager {
	private WebDriver webDriver;
    public static SearchContext Driver;
    private static DriverManager manager = null;

    private DriverManager() {
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
       webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
       webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
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
    
}
