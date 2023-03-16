package pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.testBase;

public class LoginPage extends testBase {
	WebDriverWait wait;
	
	@FindBy(id = "login2")
	WebElement loginbtn;
		
	@FindBy(id="loginusername")
	WebElement name;
	
	@FindBy(id="loginpassword")
	WebElement password;
	
	@FindBy(xpath="(//button[@class='btn btn-primary'])[3]")
	WebElement submitloginbtn;
	
	@FindBy(xpath = "//a[contains(text(),'Welcome ShriAthi')]")
	public WebElement successMsg;
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void login() throws InterruptedException {
		extentTest = reports.createTest("Login Test");
		loginbtn.click();
		name.sendKeys(prop.getProperty("username"));
		wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		password.sendKeys(prop.getProperty("password"));
		wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		submitloginbtn.click();
			
	}
	
}