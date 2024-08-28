package leventebanyoi.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import leventebanyoi.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) 
	
		{
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
	
	
	@FindBy(css=".actions a")
	private WebElement submit;
	
	@FindBy(css="[placeholder='Select Country']")
	private WebElement country;

	@FindBy(css=".ta-item:nth-of-type(2)")
	private WebElement selectCountry;
	
	private By results = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName)
	{
		Actions a =new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(By.cssSelector(".ta-results"));
		selectCountry.click();
	}
	
	public ConfiramtionPage submitOrder()
	{
		submit.click();
		return new ConfiramtionPage(driver);
	}

}
