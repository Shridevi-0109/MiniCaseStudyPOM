package testscripts;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import base.testBase;
import commonUtils.Utility;
import pages.CartPage;
import pages.LoginPage;
import pages.PurchasePage;
import pages.HomePage;

public class PurchaseOrderTest extends testBase 
{

	WebDriverWait wait;
	LoginPage logIn;
	HomePage item;
	CartPage cart;
	PurchasePage purchase;
	
	  @BeforeTest
	  public void setup() throws IOException {
		initialize();
	  }

	  @Test(priority=1)
	  public void LoginPageTest() throws InterruptedException{
		  logIn=new LoginPage();
		  logIn.login();
		  Assert.assertEquals(logIn.successMsg.getText(), "Welcome ShriAthi");;
	  }
	
	  @Test(priority=2,dataProvider = "Multipledata")
	  public void AddItemTest(String categ,String produ) throws InterruptedException {
		  Thread.sleep(3000);
		  item = new HomePage();
		  item.selectproduct(categ,produ);
		  wait = new WebDriverWait(driver,Duration.ofSeconds(20));

	  }
	
	  @DataProvider(name="Multipledata")
      public Object[][] getdata() throws CsvValidationException, IOException{
  	  String path = System.getProperty("user.dir")+"\\src\\test\\resources\\testData\\demoBlazePOM.csv";
  	  CSVReader reader = new CSVReader(new FileReader(path));
  	  String[] col;
  	  ArrayList<Object> datalist = new ArrayList<Object>();
  	  while((col = reader.readNext())!=null) {
  		  Object[] record = {col[0],col[1]};
  		  datalist.add(record);
  	  }
  	  	return datalist.toArray(new Object[datalist.size()][]);  	  
 }
	
	 @Test(priority=3)
     public void DeleteItemTest() throws InterruptedException {
			cart = new CartPage();
			cart.delete();			
	  }
  
     @Test(priority=4)
     public void PlaceOrderTest() throws InterruptedException {
	  Thread.sleep(3000);
	  purchase = new PurchasePage();
	  purchase.placeOrder();	  
      }  
 
     @AfterMethod()
	 public void tearDown(ITestResult result) {
		 if(ITestResult.FAILURE == result.getStatus()) {
			 extentTest.log(Status.FAIL, result.getThrowable().getMessage());
			 String strPath = Utility.getScreenshotPath(driver);
			 extentTest.addScreenCaptureFromPath(strPath);
		 }
	 }
     
     @AfterTest
     public void closeApp() {
    	 reports.flush();
     }
}

