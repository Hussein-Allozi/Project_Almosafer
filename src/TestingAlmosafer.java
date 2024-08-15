import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestingAlmosafer {
	WebDriver driver = new ChromeDriver();

	@BeforeTest
	public void TheFirst() {

		driver.get("https://global.almosafer.com/en");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.cssSelector("button[class='sc-jTzLTM hQpNle cta__button cta__saudi btn btn-primary']"))
				.click();

	}

	@Test
	public void CheckLanguage() {
		String expected = "en";
		String language = driver.findElement(By.tagName("html")).getAttribute("lang");
		Assert.assertEquals(language, expected);
//		System .out.println(language);

	}

	@Test
	public void CheckCurency() {
		String ExpCurency = "SAR";
		String ActualCurrency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"))
				.getText();
		Assert.assertEquals(ActualCurrency, ExpCurency);
//		System .out.println(ActualCurrency);
	}

	@Test
	public void CheckNumber() {
		String ExpNumber = "+966554400000";
		String ActNum = driver.findElement(By.tagName("strong")).getText();
		Assert.assertEquals(ActNum, ExpNumber);
//		System .out.println(ActNum);

	}

	@Test
	public void GitafLogo() {
		boolean expect = true;
		boolean logo = driver.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ekulBa.eYboXF")).isDisplayed();
		Assert.assertEquals(logo, expect);
//		System .out.println(logo);

	}

	@Test
	public void HotelSeleced() {
//      driver.findElement(By.id("uncontrolled-tab-example-tab-hotels")).click();
		String ExpHotel = "false";
		String hotelselected = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels")).getAttribute("aria-selected");
		Assert.assertEquals(hotelselected, ExpHotel);
		System.out.println(hotelselected);

	}

}
