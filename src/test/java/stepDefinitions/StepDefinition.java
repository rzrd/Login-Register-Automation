package stepDefinitions;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObject.AccountPage;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.RegisterPage;
import utilities.Base;
import utilities.ClassHelp;
import utilities.Data;

public class StepDefinition {
	WebDriver driver;
	Base base = new Base();
	Data data = new Data();
	RegisterPage register = new RegisterPage(driver);
	HomePage home = new HomePage(driver);
	LoginPage login = new LoginPage(driver);
	AccountPage account = new AccountPage(driver);

	@Given("Initiate browser")
	public void initiate_browser() throws Throwable {
		data.setIsHeadless(ClassHelp.getEnv("isHeadless"));
		data.setCloseBrowserEveryScenario(ClassHelp.getEnv("closeBrowser"));

		base = new Base();
		driver = base.openBrowser(ClassHelp.getEnv("browser"));
		driver.manage().window().setSize(new Dimension(1366, 768));
		System.out.println("Driver set: " + driver);
		driver.manage().window().maximize();
	}

	@Given("^User open \"([^\"]*)\" URL on any browser$")
	public void user_open_URL_on_any_browser(String url) throws Throwable {
		data.setUrl(url);
		home.goToHomepage();
	}

	@Given("^User click sign in nav$")
	public void user_click_sign_in_nav() throws Throwable {
		home.clickSignIn();
	}

	@Given("^User fill email address in create an account box$")
	public void user_fill_email_address_in_create_an_account_box() throws Throwable {
		login.registerAccount();
	}
	
	@Given("^User click create an account button$")
	public void user_click_create_an_account_button() throws Throwable {
		login.clickCreateAccount();
	}

	@Given("^User choose title$")
	public void user_choose_title() throws Throwable {
		register.clickTitle();
	}

	@Given("^User enter name \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_enter_name_and(String firstName, String lastName) throws Throwable {
		register.enterName(firstName, lastName);
	}

	@Given("^User enter account \"([^\"]*)\" and check email$")
	public void user_enter_account_and_check_email(String pwd) throws Throwable {
		register.enterPassword(pwd);
	}

	@Given("^user enter date of birth with \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
	public void user_enter_date_of_birth_with(String date, String month, String year) throws Throwable {
		register.enterDate(date, month, year);
	}

	@Given("^user checklist for newsletter and special offers$")
	public void user_check_for_newsletter_and_special_offers() throws Throwable {
		register.checklist();
	}

	@Given("^user enter address \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_enter_address_and(String address, String city, String state) throws Throwable {
		register.enterAddress(address, city, state);
	}

	@Given("^user enter address detail \"([^\"]*)\", \"([^\"]*)\", and \"([^\"]*)\"$")
	public void user_enter_address_detail_and(String zip, String country, String mobile) throws Throwable {
		register.enterAddressDetail(zip, country, mobile);
	}

	@When("^User click register button$")
	public void user_click_register_button() throws Throwable {
		register.clickRegister();
	}

	@Then("^I should be redirected to my Account Page$")
	public void i_should_be_redirected_to_my_Account_Page() throws Throwable {
		account.accountPage();
	}
	
	@Given("^User enter email and password account$")
	public void user_enter_email_and_password_account() throws Throwable {
	    login.signInAccount();
	}

	@Given("^User click sign in button$")
	public void user_click_sign_in_button() throws Throwable {
	    login.clickSignIn();
	}
}
