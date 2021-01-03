package pageObject;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import utilities.Base;
import utilities.Data;

public class LoginPage extends Base {
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	Data data = new Data();
	By emailCreateField = By.xpath("//input[@id='email_create']");
	By createButton	= By.xpath("//span[contains(.,'Create an account')]");
	By emailField = By.xpath("//input[@id='email']");
	By passwordField = By.xpath("//input[@id='passwd']");
	By signInButton	= By.xpath("//span[contains(.,'Sign in')]");

	public void registerAccount() throws InterruptedException {
		data.setEmail(getRandString() + "@email.com");
		String email = data.getEmail();
		driver.findElement(emailCreateField).sendKeys(email);
	}
	
	protected String getRandString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
	
	public void clickCreateAccount() throws InterruptedException {
		click(createButton);
	}
	
	public void signInAccount() throws InterruptedException {
		String email = data.getEmail();
		String pwd = data.getPassword();
				
		inputField(emailField, email);
		inputField(passwordField, pwd);
	}
	
	public void clickSignIn() throws InterruptedException {
		click(signInButton);
	}

}
