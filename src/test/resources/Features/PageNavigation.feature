@NavigationFeature
Feature: PageNavigation

  @Navigation
  Scenario Outline: Verify I can navigate to products pages on admin demo website
    Given I navigate to admin demo website
    And I click on login button
    And I am navigated to dashboard page
    When I click on "<PageTitle>" link on MainNavTreeMenu
    Then I am navigate to "<PageTitle>" page
    Examples:
    |PageTitle|
    |Products |
    |TaxSettings|
