package utilities;

import java.io.File;
import java.net.MalformedURLException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import com.cucumber.listener.Reporter;

public class Base {

	public static WebDriver driver;
	Data data = new Data();

	static String chromeDriverPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "driver"
			+ File.separator + ClassHelp.getEnv("googleChrome");
	static String mozillaDriverPath = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "driver" + File.separator + ClassHelp.getEnv("mozillaFirefox");

	public WebDriver openBrowser(String browser) throws MalformedURLException {
		if ("chrome".equals(browser)) {
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);

			ChromeOptions chromeOptions = new ChromeOptions();
			if (data.getIsHeadless().equalsIgnoreCase("yes")) {
				chromeOptions.addArguments("--headless");
			}

			chromeOptions.addArguments("--windows-size=1366, 768");
			chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			driver = new ChromeDriver(chromeOptions);
		} else if ("firefox".equals(browser)) {
			System.setProperty("webdriver.gecko.driver", mozillaDriverPath);

			FirefoxOptions firefoxOptions = new FirefoxOptions();
			if (data.getIsHeadless().equalsIgnoreCase("yes")) {
				firefoxOptions.addArguments("--headless");
			}

			firefoxOptions.addArguments("--windows-size=1366, 768");
			firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			driver = new FirefoxDriver(firefoxOptions);
		} else {
			System.out.println("Selected browser not supported - " + browser);
		}
		System.out.println("Opening Browser: " + browser);
		return driver;
	}

	public void waitVisibility(By by) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(60))
				.pollingEvery(Duration.ofMillis(500));
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void waitInisibility(By by) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(60))
				.pollingEvery(Duration.ofMillis(500));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}

	public void waitClickable(By by) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(60))
				.pollingEvery(Duration.ofMillis(500));
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}

	public void waitPageLoadUrl(String url) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(60))
				.pollingEvery(Duration.ofMillis(500));
		wait.until(ExpectedConditions.urlToBe(url));
	}

	public void reportLog(String message) {
		System.out.println(message);
		Reporter.addStepLog(message);
	}

	public void click(By elementLocation) {
		waitClickable(elementLocation);
		driver.findElement(elementLocation).click();
	}

	public void writeText(By elementLocation, String text) {
		waitVisibility(elementLocation);
		driver.findElement(elementLocation).sendKeys(text);
	}

	public void inputField(By element, String val) throws InterruptedException {
		WebElement findElement = driver.findElement(element);
		String actualText = "";
		int attempts = 0;
		while (attempts < 2) {
			try {
				findElement.sendKeys(val);
				actualText = findElement.getAttribute("value");
				if (actualText.contentEquals(val)) {
					break;
				}
				if (!actualText.contentEquals(val)) {
					throw new Exception();
				}
			} catch (Exception e) {
				System.out.println("Wrong input, try to re-input.");
				findElement.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			}
			attempts++;
		}
		actualText = findElement.getAttribute("value");
		Assert.assertEquals(actualText, val, "wrong input");
	}

	public String readValue(By elementLocation) {
		waitVisibility(elementLocation);
		return driver.findElement(elementLocation).getAttribute("value");
	}

	public String readText(By elementLocation) {
		waitVisibility(elementLocation);
		return driver.findElement(elementLocation).getText();
	}
}
