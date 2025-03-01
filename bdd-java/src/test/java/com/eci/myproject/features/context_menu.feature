Feature: Context Menu on The Internet Website

  Scenario: User right-clicks on the box and sees an alert
    Given I am on the context menu page
    When I right-click on the box
    Then I should see an alert with the message "You selected a context menu"
    And I should be able to accept the alert
