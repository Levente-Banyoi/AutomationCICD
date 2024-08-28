package leventebanyoi.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import leventebanyoi.TestComponents.BaseTest;
import leventebanyoi.pageobjects.CartPage;
import leventebanyoi.pageobjects.CheckoutPage;
import leventebanyoi.pageobjects.ConfiramtionPage;
import leventebanyoi.pageobjects.OrderPage;
import leventebanyoi.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException {

		
		//LandingPage landingPage = launchApplication(); //not needed, there is an Before meth defined in the BaseTest class
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//LandingPage landingpage = new LandingPage(driver); 
		//landingpage.goTo();
		//this steps are not needed anymore because they were defined in the BaseTest class
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductsList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfiramtionPage confiramtionPage = checkoutPage.submitOrder();
		String confirmationMessage = confiramtionPage.getConfirmationMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dependsOnMethods= {"submitOrder"})  //this test will only run after submitOrder runs successfully
	public void OrderHistoryTest()
	{
		//TO verify ZARA COAT 3 is displaying in orders page (dependent on the test from above)
		ProductCatalogue productCatalogue = landingPage.loginApplication("levente.banyoi@gmail.com", "P2ssw0rd");
		OrderPage ordersPage =productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}
	
	
	
	//Extent Reports - 
	
	@DataProvider
	public Object[][] getData() throws IOException
	{

		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\leventebanyoi\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
		
	}
	
//	HashMap<String,String> map = new HashMap<String,String>();
//	map.put("email", "levente.banyoi@gmail.com");
//	map.put("password", "P2ssw0rd");
//	map.put("productName", "ZARA COAT 3");
//	
//	HashMap<String,String> map1 = new HashMap<String,String>();
//	map1.put("email", "levente.banyoi@gmail.com");
//	map1.put("password", "P2ssw0rd");
//	map1.put("productName", "ADIDAS ORIGINAL");
	
	//instead of the lines from above, we will use a json file to get the Data from	and it will look like above(code which is not commented)
	
	
	
	
/*	@DataProvider
	public Object[][] getData()
	{
		return new Object[][] {{"levente.banyoi@gmail.com","P2ssword","ZARA COAT 3"}, {"levente.banyoi@gmail.com","P2ssword","ADIDAS ORIGINAL"}};
	}  */  // instead of this, the HashMap method was used

}
