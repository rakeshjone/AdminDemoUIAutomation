package Framework.Setup;

import Framework.Data.Userdata;
import Framework.Root.AdminDemoUIApp;
import Framework.Util.DriverManager;
import Framework.Util.ConfigurationManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import javax.management.timer.Timer;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Setup {

    private Scenario scenario;
    private static final Logger logger = Logger.getLogger("blah");


    @BeforeAll
    public static void setupDriver() {
        resolvePropertiesFile();
    }

    @Before
    public void scenarioSetup(Scenario scenario){
        this.scenario = scenario;
        System.out.println("\u001B[32m" + "########starting thread: " + Thread.currentThread().getName() + " at " + Time.valueOf(LocalTime.now()) + "\u001B[0m");
        logger.info("\u001B[32m" + "###############Running test############# "+scenario.getName() + "\u001B[0m");
        switch (ConfigurationManager.getInstance().getProperty("Local").toUpperCase()) {
            case "TRUE":
                //Run tests locally
                logger.info("\u001B[32m" + "###############Loading local driver############ "+scenario.getName() + "\u001B[0m");
                DriverManager.getInstance().LoadDriver(ConfigurationManager.getInstance().getProperty("browser"));
                break;
            case "FALSE":
                //Run test via hub and node
                try {
                    logger.info("\u001B[32m" + "###############loading remote driver############# "+scenario.getName() + "\u001B[0m");
                    DriverManager.getInstance().LoadRemoteDriver(ConfigurationManager.getInstance().getProperty("browser"));
                } catch (MalformedURLException e) {
                    logger.info("\u001B[32m" + "###############Exception############# "+scenario.getName() + "\u001B[0m");
                    e.printStackTrace();
                }
                break;
        }
        DriverManager.getInstance().navigateToURL(ConfigurationManager.getInstance().getProperty("URL"));
    }


    @After
    public void scenarioReport(){
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) DriverManager.getInstance().Driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot");
        }

        if (!ConfigurationManager.getInstance().getProperty("browser").equals("firefox")){
            DriverManager.getInstance().closeDriver();
        }

        DriverManager.getInstance().quitDriver();
        DriverManager.getInstance().killSession();
    }

    @AfterAll
    public static void looseDriverManager(){
        DriverManager.getInstance().remove();
    }

    private static void resolvePropertiesFile(){
        switch (ConfigurationManager.getInstance().getProperty("Environment").toUpperCase()) {
            case "TEST" -> ConfigurationManager.getInstance().LoadAdditionalProperties("Test.properties");
            case "ACCEPTANCE" -> ConfigurationManager.getInstance().LoadAdditionalProperties("Acceptance.properties");
            default -> ConfigurationManager.getInstance().LoadAdditionalProperties("Delivery.properties");
        }
    }
}
