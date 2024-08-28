package leventebanyoi.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import leventebanyoi.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{
	
	WebDriver driver;
	
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> productNames;
	

	public OrderPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Boolean VerifyOrderDisplay(String productName) 
	{
		Boolean match = productNames.stream().anyMatch(cartProduct->cartProduct.getText().equals(productName));
		return match;
	}
	
	
}
	
	