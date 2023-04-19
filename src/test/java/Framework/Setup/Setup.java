package Framework.Setup;

import Framework.Util.DriverManager;
import Framework.Util.ConfigurationManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Setup {

    @Before
    public void SetupDriver(){
        System.out.println("Before");
        ResolvePropertiesFile();
    	//DriverManager.getInstance().LoadDriver("chrome");
    	//DriverManager.getInstance().navigateToURL("https://admin-demo.nopcommerce.com/login");
        DriverManager.getInstance().LoadDriver(ConfigurationManager.getInstance().getProperty("browser"));
        DriverManager.getInstance().navigateToURL(ConfigurationManager.getInstance().getProperty("URL"));
    }

    @After
    public void Cleanup(){
        DriverManager.getInstance().CloseDriver();
    }

    private void ResolvePropertiesFile(){
    	
        switch (ConfigurationManager.getInstance().getProperty("Environment")) {
            case "Test" -> ConfigurationManager.getInstance().LoadAdditionalProperties("Test.properties");
            case "Acceptance" -> ConfigurationManager.getInstance().LoadAdditionalProperties("Acceptance.properties");
            default -> ConfigurationManager.getInstance().LoadAdditionalProperties("Delivery.properties");
        }
    }
}
