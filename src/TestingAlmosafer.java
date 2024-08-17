import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestingAlmosafer {
	WebDriver driver = new ChromeDriver();
	Random rand = new Random();

	@BeforeTest
	public void TheFirst() {

		driver.get("https://global.almosafer.com/en");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.cssSelector("button[class='sc-jTzLTM hQpNle cta__button cta__saudi btn btn-primary']"))
				.click();

	}

	@Test(priority = 1)
	public void CheckLanguage() {
		String expected = "en";
		String language = driver.findElement(By.tagName("html")).getAttribute("lang");
		Assert.assertEquals(language, expected);
//		System .out.println(language);

	}

	@Test(priority = 2)
	public void CheckCurency() {
		String ExpCurency = "SAR";
		String ActualCurrency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"))
				.getText();
		Assert.assertEquals(ActualCurrency, ExpCurency);
//		System .out.println(ActualCurrency);
	}

	@Test(priority = 3)
	public void CheckNumber() {
		String ExpNumber = "+966554400000";
		String ActNum = driver.findElement(By.tagName("strong")).getText();
		Assert.assertEquals(ActNum, ExpNumber);
//		System .out.println(ActNum);

	}

	@Test(priority = 4)
	public void GitafLogo() {
		boolean expect = true;
		boolean logo = driver.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ekulBa.eYboXF")).isDisplayed();
		Assert.assertEquals(logo, expect);
//		System .out.println(logo);

	}

	@Test(priority = 5)
	public void HotelSeleced() {
//      driver.findElement(By.id("uncontrolled-tab-example-tab-hotels")).click();
		String ExpHotel = "false";
		String hotelselected = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"))
				.getAttribute("aria-selected");
		Assert.assertEquals(hotelselected, ExpHotel);
//		System.out.println(hotelselected);

	}

	@Test(priority = 6)
	public void FlightStrctrueAndReturn() {
		LocalDate date = LocalDate.now();
		int Tomorow = date.plusDays(1).getDayOfMonth();
		int AfterTomorow = date.plusDays(2).getDayOfMonth();
		List<WebElement> depatureAndArrivalDates = driver.findElements(By.className("LiroG"));
		String departure = depatureAndArrivalDates.get(0).getText();
		String arival = depatureAndArrivalDates.get(1).getText();
		int dep = Integer.parseInt(departure);
		int ari = Integer.parseInt(arival);
		Assert.assertEquals(Tomorow, dep);
		Assert.assertEquals(AfterTomorow, ari);

	}

	@Test(priority = 7)
	public void RandomLanguageCheck() {
		String[] lang = { "https://global.almosafer.com/en", "https://global.almosafer.com/ar" };
		int RandomLanguage = rand.nextInt(lang.length);
		driver.get(lang[RandomLanguage]);
		String Url = driver.findElement(By.tagName("link")).getAttribute("href");
		Assert.assertEquals(lang[RandomLanguage], Url);
//		System.out.println(Url);
	}

	@Test(priority = 8)
	public void HotelSearchTab() {
		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		HotelTab.click();
		String language = driver.findElement(By.tagName("html")).getAttribute("lang");
		WebElement SearchInput = driver.findElement(By.xpath("//input[@data-testid='AutoCompleteInput']"));
		if (language.equals("en")) {
			SearchInput.sendKeys("Dubai , jeddah , Riyadh");
		} else {
			SearchInput.sendKeys(" دبي , جدة ");
		}
		List<WebElement> FirstResult = driver.findElements(By.xpath("//ul[@data-testid='AutoCompleteResultsList']"));
		FirstResult.get(0).click();
		WebElement ButtonSearch = driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
		ButtonSearch.click();
	}

	@Test(priority = 9)
	public void RandomSeletRoom() {
		WebElement ButtonSelect = driver.findElement(By.xpath("//select[@data-testid='HotelSearchBox__ReservationSelect_Select']"));
		ButtonSelect.click();
		List<WebElement> SelectList = driver.findElements(By.xpath("//option[@data-testid='HotelSearchBox__ReservationSelect_A']"));
		int RandomSelect = rand.nextInt(1);
		SelectList.get(RandomSelect);
//		System.out.println(SelectList.get(RandomSelect).getText());
	}

	@Test(priority = 10)
	public void ButtonSearch() {
		WebElement ButtonSearch = driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
		ButtonSearch.click();
	}
}
