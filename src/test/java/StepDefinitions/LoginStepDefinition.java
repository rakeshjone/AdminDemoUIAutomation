package StepDefinitions;

import io.cucumber.java.en.*;
import Framework.Setup.*;

public class LoginStepDefinition {

@Given("I navigate to admin demo website")
public void i_navigate_to_admin_demo_website() {
    System.out.println("Given");
    Setup s = new Setup();
    s.SetupDriver();
    s.Cleanup();
}

@Given("I enter user credentials")
public void i_enter_user_credentials() {
    System.out.println("And");
}

@When("I click on login button")
public void i_click_on_login_button() {
    System.out.println("When");
}

@Then("I am navigated to home page")
public void i_am_navigated_to_home_page() {
    System.out.println("Then");
}
}
