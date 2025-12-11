package caaza_testcases;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.AccountsInfoPage;
import pages.AddDevicePage;
import pages.DeviceMenuPage;
import pages.HierarchyPage;
import pages.HomePage;
import pages.LandingPage;
import pages.OtpPage;
import pages.Profilepage;
import pages.SettingsPage;
import pages.SignUpPage;
import pages.StoreLogPage;
import pages.SwitchPage;
import utils.logReadandWrite;
import wrappers.MobileAppWrappers;

public class OTA_update extends MobileAppWrappers {

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
	HierarchyPage hierarchypage;
	Profilepage profilepage;
	
	@BeforeClass
	public void startTestCase() {
		testCaseName = "Check OTA update";
		testDescription = "Flashdevice /br Register Serial number /br Check OTA successfull update";
	}
	
	
	logReadandWrite readwrite = logReadandWrite.getInstance(loadProp("COM"));
	List<String> switchNames = Arrays.asList("Switch1");
	String Hierarchyname="apartment";
	String Oldpassword =loadProp("PASSWORD");
	String GeneratedPassword=updateProperty("PASSWORD", randomCharacters(3, 1)+randomCharacters(2, 2)+randomCharacters(3, 3)+randomCharacters(2, 4));
	String userName = updateProperty("USERNAME", randomCharacters(4,2 ));
	
	@Test()
	public void OTAupdatecheck() throws Exception {
		initAndriodDriver();
		functionaCheck();
	}


	public void functionaCheck() throws Exception {
		landingpage= new LandingPage(driver);
		signuppage = new SignUpPage(driver);
		settingspage = new SettingsPage(driver);
		homepage= new HomePage(driver);
		adddevicepage= new AddDevicePage(driver);
		switchpage = new SwitchPage(driver);
		hierarchypage = new HierarchyPage(driver);
		profilepage = new Profilepage(driver);
		
		logReadandWrite readwrite = logReadandWrite.getInstance(loadProp("COM"));
		try {
			readwrite.openPort();
			readwrite.write("factory_reset\r");
			uninstall_reinstall();
			landingpage.clickLandingPageNextBtn();
			landingpage.enterUserName("Demouserauto");
			landingpage.enterPassword("Welcome@123");
			landingpage.clickSignInButton();
			
			readwrite.closePort();
		}
		catch (Exception e) {
			readwrite.closePort();
			logpage.CollectLogOnFailure(testCaseName,testDescription);
			fail(e);
		}
  }
}
