
@tag
Feature: Purchase the order from the e-commerce Website
  
	Background: 
	Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And checkout <productName> and submit order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name  									 | password 		| productName	|
      | levente.banyoi@gmail.com | P2ssw0rd			| ZARA COAT 3	|

