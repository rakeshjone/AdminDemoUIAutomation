package Framework.Root;

import Framework.Data.DataManager;
import Framework.Data.Userdata;
import Framework.Util.ConfigurationManager;
import Pages.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class AdminDemoUIApp {
    private static LoginPage loginPage = null;
    private static DashboardPage dashboardPage = null;
    private static ProductsPage productsPage = null;
    private static TaxSettingsPage taxSettingsPage = null;
    private static NavigationPane navigationPane = null;
    private static Userdata userdata = new Userdata();

    public static Userdata userdata(){
        ObjectMapper om = new ObjectMapper();
        try {
            userdata = om.readValue(new File("src/test/resources/Data/UserData.json"), Userdata.class);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return userdata;
    }
    public static DashboardPage dashboardPage() {
        return new DashboardPage("Dashboard / nopCommerce administration");
    }

    public static LoginPage loginPage() {
        return new LoginPage("Your store. Login");
    }

    public static ProductsPage productsPage() {
        return new ProductsPage("Products / nopCommerce administration");
    }

    public static TaxSettingsPage taxSettingsPage(){
        return new TaxSettingsPage("Tax settings / nopCommerce administration");
    }

    public static NavigationPane navigationPane(){
        return new NavigationPane();
    }
}
