package Framework.Root;

import Framework.Util.ConfigurationManager;
import Pages.DashboardPage;
import Pages.LoginPage;

import java.io.IOException;

public class AdminDemoUIApp {
    private static LoginPage loginPage = new LoginPage("Your store. Login");
    private static DashboardPage dashboardPage = null;

    public static DashboardPage dashboardPage() {
        if (dashboardPage == null){
            dashboardPage = new DashboardPage("Dashboard / nopCommerce administration");
        }
        return dashboardPage;
    }

    public static LoginPage loginPage() {
        return loginPage;
    }
}
