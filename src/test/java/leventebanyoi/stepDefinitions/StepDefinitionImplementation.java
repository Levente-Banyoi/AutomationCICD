package leventebanyoi.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import leventebanyoi.TestComponents.BaseTest;
import leventebanyoi.pageobjects.CartPage;
import leventebanyoi.pageobjects.CheckoutPage;
import leventebanyoi.pageobjects.ConfiramtionPage;
import leventebanyoi.pageobjects.LandingPage;
import leventebanyoi.pageobjects.ProductCatalogue;

public class StepDefinitionImplementation extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfiramtionPage confiramtionPage;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password)
	{
		productCatalogue = landingPage.loginApplication(username,password);
	}
	
	@When("^I add product (.+) to Cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException
	{
		List<WebElement> products = productCatalogue.getProductsList();
		productCatalogue.addProductToCart(productName);
	}
	
	@When("^checkout (.+) and submit order$")
	public void checkout_submit_order(String productName)
	{
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		confiramtionPage = checkoutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String string)
	{
		String confirmationMessage = confiramtionPage.getConfirmationMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase(string));
		driver.quit();
	}
	
	@Then("^\"([^\"]*)\" message is displayed$")  //this line could have been written like this too: @Then("{string} message is displayed")
	public void something_message_is_displayed(String strArg1) throws Throwable
	{
		Assert.assertEquals(strArg1, landingPage.getErrorMessage());
		driver.quit();
	}

}
