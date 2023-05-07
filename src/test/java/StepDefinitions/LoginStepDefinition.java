package StepDefinitions;

import Framework.Root.AdminDemoUIApp;
import io.cucumber.java.en.*;

public class LoginStepDefinition {

@Given("I navigate to admin demo website")
public void i_navigate_to_admin_demo_website() {
    System.out.println("Given");
    AdminDemoUIApp.loginPage().clickOnLoginButton();
}

@Given("I enter user credentials")
public void i_enter_user_credentials() {
    System.out.println("And---");
}

@When("I click on login button")
public void i_click_on_login_button() {
    System.out.println("When----");
}

@Then("I am navigated to dashboard page")
public void i_am_navigated_to_dashboard_page() {
    System.out.println("Then------");
    AdminDemoUIApp.dashboardPage().VerifyDashboardPageIsDisplayed();
}
}
