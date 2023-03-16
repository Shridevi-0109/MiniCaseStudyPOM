package pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.testBase;

public class HomePage extends testBase{
	Alert alert;
	WebDriverWait wait;
	
	@FindBy(xpath="(//ul/li/a)[1]")
	public WebElement home;
	
	@FindBy(xpath="//div/a[@class='btn btn-success btn-lg']")
	public WebElement addtocartbtn; 
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
			
	public void selectproduct(String category,String product) throws InterruptedException {
		extentTest = reports.createTest("Add Item Test");
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement typcat = driver.findElement(By.partialLinkText(category));
		typcat.click();
		WebElement typprod =driver.findElement(By.partialLinkText(product));
		typprod.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		addtocartbtn.click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.alertIsPresent());
		alert = driver.switchTo().alert();
		alert.accept();
		home.click();
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	}

	
}