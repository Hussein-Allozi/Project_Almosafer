package homePage;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Parametere {
	WebDriver driver = new ChromeDriver();
	Random rand = new Random();
	String WebSite ="https://global.almosafer.com/en";
	String expected = "en";
	String ExpCurency = "SAR";
	String ExpNumber = "+966554400000";
	boolean expectLogo = true;
	String ExpHotelSelected = "false";
	LocalDate date = LocalDate.now();
	int Tomorow = date.plusDays(1).getDayOfMonth();
	int AfterTomorow = date.plusDays(2).getDayOfMonth();
	String[] lang = { "https://global.almosafer.com/en", "https://global.almosafer.com/ar" };
	int RandomLanguage = rand.nextInt(lang.length);
	String[] EnglishCities = { "Dubbai", "Jeddah", "riyadh" };
	int randomEnglishCity = rand.nextInt(EnglishCities.length);
	String[] ArabicCities = { "دبي", "جدة" };
	int randomArabicCity = rand.nextInt(ArabicCities.length);
	boolean expectedPageLoadedResult = true;
	boolean expLowestPrice = true;
	
	public void Setup() {
		driver.get(WebSite);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	public void RandomSelectTheLanguage() {
		driver.get(lang[RandomLanguage]);
		String Url = driver.findElement(By.tagName("link")).getAttribute("href");
		Assert.assertEquals(lang[RandomLanguage], Url);
	}
	
}
