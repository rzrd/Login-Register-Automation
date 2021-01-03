@Test
Feature: Register and Login
	In order to use the system
	As a registered user of the system
	I want to be able to Register new user
	and then log in to the system
	
Background: 
	Given Initiate browser

Scenario Outline: Register new account
	Given User open "http://automationpractice.com/" URL on any browser
	And User click sign in nav
	And User fill email address in create an account box
	And User click create an account button
	And User choose title
	And User enter name "<firstname>" and "<lastname>"
	And User enter account "<password>" and check email
	And user enter date of birth with "<date>", "<month>", "<year>"
	And user checklist for newsletter and special offers
	And user enter address "<address>", "<city>" and "<state>"
	And user enter address detail "<zip code>", "<country>", and "<mobile phone>"
	When User click register button
	Then I should be redirected to my Account Page
	
Examples:
	| firstname		| lastname	| password	| date	| month	| year	| address		| city		| state		| zip code	| country				| mobile phone		|
	| muhammad		| rozi			| Test1234	| 17		| 8			| 1945	| jl.cinta	| jakarta	| Kansas	| 12543			| United States	| 0809 8888 6969	|


Scenario: Log In with registered account
	Given User open "http://automationpractice.com/" URL on any browser
	And User click sign in nav
	And User enter email and password account
	And User click sign in button
	Then I should be redirected to my Account Page