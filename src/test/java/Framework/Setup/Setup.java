package Framework.Setup;

import Framework.Util.DriverManager;
import Framework.Util.ConfigurationManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Setup {

    @Before
    public void SetupDriver(){
        ResolvePropertiesFile();
        DriverManager.getInstance().LoadDriver(ConfigurationManager.getInstance().getProperty("browser"));
        DriverManager.getInstance().navigateToURL(ConfigurationManager.getInstance().getProperty("URL"));
    }

    @After
    public void Cleanup(){
        DriverManager.getInstance().CloseDriver();
    }

    private void ResolvePropertiesFile(){
    	
        switch (ConfigurationManager.getInstance().getProperty("Environment").toUpperCase()) {
            case "TEST" -> ConfigurationManager.getInstance().LoadAdditionalProperties("Test.properties");
            case "ACCEPTANCE" -> ConfigurationManager.getInstance().LoadAdditionalProperties("Acceptance.properties");
            default -> ConfigurationManager.getInstance().LoadAdditionalProperties("Delivery.properties");
        }
    }
}
