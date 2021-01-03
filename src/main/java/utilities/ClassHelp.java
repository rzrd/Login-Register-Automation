package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ClassHelp {

	public static String getEnv(String text) {
		try {
			String filename = "conf.properties";
			InputStream input = new FileInputStream(System.getProperty("user.dir") + File.separator + filename);
			Properties prop = new Properties();
			prop.load(input);
			String env = prop.getProperty(text);
			if (env != null)
				return env;
			else
				throw new RuntimeException(text + " is not specified in the conf.properties file.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return text;
	}

	public static String randomNum(int min, int max) throws InterruptedException {
		return String.valueOf(ThreadLocalRandom.current().nextInt(min, max + 1));
	}

	public static String getStringDate() throws InterruptedException {
		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		calendar.add(Calendar.DAY_OF_YEAR, -1);

		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		String todayAsString = dateFormat.format(today);
		return todayAsString;
	}

	public void moveHighLight(WebDriver driver, WebElement element) {
		((JavascriptExecutor) driver)
				.executeScript("arguments[0].style.border='3px solid" + getEnv("highlightColor") + "'", element);
		highlight(driver, element);
	}

	public void highlight(WebDriver driver, WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
}
