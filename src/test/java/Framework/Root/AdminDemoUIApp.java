package Framework.Root;

import Framework.Util.ConfigurationManager;
import Pages.*;

import java.io.IOException;

public class AdminDemoUIApp {
    private static LoginPage loginPage = new LoginPage("Your store. Login");
    private static DashboardPage dashboardPage = null;
    private static ProductsPage productsPage = null;
    private static TaxSettingsPage taxSettingsPage = null;
    private static NavigationPane navigationPane = null;

    public static DashboardPage dashboardPage() {
        if (dashboardPage == null){
            dashboardPage = new DashboardPage("Dashboard / nopCommerce administration");
        }
        return dashboardPage;
    }

    public static LoginPage loginPage() {
        if (loginPage == null){
            loginPage = new LoginPage("Your store. Login");
        }
        return loginPage;
    }

    public static ProductsPage productsPage() {
        if (productsPage ==null){
            productsPage = new ProductsPage("Products / nopCommerce administration");
        }
        return productsPage;
    }

    public static TaxSettingsPage taxSettingsPage(){
        if ((taxSettingsPage==null)){
            taxSettingsPage = new TaxSettingsPage("Tax settings / nopCommerce administration");
        }
        return taxSettingsPage;
    }

    public static NavigationPane navigationPane(){
        if (navigationPane == null){
            navigationPane = new NavigationPane();
        }
        return navigationPane;
    }
}
