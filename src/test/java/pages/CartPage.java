package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.testBase;

public class CartPage extends testBase {
    
	@FindBy(id="cartur")
	WebElement cart;
	
	@FindBy(xpath="(//a[contains(text(),'Delete')])[3]")
	WebElement delBtn;
	
	public CartPage() {
		PageFactory.initElements(driver, this); 
	}
	
	public void delete(){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.elementToBeClickable(cart));
		cart.click(); 
		List<WebElement> BeforeDel = driver.findElements(By.xpath("//td[2]"));
		wait.until(ExpectedConditions.visibilityOf(delBtn));
		delBtn.click();
		List<WebElement> AfterDel = driver.findElements(By.xpath("//td[2]"));
		wait.until(ExpectedConditions.visibilityOfAllElements(AfterDel));
		if(BeforeDel.size()>AfterDel.size()) {
			System.out.println("1 item deleted");
			Assert.assertTrue(true);
		}
    }
}



