package StepDefinitions;

import Framework.Root.AdminDemoUIApp;
import io.cucumber.java.en.*;

public class PageNavigationStepDefinition {
    @When("I click on {string} link on MainNavTreeMenu")
    public void i_click_on_link_on_main_nav_tree_menu(String link) {
        switch (link) {
            case "Products":
                AdminDemoUIApp.navigationPane().expandMainNavTreeInSideMenu("Catalog");
                AdminDemoUIApp.navigationPane().clickOnPageNavigationLinkInMainNavTreeMenu(link);
                break;
            case "TaxSettings":
                AdminDemoUIApp.navigationPane().expandMainNavTreeInSideMenu("\n" + "Configuration");
                AdminDemoUIApp.navigationPane().expandSubNavTreeInSideMenu("\n" + "Settings");
                AdminDemoUIApp.navigationPane().clickOnPageNavigationLinkInSubNavTreeMenu(link);
                break;
        }
    }

    @Then("I am navigate to {string} page")
    public void i_am_navigate_to_link_page(String page) {
        switch (page){
            case "Products":
                AdminDemoUIApp.productsPage().VerifyProductsPageIsDisplayed();
                break;
            case "TaxSettings":
                break;
        }
        AdminDemoUIApp.navigationPane().clickOnLogoutButton();
    }

}
