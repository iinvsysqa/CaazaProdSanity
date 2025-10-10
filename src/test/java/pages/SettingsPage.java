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

	@FindBy(xpath = "//*[@resource-id='logoutLabel']")
	private WebElement logoutButton;
	@FindBy(xpath = "//*[@resource-id='ThemedButton_Text_Logout']")
	private WebElement logoutConfirmationButton;
	@FindBy(xpath = "//*[@resource-id='Header_MenuButton']")
	private WebElement devicepage_menuButton;

	@FindBy(xpath = "//*[@resource-id='DevicesHeading']")
	private WebElement devicesTitle;
	
	@FindBy(xpath = "//*[@resource-id='Close_Icon_Svg']")
	private WebElement menu_CloseIcon;
	
	@FindBy(xpath = "//*[@resource-id='MenuItem_Settings_1']")
	private WebElement menu_Settingsbtn;
	@FindBy(xpath = "//*[@resource-id='MenuItem_AddandEditSwitchboard_0']")
	private WebElement Menu_AddandEditSwitchboard;
	@FindBy(xpath = "//*[@resource-id='MenuItem_FirmwareUpdate_2']")
	private WebElement Menu_FirmwareUpdate;
	@FindBy(xpath = "//*[@resource-id='SettingsItem_HighVoltageCuttoff']")
	private WebElement SettingsItem_HighVoltageCuttoff;
	@FindBy(xpath = "//*[@resource-id='SettingsItem_LowVoltageCuttoff']")
	private WebElement SettingsItem_LowVoltageCuttoff;
	@FindBy(xpath = "//*[@resource-id='SettingsItem_TimeZoneConfiguration']")
	private WebElement SettingsItem_TimeZoneConfiguration;
	@FindBy(xpath = "//*[@resource-id='SettingsItem_EnergyMonitor']")
	private WebElement SettingsItem_EnergyMonitor;
	@FindBy(xpath = "//*[@resource-id='SettingsItem_ResetDevice']")
	private WebElement SettingsItem_ResetDevice;
	@FindBy(xpath = "//*[@resource-id='SettingsItem_ModifyRouter']")
	private WebElement SettingsItem_ModifyRouter;
	@FindBy(xpath = "//*[@resource-id='SettingsItem_SubText_ConfiguredRouter']")
	private WebElement SettingsItem_SubText_ConfiguredRouter;
	@FindBy(xpath = "//*[@resource-id='Header_Title']")
	private WebElement Header_Title;
	@FindBy(xpath = "//*[@resource-id='HighVoltage_Toggle']")
	private WebElement HighVoltage_Toggle;
	@FindBy(xpath = "//*[@resource-id='SaveText']")
	private WebElement SaveText;
	@FindBy(xpath = "//*[@resource-id='HighVoltage_Input']")
	private WebElement HighVoltage_Input;
	@FindBy(xpath = "//*[@resource-id='LowVoltage_Toggle']")
	private WebElement LowVoltage_Toggle;
	@FindBy(xpath = "//*[@resource-id='LowVoltage_Input']")
	private WebElement LowVoltage_Input;
	@FindBy(xpath = "//*[@resource-id='CustomModal_ConfirmButton_Text']")
	private WebElement Reset_ConfirmButton_Text;
	@FindBy(xpath = "//*[@resource-id='CustomModal_CancelButton_Text']")
	private WebElement Reset_CancelButton_Text;
	@FindBy(xpath = "//android.widget.EditText[@text=\"Enter Password\"]")
	private WebElement WIFI_EnterPasswordTextbox;
	@FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Cancel\"]")
	private WebElement WIFI_Cancelbtn;
	@FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Submit\"]")
	private WebElement WIFI_Submitbtn;
	@FindBy(xpath = "//android.widget.Toast[@text=\"high voltage threshold value saved\"]")
	private WebElement HighVoltagesavedToast;
	@FindBy(xpath = "//android.widget.Toast[@text=\"Low voltage threshold value saved\"]")
	private WebElement LowVoltagesavedToast;
	@FindBy(xpath = "//android.widget.Toast[@text=\"Your device reset successfully\"]")
	private WebElement DeviceresetToast;
	@FindBy(xpath = "//android.widget.TextView[@text=\"Device couldn't connect with router\"]")
	private WebElement CouldntConnectrouterPopUp;
	@FindBy(xpath = "//*[@resource-id='Single_Button']")
	private WebElement CouldntConnectrouter_OKPopUp;
	
	
	public void clickLogOutButton() {
		clickbyXpath(logoutButton, "Click Log out Button");
	}

	public void clickLogOutConfirmButton() {
		clickbyXpath(logoutConfirmationButton, "Click Log out Button");
	}

	public void openMenuPage() {
		clickbyXpath(devicepage_menuButton, "Menu button");
	}
	public void navigateSettingspage() {
		clickbyXpath(menu_Settingsbtn, "Settings button");
	}
	public void navigateHighVoltCutoff() {
		clickbyXpath(SettingsItem_HighVoltageCuttoff, "HighVoltageBTN");
	}
	public void navigateLowVoltCutoff() {
		clickbyXpath(SettingsItem_LowVoltageCuttoff, "LowVoltageBTN");

	}
	public void clickHighVoltToggle() {
		clickbyXpath(HighVoltage_Toggle, "High Volt Toggle");
	}
	public void clickLowVoltToggle() {
		clickbyXpath(LowVoltage_Toggle, "Low Voltage Toggle");

	}
	public void enterHighVoltagevalue(String input) {
		entervaluebyXpath(HighVoltage_Input, "High Voltage Input textbox",input);
	}
	public void enterLowVoltagevalue(String input) {
		entervaluebyXpath(LowVoltage_Input, "High Voltage Input textbox",input);

	}
	public void verifyHighVoltValues(String input) {
		verifyTextContainsByXpath(HighVoltage_Input, input, "High Volt value");
		driver.navigate().back();
	}
	public void verifyLowVoltValues(String input) {
		verifyTextContainsByXpath(LowVoltage_Input, input, "Low Volt value");
		driver.navigate().back();
		
	}
	
	public void clickSavebutton() {
		clickbyXpath(SaveText, "Save Button");
	}
	public void verifyHighvoltToast() {
		verifyTextContainsByXpath_Toast(HighVoltagesavedToast, "high voltage threshold value saved","High Volt Toast");
	}
	public void verifyLowvoltToast() {
		verifyTextContainsByXpath_Toast(LowVoltagesavedToast, "Low voltage threshold value saved","Low Volt Toast");
	}
	public void navigateback() {
		driver.navigate().back();
	}
	
	public void resetDevice() throws InterruptedException {
		clickbyXpath(SettingsItem_ResetDevice, "ResetDevice");
		clickbyXpath(Reset_ConfirmButton_Text, "ResetDevice_confirmationBTN");
		Thread.sleep(3000);
		verifyTextContainsByXpath(DeviceresetToast, "Your device reset successfully", "Device reset successfully ");
	}
	public void resetDeviceCancelBTN() {
		clickbyXpath(Reset_CancelButton_Text, "ResetDeviceCancelBtn");
	}
	
	
}

