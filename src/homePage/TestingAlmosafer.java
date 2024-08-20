package homePage;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestingAlmosafer extends Parametere {

	@BeforeTest
	public void TheFirst() {

		Setup();
		driver.findElement(By.cssSelector("button[class='sc-jTzLTM hQpNle cta__button cta__saudi btn btn-primary']"))
				.click();

	}

	@Test(priority = 1)
	public void CheckLanguage() {
		String language = driver.findElement(By.tagName("html")).getAttribute("lang");
		Assert.assertEquals(language, expected);
//		System .out.println(language);

	}

	@Test(priority = 2)
	public void CheckCurency() {
		String ActualCurrency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"))
				.getText();
		Assert.assertEquals(ActualCurrency, ExpCurency);
//		System .out.println(ActualCurrency);
	}

	@Test(priority = 3)
	public void CheckNumber() {
		String ActNum = driver.findElement(By.tagName("strong")).getText();
		Assert.assertEquals(ActNum, ExpNumber);
//		System .out.println(ActNum);

	}

	@Test(priority = 4)
	public void GitafLogo() {
		boolean logo = driver.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ekulBa.eYboXF")).isDisplayed();
		Assert.assertEquals(logo, expectLogo);
//		System .out.println(logo);

	}

	@Test(priority = 5)
	public void HotelSeleced() {

		String hotelselected = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"))
				.getAttribute("aria-selected");
		Assert.assertEquals(hotelselected, ExpHotelSelected);

	}

	@Test(priority = 6)
	public void FlightStrctrueAndReturn() {

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

		RandomSelectTheLanguage();

	}

	@Test(priority = 8)

	public void FillHotelTab() {

		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));

		HotelTab.click();
		WebElement SearchHotelInputField = driver.findElement(By.xpath("//input[@data-testid='AutoCompleteInput']"));

		String WebsiteURL = driver.getCurrentUrl();

		if (WebsiteURL.contains("ar")) {

			SearchHotelInputField.sendKeys(ArabicCities[randomArabicCity]);
		} else {
			SearchHotelInputField.sendKeys(EnglishCities[randomEnglishCity]);

		}

		WebElement ListOfLocations = driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List"));

		WebElement firstResult = ListOfLocations.findElements(By.tagName("li")).get(1);
		firstResult.click();

	}

	@Test(priority = 9)

	public void RandomlySelectTheNumberOfVistor() {

		WebElement SelectorofTheVistor = driver
				.findElement(By.xpath("//select[@data-testid='HotelSearchBox__ReservationSelect_Select']"));

		Select select = new Select(SelectorofTheVistor);
		int randomIndex = rand.nextInt(2);
		select.selectByIndex(randomIndex);
		WebElement SearchHotelButton = driver
				.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
		SearchHotelButton.click();

	}

	@Test(priority = 10)

	public void CheckThePageFullyLoaded() throws InterruptedException {

		Thread.sleep(10000);
		String results = driver.findElement(By.xpath("//span[@data-testid='HotelSearchResult__resultsFoundCount']"))
				.getText();

		boolean finished = results.contains("وجدنا") || results.contains("found");

		Assert.assertEquals(finished, expectedPageLoadedResult);

	}

	@Test(priority = 11)
	public void SortLowestPrice() {
		WebElement ButtonLowestPrice = driver
				.findElement(By.xpath("//button[@data-testid='HotelSearchResult__sort__LOWEST_PRICE']"));
		ButtonLowestPrice.click();
		List<WebElement> Price = driver.findElements(By.className("Price__Value"));
		String lowest = Price.get(0).getText();
		int Lowest = Integer.parseInt(lowest);
		String highest = Price.get(Price.size() - 1).getText();
		int Highest = Integer.parseInt(highest);
		boolean act = Lowest < Highest;
		Assert.assertEquals(act, expLowestPrice);

	}
}