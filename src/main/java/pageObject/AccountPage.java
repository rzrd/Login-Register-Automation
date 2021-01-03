package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utilities.Base;
import utilities.Data;

public class AccountPage extends Base {
	
	public AccountPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	Data data = new Data();
	By emailField = By.xpath("//input[@id='email_create']");
	By createButton	= By.xpath("//span[contains(.,'Create an account')]");
	
	public void accountPage() throws InterruptedException {
		String expectedUrl = data.getUrl() + "index.php?controller=my-account";
		waitPageLoadUrl(expectedUrl);
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
		
		String name = data.getFirstname() + " " + data.getLastname();
		boolean isAccountCorrect = driver.findElement(By.xpath("//span[contains(.,'"+ name +"')]")).isDisplayed();
		Assert.assertTrue(isAccountCorrect);		
	}
}
