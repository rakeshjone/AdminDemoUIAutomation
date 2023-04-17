package Framework.Setup;

import Framework.Util.ConfigurationManager;

public class Setup {

    public void SetupDriver(){
        ResolvePropertiesFile();
    	//DriverManager.getInstance().LoadDriver("chrome");
    	//DriverManager.getInstance().navigateToURL("https://admin-demo.nopcommerce.com/login");
        DriverManager.getInstance().LoadDriver(ConfigurationManager.getInstance().getProperty("browser"));
        DriverManager.getInstance().navigateToURL(ConfigurationManager.getInstance().getProperty("URL"));
    }

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
