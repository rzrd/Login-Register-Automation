package pageObject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import utilities.Base;
import utilities.Data;

public class RegisterPage extends Base {
	
	public RegisterPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	Data data = new Data();
	By mrTitleButton = By.xpath("//input[@id='id_gender1']");
	By firstNameField = By.xpath("//input[@id='customer_firstname']");
	By lastnameField = By.xpath("//input[@id='customer_lastname']");
	By emailField = By.xpath("//input[@id='email']");
	By passwordField = By.xpath("//input[@id='passwd']");
	By yearField = By.xpath("//select[@id='years']");
	By monthField = By.xpath("//select[@id='months']");
	By dayField = By.xpath("//select[@id='days']");
	By newsletterButton = By.xpath("//*[@id=\"uniform-newsletter\"]");
	By offersButton = By.xpath("//*[@id=\"uniform-optin\"]");
	By addressField = By.xpath("//input[@id='address1']");
	By cityField = By.xpath("//input[@id='city']");
	By stateField = By.xpath("//select[@id='id_state']");
	By zipcodeField = By.xpath("//input[@id='postcode']");
	By countryField = By.xpath("//select[@id='id_country']");
	By phoneField = By.xpath("//input[@id='phone_mobile']");
	By registerButton = By.xpath("//span[contains(.,'Register')]");
	
	public void clickTitle() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		click(mrTitleButton);
	}
	
	public void enterName(String firstname, String lastname) throws InterruptedException {
		data.setFirstname(firstname);
		data.setLastname(lastname);
		inputField(firstNameField, firstname);
		inputField(lastnameField, lastname);
	}
	
	public void enterPassword(String pwd) throws InterruptedException {
		String actualEmail = readValue(emailField);
		String expctedEmail = data.getEmail();
		Assert.assertEquals(actualEmail, expctedEmail);
		
		data.setPassword(pwd);
		String getPwd = data.getPassword();
		driver.findElement(passwordField).sendKeys(getPwd);
		System.out.println(getPwd);
	}
	
	public void enterDate(String date, String month, String year) throws InterruptedException {
		Select selectDay = new Select(driver.findElement(dayField));
		selectDay.selectByValue(date);
		
		Select selectMonth = new Select(driver.findElement(monthField));
		selectMonth.selectByValue(month);
		
		Select selectYear = new Select(driver.findElement(yearField));
		selectYear.selectByValue(year);
	}
	
	public void checklist() throws InterruptedException {		
		driver.findElement(newsletterButton).click();
		driver.findElement(offersButton).click();		
	}
	
	public void enterAddress(String address, String city, String state) throws InterruptedException {
		inputField(addressField, address);
		inputField(cityField, city);
		
		Select selectState = new Select(driver.findElement(stateField));
		selectState.selectByVisibleText(state);
	}
	
	public void enterAddressDetail(String zip, String country, String mobile) throws InterruptedException {
		inputField(zipcodeField, zip);
		
		Select selectCountry = new Select(driver.findElement(countryField));
		selectCountry.selectByVisibleText(country);
		
		inputField(phoneField, mobile);
	}
	
	public void clickRegister() throws InterruptedException {
		click(registerButton);
		reportLog("account created with email: " + data.getEmail() + "\n" + "and password: " + data.getPassword());
	}

}
