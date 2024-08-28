package leventebanyoi.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import leventebanyoi.TestComponents.BaseTest;
import leventebanyoi.TestComponents.Retry;
import leventebanyoi.pageobjects.CartPage;
import leventebanyoi.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException {

		String productName = "ZARA COAT 3";
		landingPage.loginApplication("someone.else@gmail.com", "P2ssw0rd");
		Assert.assertEquals("Incorrect email  password.", landingPage.getErrorMessage());

	}

	@Test
	public void ProductErrorValidation() throws IOException {

		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("levente.banyoi@gmail.com", "P2ssw0rd");
		List<WebElement> products = productCatalogue.getProductsList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 73");
		Assert.assertFalse(match);
	}

}
