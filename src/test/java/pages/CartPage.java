package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import base.testBase;

public class CartPage extends testBase {
    public String price;
    public int cartsize;
	@FindBy(id="cartur")
	WebElement cart;
	
	@FindBy(id="totalp")
	WebElement totalprice;
	
	@FindBy(xpath="//tbody//td[3]")
	List<WebElement> cartitems;
	
	public CartPage() {
		PageFactory.initElements(driver, this); 
	}
	
	public void cart() {
		extentTest = reports.createTest("Cart Test");
		cart.click();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		price = totalprice.getText();
		cartsize = cartitems.size();
	}
}



