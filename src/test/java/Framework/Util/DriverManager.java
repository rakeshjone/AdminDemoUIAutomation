package Framework.Util;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import static java.util.concurrent.TimeUnit.*;

public class DriverManager {
	private WebDriver webDriver = null;
    public SearchContext Driver = null;
    private static ThreadLocal<DriverManager> manager = new ThreadLocal<DriverManager>();;

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

    public static DriverManager getInstance(){
        if (manager.get()==null){
            manager.set(new DriverManager());
        }
        return manager.get();
    }

   public void LoadDriver(String browser) {
       switch (browser) {
           case "edge":
               setWebDriver(new EdgeDriver());
               break;
           case "firefox":
               setWebDriver(new FirefoxDriver());
               break;
           default:
               ChromeOptions options = new ChromeOptions();
               if (ConfigurationManager.getInstance().getProperty("emulate").equals("y")) {
                   Map<String, Object> emulation = new HashMap<>();
                   emulation.put("deviceName", ConfigurationManager.getInstance().getProperty("mobileDevice"));
                   options.setExperimentalOption("mobileEmulation", emulation);
               }
               setWebDriver(new ChromeDriver(options));
               break;
       }
       webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(
               Integer.parseInt(ConfigurationManager.getInstance().getProperty("Timeout"))));
       webDriver.manage().window().maximize();
   }

    public void LoadRemoteDriver(String browser) throws MalformedURLException {
        switch (browser) {
            case "edge":
                setWebDriver(new EdgeDriver());
                break;
            case "firefox":
                setWebDriver(new FirefoxDriver());
                break;
            default:
                /*
                ChromeOptions options = new ChromeOptions();
                if (ConfigurationManager.getInstance().getProperty("emulate").equals("y")) {
                    Map<String, Object> emulation = new HashMap<>();
                    emulation.put("deviceName", ConfigurationManager.getInstance().getProperty("mobileDevice"));
                    options.setExperimentalOption("mobileEmulation", emulation);
                }*/
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setBrowserName("chrome");
                //Set hub and node locally or on docker
                //change Docker property in default.properties
                if (ConfigurationManager.getInstance().getProperty("Docker").toUpperCase().equals("TRUE")) {
                    //if hub and nodes are setup on docker
                    System.out.println("\u001B[32m" + "########running on docker driver manager############# " + "\u001B[0m");
                    setWebDriver(new RemoteWebDriver(new URL(ConfigurationManager.getInstance().getProperty("DOCKERHUB")),capabilities));
                    System.out.println("$$$$$$$$$$$$$running on docker driver manager$$$$$$$$$$$$$$$$$$$");
                }
                else {
                    //if hub and node are setup locally
                    System.out.println("\u001B[32m" + "########running on local docker driver manager############# " + "\u001B[0m");
                    setWebDriver(new RemoteWebDriver(new URL(ConfigurationManager.getInstance().getProperty("LOCALHUB")),capabilities));
                    System.out.println("$$$$$$$$$$$$$running on local docker driver maanager$$$$$$$$$$$$$$$$$$$");
                }

                break;
        }
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(
                Integer.parseInt(ConfigurationManager.getInstance().getProperty("Timeout"))));
        webDriver.manage().window().maximize();
    }

	public void loadElectronDriver() {
        //System.setProperty(
          //      "webdriver.chrome.driver",
            //    "C:\\Users\\RakeshChauhan\\Downloads\\chromedriver-v32.2.0-win32-x64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        //options.setBinary(
          //      "C:\\Users\\RakeshChauhan\\AppData\\Local\\Programs\\pbn-desktop-app\\Practice by Numbers.exe");
        options.setAcceptInsecureCerts(true);
        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        options.addArguments("--no-sandbox"); // Bypass OS security model
        options.addArguments("--headless");
        webDriver = new ChromeDriver(options);
        Driver = webDriver;
        String mainWindow = webDriver.getWindowHandle();
        Set<String> openedWindows = webDriver.getWindowHandles();
        for (String newWindow: openedWindows) {
            webDriver.switchTo().window(newWindow);
            if (Objects.equals(webDriver.getTitle(), "Practice by Numbers")) {
                break;
            }
        }
    }

	
    public void closeDriver(){
        if (webDriver !=null){
        	webDriver.close();
        }
    }

    public void quitDriver(){
        if (webDriver !=null){
            webDriver.quit();
        }
    }

    public void killSession(){
        webDriver = null;
        Driver = null;
    }

    public void remove(){
        manager.remove();
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

	public void startListeningToAPIRequests(String matcher) {
        DevTools devTools = ((HasDevTools)webDriver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));
        devTools.addListener(
            Network.requestWillBeSent(),
            request -> {
              if (request.getRequest().getUrl().contains(matcher)) {
                for (String headerPair : request.getRequest().getHeaders().toString().split(",")) {
                  if (headerPair.split("=")[0].trim().equals("X-CSRFTOKEN")) {
                    csrfToken = headerPair.split("=")[1];
                  }
                }
              }
            });
    }


    public void startListeningToAPIResponses(String matcher, ResourceType type) {
        logger.info("listening to API responses for API matching with: " + matcher);
        DevTools devTools = ((HasDevTools) webDriver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(),Optional.empty(),
                Optional.empty()));
        devTools.addListener(
            Network.responseReceived(),
            responseReceived -> {
              if (responseReceived.getResponse().getUrl().contains(matcher)
                  && responseReceived.getType() == type) {
                response = responseReceived.getResponse().getStatus();
                logger.info(
                        "\u001B[32m" + "response logged for "
                        + matcher
                        + ": "
                        + response
                        + " status_text: "
                        + responseReceived.getType()
                        + " method: "
                        + responseReceived.getResponse().getUrl()
                        + " by thread: "
                        + Thread.currentThread().getName() + "\u001B[0m");
              }
            });
    }

    @SuppressWarnings("resource")
    public void interceptNetworkRequests(String matcher) {
        logger.info("listening to API responses for API matching with: " + matcher);
        NetworkInterceptor networkInterceptor = new NetworkInterceptor(
                webDriver,
                (Filter)
                    next ->
                        req -> {
                        System.out.println(req.getHeader("x-csrftoken"));
                        if (req.getUri().contains(matcher)) {
                            response = next.execute(req).getStatus();
                        }
                        return next.execute(req);
                }
        );
    }

}
