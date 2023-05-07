@LoginFeature
Feature: Login

  @login
  Scenario: Verify I can login to admin demo website
    Given I navigate to admin demo website
    And I enter user credentials
    When I click on login button
    Then I am navigated to dashboard page
