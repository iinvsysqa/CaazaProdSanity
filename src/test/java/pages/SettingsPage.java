package pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import wrappers.GenericWrappers;

public class SettingsPage extends GenericWrappers{

	private AndroidDriver driver;
	
	public SettingsPage(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait=new WebDriverWait(driver, 10);
	}

	
	// Locate all elements on the page

	@FindBy(xpath = "//*[@resource-id='menu_icon_deviceSettings']")
	private WebElement deviceSettingsButton;

	@FindBy(xpath = "//*[@resource-id='DeviceSetting_resetDevice']")
	private WebElement resetDeviceButtom;
	
	@FindBy(xpath = "//*[@resource-id='logoutLabel']")
	private WebElement logoutButton;
	
	@FindBy(xpath = "//*[@resource-id='ThemedButton_Logout']")
	private WebElement logoutConfirmationButton;
	
	
	public void clickLogOutButton() {
		clickbyXpath(logoutButton, "Click Log out Button");
	}

	public void clickLogOutConfirmButton() {
		clickbyXpath(logoutConfirmationButton, "Click Log out Button");
	}

	
	
}

