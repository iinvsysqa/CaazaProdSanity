package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import utils.Reporter;
import wrappers.GenericWrappers;

public class SwitchPage extends GenericWrappers{

	private AndroidDriver driver;
	
	public SwitchPage(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait=new WebDriverWait(driver, 10);
	}

	
	// Locate all elements on the page

	@FindBy(xpath = "//*[@resource-id='Header_MenuButton']")
	private WebElement menuButton;

	@FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"com.CaaZa_Smart:id/SwitchToggle_1\"]/android.view.ViewGroup")
	private WebElement onOffButton;
	
	@FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"com.CaaZa_Smart:id/SwitchToggle_2\"]/android.view.ViewGroup")
	private WebElement onOffButton2;
	
	@FindBy(xpath = "MenuItem_AddandEditSwitchboard_0")
	private WebElement addEditSwitchBoardButton;
	
	
	@FindBy(xpath = "//*[@resource-id='MenuItem_Settings_1']")
	private WebElement settingsButton;
	
	@FindBy(xpath = "//*[@resource-id='SettingsItem_ResetDevice']")
	private WebElement resetDeviceButton;
	
	@FindBy(xpath = "//*[@resource-id='CustomModal_ConfirmButton']")
	private WebElement resetConfirmationButton;
	
	@FindBy(xpath = "//*[@resource-id='Header_Back_Button']")
	private WebElement headerbackbutton;
	
	
	
	
	public void clickMenuButton() {
		clickbyXpath(menuButton, "Click on Menu Button");
	}

	public void clickOnOffButton() {
		clickbyXpath(onOffButton, "Click On/OFF 1 Button");
	}
	
	public void clickOnOff2Button() {
		clickbyXpath(onOffButton2, "Click On/OFF 2 Button");
	}

	public void clickAddEditSwitchBoardButton() {
		clickbyXpath(addEditSwitchBoardButton, "Click On Add Edit Switchboard Button");
	}

	
	public void clickSettingsButton() {
		clickbyXpath(settingsButton, "Click On settings Button");
	}
	
	public void clickResetDeviceButton() {
		clickbyXpath(resetDeviceButton, "Click On Reset Device Button");
	}
	
	public void clickResetConfirmationButton() {
		clickbyXpath(resetConfirmationButton, "Click On Reset Confirmation Button");
	}
	
	public void clickBackButton() {
		try {
			expshortWait(headerbackbutton);
			headerbackbutton.click();
		}catch (Exception e) {
			Reporter.reportStep(" Back button is not clicked"+e, "FAIL");
		}
		//clickbyXpath(headerbackbutton, "Click On back Button");
	}
	
	
	
}

