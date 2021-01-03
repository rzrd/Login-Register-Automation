package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utilities.Base;
import utilities.Data;

public class HomePage extends Base {
	
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	Data data = new Data();
	By signInButton	= By.xpath("//a[@class='login'][contains(.,'Sign in')]");
	
	public void goToHomepage() throws InterruptedException {
		String url = data.getUrl();
		driver.get(url);
		String expectedUrl = url + "index.php";
		waitPageLoadUrl(expectedUrl);
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}
	
	public void clickSignIn() throws InterruptedException {
		click(signInButton);
	}

}
