package pages;

import java.util.List;

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

	@FindBy(xpath = "//*[@resource-id='SwitchToggle_1']")
	private WebElement onOffButton;
	
	@FindBy(xpath = "//*[@resource-id='SwitchToggle_2']")
	private WebElement onOffButtonTwo;
	
	@FindBy(xpath = "//*[@resource-id='SwitchToggle_3']")
	private WebElement onOffButtonThree;
	
	@FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"com.CaaZa_Smart:id/SwitchToggle_2\"]/android.view.ViewGroup")
	private WebElement onOffButton2;
	
	@FindBy(xpath = "//*[@resource-id='MenuItem_AddandEditSwitchboard_0']")
	private WebElement addEditSwitchBoardButton;
	
	
	@FindBy(xpath = "//*[@resource-id='MenuItem_Settings_0']")
	private WebElement settingsButton;
	
	@FindBy(xpath = "//*[@resource-id='SettingsItem_ResetDevice']")
	private WebElement resetDeviceButton;
	
	@FindBy(xpath = "//*[@resource-id='SettingsItem_RemoveDevice']")
	private WebElement removeDeviceButton;
	
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
	@FindBy(xpath = "//*[@resource-id='Switchboard_DeviceName_0']")
	private WebElement Switchboard_DeviceName;
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
	private WebElement SwitchBoardMenu(int switches) {
		return driver.findElement(By.xpath("//*[@resource-id='Switchboard_MenuIconContainer_" + switches + "\']"));
	}
	private WebElement SwitchBoardEditoptiontext(int switches) {
		return driver.findElement(By.xpath("//*[@resource-id='Switchboard_EditOptionText_" + switches + "\']"));
		
	}
	private WebElement SwitchBoardEditconfirm(int switches) {
		return driver.findElement(By.xpath("//*[@resource-id='Switchboard_EditConfirm_" + switches + "\']"));
		
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
	
	public void clickSwitchboardmenuButton(int switches) {
		clickbyXpath(SwitchBoardMenu(switches), "Switchboardmenu");
	}

	public void clickOnOffButton() {
		if (isElementDisplayedCheck(onOffButton)) {
		clickbyXpath(onOffButton, "Click On/OFF 1 Button");
		}
	}
	public void DualnodeclickOnOffButton() {
		if (isElementDisplayedCheck(onOffButton)) {
			clickbyXpath(onOffButton, "Click On/OFF 1 Button");
			clickbyXpath(onOffButtonTwo, "Click On/OFF 1 Button");
		}
	}
	public void ThreenodeclickOnOffButton(int enternode) {
		switch (enternode) {
		case 1:
			clickbyXpath(onOffButton, "Click On/OFF 1 Button");
			break;
		case 2:
			clickbyXpath(onOffButton, "Click On/OFF 1 Button");
			clickbyXpath(onOffButtonTwo, "Click On/OFF 2 Button");
			break;
		case 3:	
			clickbyXpath(onOffButton, "Click On/OFF 1 Button");
			clickbyXpath(onOffButtonTwo, "Click On/OFF 2 Button");
			clickbyXpath(onOffButtonThree, "Click On/OFF 3 Button");
			break;
		 default:
             System.out.println("Invalid size number");
		}
//		if(isElementDisplayedCheck(onOffButtonThree)){
//		}else if(isElementDisplayedCheck(onOffButtonTwo)){
//		}else if (isElementDisplayedCheck(onOffButton)) {
//		}
	}

	public void clickOnOff2Button() {
		
		clickbyXpath(onOffButton2, "Click On/OFF 2 Button");
	}

	public void clickAddEditSwitchBoardButton() {
		clickbyXpath(addEditSwitchBoardButton, "Click On Add Edit Switchboard Button");
	}
	public void clickEditoptionText(int count) {
		clickbyXpath(SwitchBoardEditoptiontext(count), "Switchboard edit option");
	}
	
	public void clickSettingsButton() {
		clickbyXpath(settingsButton, "Click On settings Button");
	}
	
	public void clickResetDeviceButton() {
		clickbyXpath(resetDeviceButton, "Click On Reset Device Button");
	}
	public void clickRemoveDeviceButton() {
		clickbyXpath(removeDeviceButton, "Click On Reset Device Button");
	}
	
	public void clickResetConfirmationButton() {
		clickbyXpath(resetConfirmationButton, "Click On Reset Confirmation Button");
	}
	public void checkForpopup() {
		if(isElementDisplayedCheck(okButton)) {
			clickbyXpath(okButton, "ok button");
		}
	}
	
	public void clickBackButton() {
		try {
			expWaitTillElementDisplay(headerbackbutton,10);
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
		verifyTextContainsByXpath(Switchboard_DeviceName, text, "Switchboard name");
	}
	public void clickSwitchboardmenu() {
		clickbyXpath(Switchboard_MenuTrigger, "Swiychboardmenu trigger");
	}
	public void clickSwitchboardMenuEdit() {
		clickbyXpath(Switchboard_EditConfirm, "Switch Board menu edit button");
	}
	public void changeSwitchBoardname(String text) {
		switchpageTextbox.clear();
		entervaluebyXpath(switchpageTextbox, "Switchboard textbox value", text);
	}
	public void clickConfirmButton(int count) {
		clickbyXpath(SwitchBoardEditconfirm(count), "Switchboard Edit confirm button");
	}
	public void clickSwitchToggle() {
		
			clickbyXpath(SwitchToggle, "SwitchToggle");
	}
	public void checkforDisconenctedBadge() {
		 if(isElementDisplayedCheck(disconnectedIcon)){
				Reporter.reportStep("Disconnected Badge Displayed", "FAIL");
				
			}
	}
	public void NavigatetoSwitches(int switchnumber) {
		
		clickbyXpath(deviceName(switchnumber), "Switch card");
	}
	
	public void FetchSerailnumber() {
		
		try {
			List<WebElement> switchElements = driver.findElements(
		            By.xpath("//*[@resource-id[contains(., 'PanelName_')]]")
		        );

		        System.out.println("Found " + switchElements.size() + " switches:");
		        System.out.println("========================================");

		        for (WebElement element : switchElements) {
		            String resourceId = element.getAttribute("resource-id");
		            
		            // Extract serial number: everything after the LAST underscore
		            String serialNumber = resourceId.substring(resourceId.lastIndexOf("_") + 1);
		            Reporter.reportStep( "Serial Number -"+ serialNumber , "PASS");
		            System.out.println("Serial Number: " + serialNumber);
		        }
		}catch (Exception e) {
        	System.out.println(e);
        	 Reporter.reportStep( "Serial Number not fetched " , "PASS");
		}
		
	}
	public String FetchSerailnumber_Dual_Threenode() {
		
		String serialNumber = null;
		try {
			List<WebElement> switchElements = driver.findElements(
					By.xpath("//*[@resource-id[contains(., 'SwitchWrapper_')]]")
					);
			
			System.out.println("Found " + switchElements.size() + " switches:");
			System.out.println("========================================");
			
			for (WebElement element : switchElements) {
				String resourceId = element.getAttribute("resource-id");
				
				// Extract serial number: everything after the LAST underscore
				 serialNumber = resourceId.substring(resourceId.lastIndexOf("_") + 1);
				Reporter.reportStep( "Serial Number -"+ serialNumber , "PASS");
				System.out.println("Serial Number: " + serialNumber);
			}
		}catch (Exception e) {
			System.out.println(e);
			Reporter.reportStep( "Serial Number not fetched " , "PASS");
		}
		return serialNumber;
		
	}
}

