package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

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
	@FindBy(xpath = "//*[@resource-id='Single_Button']")
	private WebElement okButton;

	@FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"com.CaaZa_Smart:id/SwitchToggle_1\"]/android.view.ViewGroup")
	private WebElement onOffButton;
	
	@FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"com.CaaZa_Smart:id/SwitchToggle_2\"]/android.view.ViewGroup")
	private WebElement onOffButton2;
	
	@FindBy(xpath = "//*[@resource-id='MenuItem_AddandEditSwitchboard_0']")
	private WebElement addEditSwitchBoardButton;
	
	
	@FindBy(xpath = "//*[@resource-id='MenuItem_Settings_1']")
	private WebElement settingsButton;
	
	@FindBy(xpath = "//*[@resource-id='SettingsItem_ResetDevice']")
	private WebElement resetDeviceButton;
	
	@FindBy(xpath = "//*[@resource-id='CustomModal_ConfirmButton']")
	private WebElement resetConfirmationButton;
	
	@FindBy(xpath = "//*[@resource-id='Header_Back_Button']")
	private WebElement headerbackbutton;
	
	@FindBy(xpath = "//*[@resource-id='Switchboard_MenuIconContainer_0']")
	private WebElement switchBoardMenu1;
	
	@FindBy(xpath = "//*[@resource-id='Switchboard_RemoveOptionText_0']")
	private WebElement removeSwitchBoardOption;
	
	@FindBy(xpath = "//*[@resource-id='DisconnectedBadge_1']")
	private WebElement disconnectedIcon;
	
	
	//doing
	@FindBy(xpath = "//*[@resource-id='MenuItem_AddandEditSwitchboard_0']")
	private WebElement MenuItem_AddandEditSwitchboard;
	@FindBy(xpath = "//*[@resource-id='Switchboard_MenuTrigger_0']")
	private WebElement Switchboard_MenuTrigger;
	@FindBy(xpath = "//*[@resource-id='Switchboard_EditOptionText_0']")
	private WebElement Switchboard_EditOptionText_0;
	@FindBy(xpath = "//*[@resource-id='Switchboard_EditInput_0']")
	private WebElement switchpageTextbox;
	@FindBy(xpath = "//*[@resource-id='Switchboard_EditConfirm_0']")
	private WebElement Switchboard_EditConfirm;
	@FindBy(xpath = "//*[@resource-id='Header_BackIcon']")
	private WebElement Switchboard_BackIcon;
	@FindBy(xpath = "//*[@resource-id='SwitchToggle_1']")
	private WebElement SwitchToggle;
//	@FindBy(xpath = "//*[@resource-id='']")
//	private WebElement ;
//	@FindBy(xpath = "//*[@resource-id='']")
//	private WebElement ;
	
	private WebElement deviceName(int username) {
		return driver.findElement(By.xpath("//*[@resource-id='SwitchName_" + username + "\']"));

	}
	
	public void clickSwitchBoardMenu1() {
		clickbyXpath(switchBoardMenu1, "Click on Switch Board Menu Option Button");
	}
	
	public void clickRemoveSwitchBoardOption() {
		clickbyXpath(removeSwitchBoardOption, "Click on Remove Button");
	}
	public void clickOkButton() {
		if (isElementDisplayedCheck(okButton)) {
			clickbyXpath(okButton, "Pop-up Ok button");
		}
	}
	
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
	
	public void clickAddandEditSwitchboard() {
		clickbyXpath(MenuItem_AddandEditSwitchboard, "MenuItem Add and Edit Switchboard");
	}
	public void verifySwitchBoardname(String text) {
		verifyTextContainsByXpath(switchpageTextbox, text, "SwitchpageTextbox");
	}
	public void clickSwitchboardmenu() {
		clickbyXpath(Switchboard_MenuTrigger, "Swiychboardmenu trigger");
	}
	public void clickSwitchboardMenuEdit() {
		clickbyXpath(Switchboard_EditConfirm, "Switch Board menu edit button");
	}
	public void changeSwitchBoardname(String text) {
		entervaluebyXpath(switchpageTextbox, "Switchboard textbox value", text);
	}
	public void clickConfirmButton() {
		clickbyXpath(Switchboard_EditConfirm, "Switchboard Edit confirm button");
	}
	public void clickSwitchToggle() {
		
		if(isElementDisplayedCheck(SwitchToggle)) {
			clickbyXpath(SwitchToggle, "SwitchToggle");
		}else if(isElementDisplayedCheck(disconnectedIcon)){
			Reporter.reportStep("Disconnected Badge Displayed", "FAIL");
			
		}
	}

	public void NavigatetoSwitches(int switchnumber) {
		
		clickbyXpath(deviceName(switchnumber), "Switch card");
	}
}

