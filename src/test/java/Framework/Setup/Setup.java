package Framework.Setup;

import Framework.Util.DriverManager;
import Framework.Util.ConfigurationManager;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Setup {

    private Scenario scenario;
    private static final Logger logger = Logger.getLogger("blah");


    @BeforeAll
    public static void SetupDriver(){
        ResolvePropertiesFile();
    }

    @Before
    public void ScenarioSetup(Scenario scenario){
        this.scenario = scenario;
        logger.info("###############Running step############# "+scenario.getName());
        DriverManager.getInstance().LoadDriver(ConfigurationManager.getInstance().getProperty("browser"));
        DriverManager.getInstance().navigateToURL(ConfigurationManager.getInstance().getProperty("URL"));
    }


    @After
    public void ScenarioReport(){
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) DriverManager.getInstance().Driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot");
        }
        DriverManager.getInstance().CloseDriver();
        DriverManager.getInstance().QuitDriver();
    }


    private static void ResolvePropertiesFile(){
        switch (ConfigurationManager.getInstance().getProperty("Environment").toUpperCase()) {
            case "TEST" -> ConfigurationManager.getInstance().LoadAdditionalProperties("Test.properties");
            case "ACCEPTANCE" -> ConfigurationManager.getInstance().LoadAdditionalProperties("Acceptance.properties");
            default -> ConfigurationManager.getInstance().LoadAdditionalProperties("Delivery.properties");
        }
    }
}
