package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class VerifyHtmlLinksOnThePage {
	public WebDriver driver;
	public String url = "";
	public String verificationError = "";

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\WindowsDrive\\Selenium_Jar\\Driver\\Runner\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		url = "http://demo.automationtesting.in/";
	}

	// How to get the total number of links
	@Test
	public void totalNumberOfHtmlLinks() {
		driver.get(url);

		driver.findElement(By.id("email")).sendKeys("saikiran@gmail.com");

		driver.findElement(By.id("enterimg")).click();

		List<WebElement> allLinks = driver.findElements(By.tagName("a"));

		System.out.println("total count of link - " + allLinks.size());
	}

	// Print all html link
	@Test
	public void printAllHtmlLinks() {
		driver.get(url);

		driver.findElement(By.id("email")).sendKeys("saikiran@gmail.com");

		driver.findElement(By.id("enterimg")).click();

		// get the link
		List<WebElement> allLinks = driver.findElements(By.tagName("a"));

		System.out.println("total count of link - " + allLinks.size());
		// for each to print the value
		for (WebElement link : allLinks) {
			System.out.println(link.getText());
		}
	}

	// Get html attributes
	@Test
	public void testGetAttributes() {
		driver.get(url);

		driver.findElement(By.id("email")).sendKeys("saikiran@gmail.com");
		driver.findElement(By.id("enterimg")).click();

		// get the attribute
		WebElement faceBookLink = driver.findElement(By.xpath("//a[@class='link facebook']"));

		// get the href attribute value
		System.out.println("Attribute Value : " + faceBookLink.getAttribute("href"));

		// get the tag name value
		System.out.println("Tag name in which element wrapped : " + faceBookLink.getTagName());
	}

	// Print all the html links from the page who has href as attribute
	@Test
	public void getAllHtmlLinksAttributes() {
		driver.get(url);

		driver.findElement(By.id("email")).sendKeys("saikiran@gmail.com");

		driver.findElement(By.id("enterimg")).click();

		// get all the web elements
		List<WebElement> allLinks = driver.findElements(By.tagName("a"));
		System.out.println("total count of link - " + allLinks.size());
		// Print the value who has href
		for (int i = 0; i <= allLinks.size() - 1; i++) {
			if ((allLinks.get(i)).getAttribute("href") != null) {
				System.out.println((allLinks.get(i)).getAttribute("href"));
			}
		}
	}

	@Test
	public void navigateToPerticularHtmlLinks() {
		driver.get(url);

		driver.findElement(By.id("email")).sendKeys("saikiran@gmail.com");

		driver.findElement(By.id("enterimg")).click();

		// Get all the tag element
		List<WebElement> allLinks = driver.findElements(By.tagName("a"));
		// Create arraylist
		ArrayList<String> hrefs = new ArrayList<String>();

		// Get the list size
		System.out.println("total count of link - " + allLinks.size());

		// Identify href who doesn't have null value
		for (int i = 0; i < allLinks.size(); i++) {
			String hrefVal = (allLinks.get(i)).getAttribute("href");
			if (hrefVal != null) {
				System.out.println(hrefVal);
				hrefs.add(hrefVal);
			}
		}

		// Navigate to perticuler link
		for (String href : hrefs) {
			if (href.contains("http://demo.automationtesting.in/Alerts.html")) {
				driver.navigate().to(href);
				break;
			}
		}
	}

	@Test
	public void comparetheLinkWithString() {
		driver.get(url);

		driver.findElement(By.id("email")).sendKeys("saikiran@gmail.com");

		driver.findElement(By.id("enterimg")).click();

		List<WebElement> allLinks = driver.findElements(By.tagName("a"));

		for (int i = 0; i < allLinks.size(); i++) {
			if (allLinks.get(i).getText().equals("Widgets") || allLinks.get(i).getText().equals("Video")) {
				System.out.println(allLinks.get(i).getText());
				break;
			} else {
				System.out.println(allLinks.get(i).getText());
			}
		}
	}

	@After
	public void tierDown() {
		driver.quit();
		if (!verificationError.equals(""))
			Assert.fail(verificationError);
	}
}
