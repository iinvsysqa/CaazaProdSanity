package caaza_testcases;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AccountsInfoPage;
import pages.AddDevicePage;
import pages.DeviceMenuPage;
import pages.HomePage;
import pages.LandingPage;
import pages.OtpPage;
import pages.SettingsPage;
import pages.SignUpPage;
import pages.StoreLogPage;
import pages.SwitchPage;
import utils.logReadandWrite;
import wrappers.MobileAppWrappers;

public class SingleNodeSanity extends MobileAppWrappers {

	LandingPage landingpage;
	SignUpPage signuppage;
	HomePage homepage;
	OtpPage otppage;
	SettingsPage settingspage;
	AccountsInfoPage accountinfopage;
	DeviceMenuPage devicesettingpage;
	StoreLogPage logpage;
	AddDevicePage adddevicepage;
	SwitchPage switchpage;
	
	@BeforeClass
	public void startTestCase() {
		testCaseName = "Single Node Sanity Check";
		testDescription = "Single Node Sanity Check";
	}

	
	@Test(priority = 0)
	public void SingleNodeSanity_Check() throws Exception {
		
		initAndriodDriver();
		functionaCheck();
	}

	List<String> switchNames = Arrays.asList("Switch1");
	public void functionaCheck() throws Exception {
		landingpage= new LandingPage(driver);
		signuppage = new SignUpPage(driver);
		settingspage = new SettingsPage(driver);
		homepage= new HomePage(driver);
		adddevicepage= new AddDevicePage(driver);
		switchpage = new SwitchPage(driver);
		
		logReadandWrite readwrite = logReadandWrite.getInstance(loadProp("COM"));
		try {
//			readwrite.openPort();
			
//			landingpage.clickLandingPageNextBtn();			
//			landingpage.enterUserName("Demouserauto");
//			landingpage.enterPassword("Welcome@123");
//			landingpage.clickSignInButton();

			homepage.clickFloorSelctionBtn();
			adddevicepage.pair(2);
			adddevicepage.EnterNode(node,switchNames);
//			adddevicepage.waitForVerificationComplete();
//			adddevicepage.enterPanelName("PNL1");
//			adddevicepage.clickAddPanelSaveBtn();
//			//adddevicepage.clickAddswitchSaveBtn();
//			Thread.sleep(5000);
//			adddevicepage.enterSwitchName("SW1");
//			adddevicepage.clickSwitchTypeDropdown();
//			adddevicepage.clickFanType();
//			adddevicepage.clickAddswitchSaveBtn();
//			adddevicepage.clickOkButton();
			homepage.clickFloorSelctionBtn();
			homepage.clickPanel(0);
			Thread.sleep(5000);
			switchpage.clickOnOffButton();
			Thread.sleep(5000);
			switchpage.clickOnOffButton();
			switchpage.FetchSerailnumber();//newly added
			
			
			
			switchpage.clickMenuButton();
			switchpage.clickSettingsButton();
			switchpage.clickResetDeviceButton();
			switchpage.clickResetConfirmationButton();
			Thread.sleep(5000);
			driver.navigate().back();
			driver.navigate().back();
			//switchpage.clickBackButton();

//			readwrite.closePort();
		}
		catch (Exception e) {
//			readwrite.closePort();
//			logpage.CollectLogOnFailure(testCaseName,testDescription);
			fail(e);
		}
	}


}	