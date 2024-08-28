package leventebanyoi.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import leventebanyoi.AbstractComponents.AbstractComponent;

public class ConfiramtionPage extends AbstractComponent{
	
	WebDriver driver;
	
	public ConfiramtionPage(WebDriver driver) 
	
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement confirmationMessage;
	
	public String getConfirmationMessage()
	{
		return confirmationMessage.getText();
	}

}
