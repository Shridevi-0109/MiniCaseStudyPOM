package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.testBase;

public class PurchasePage extends testBase{
	
	WebDriverWait wait;
	
	@FindBy(xpath = "//button[contains(text(),'Place Order' )]")
	WebElement orderBtn;
	
	@FindBy(xpath = "//input[@id='name']")
	WebElement name;
	
	@FindBy(xpath = "//input[@id='country']")
	WebElement country;
	
	@FindBy(xpath = "//input[@id='city']")
	WebElement city;
	
	@FindBy(xpath = "//input[@id='card']")
	WebElement card;
	
	@FindBy(xpath = "//input[@id='month']")
	WebElement month;
	
	@FindBy(xpath = "//input[@id='year']")
	WebElement year;
	
	@FindBy(xpath = "//button[contains(text(),'Purchase')]")
	WebElement purchaseBtn;
	
	@FindBy(xpath="//h2[(text()='Thank you for your purchase!')]")
	WebElement confirm;
	
	@FindBy(xpath="//button[contains(text(),'OK')]")
	WebElement ok;
	
	@FindBy(xpath = "//tbody")
	List<WebElement> items;
	
	public PurchasePage() {
		PageFactory.initElements(driver, this);
	}
	
	public void placeOrder() throws InterruptedException {
		extentTest = reports.createTest("Purchase Item Test");
		wait=new WebDriverWait(driver,Duration.ofSeconds(30));
		if(items.size()!=0) {
			wait.until(ExpectedConditions.elementToBeClickable(orderBtn));
			orderBtn.click();
			name.sendKeys(prop.getProperty("name"));
			country.sendKeys(prop.getProperty("country"));
			city.sendKeys(prop.getProperty("city"));
			card.sendKeys(prop.getProperty("cardNo"));
			month.sendKeys(prop.getProperty("month"));
			year.sendKeys(prop.getProperty("year"));
			wait.until(ExpectedConditions.elementToBeClickable(purchaseBtn));
			purchaseBtn.click();
			boolean isDisp = confirm.isDisplayed();
	    	String Disp = confirm.getText();
	    	Assert.assertTrue(isDisp);
	    	System.out.println(Disp);
	    	Thread.sleep(1000);
	    	wait.until(ExpectedConditions.elementToBeClickable(ok));
	    	ok.click();
		}	

	}
}
