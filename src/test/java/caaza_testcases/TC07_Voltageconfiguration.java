package caaza_testcases;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.AddDevicePage;
import pages.Analytics;
import pages.HomePage;
import pages.LandingPage;
import pages.ScenecreationPage;
import pages.Schedularpage;
import pages.Schedulartestpage;
import pages.SettingsPage;
import pages.SignUpPage;
import pages.StoreLogPage;
import utils.logReadandWrite;
import wrappers.MobileAppWrappers;

public class TC07_Voltageconfiguration extends MobileAppWrappers{

	LandingPage landingpage;
	SignUpPage signuppage;
	HomePage homepage;
	SettingsPage settingspage;
	StoreLogPage logpage;
	Schedularpage schedular;
	Schedulartestpage test;
	Analytics analytics;
	AddDevicePage adddevicepage;
	ScenecreationPage scenecreation;

	@BeforeClass
	public void startTestCase() {
		testCaseName = "TC_01_SignUp with Valid creds";
		testDescription = "Sign Up with valid user and log-out";
	}

	
	@Test(priority = 4)
	public void TC_01_Account_Info_page_check() throws Exception {
		initAndriodDriver();
		landingPageCheck();
    }
	
	public void landingPageCheck() throws Exception {
		landingpage= new LandingPage(driver);
		signuppage = new SignUpPage(driver);
		settingspage = new SettingsPage(driver);
		homepage= new HomePage(driver);
		test= new Schedulartestpage(driver);
		logpage= new StoreLogPage(driver);
		schedular= new Schedularpage(driver);
		analytics = new Analytics(driver);
		adddevicepage = new AddDevicePage(driver);
		scenecreation =new ScenecreationPage(driver);
		
		logReadandWrite readwrite = logReadandWrite.getInstance(loadProp("COM"));
		List<String> switchNames = Arrays.asList("Switch1");
		try {
//			readwrite.openPort();
			uninstall_reinstall();
			landingpage.clickLandingPageNextBtn();			
			landingpage.enterUserName(loadProp("USERNAME"));
			landingpage.enterPassword(loadProp("PASSWORD"));
			landingpage.clickSignInButton();
			
			
			
			homepage.enterFirstcard();
			adddevicepage.pair(2);
			adddevicepage.EnterNode(1,switchNames);
			homepage.enterFirstcard();
			settingspage.openMenuPage();
			settingspage.navigateSettingspage();
			
			settingspage.navigateHighVoltCutoff();
			settingspage.clickHighVoltToggle();
			settingspage.enterHighVoltagevalue("270");
			settingspage.clickSavebutton();
			settingspage.verifyHighvoltToast();
			
			settingspage.navigateHighVoltCutoff();
			settingspage.verifyHighVoltValues("270");
			settingspage.navigateback();
			
			
			settingspage.navigateLowVoltCutoff();
			settingspage.clickLowVoltToggle();
			settingspage.enterLowVoltagevalue("170");
			settingspage.clickSavebutton();
			settingspage.verifyLowvoltToast();
			
			settingspage.navigateLowVoltCutoff();
			settingspage.verifyLowVoltValues("170");
			settingspage.navigateback();
			
//modify router			
			settingspage.navigateback();
			settingspage.openMenuPage();
			settingspage.navigateSettingspage();
			settingspage.resetDevice();
			settingspage.navigateback();
			
			
			
//			readwrite.closePort();
		}
		catch (Exception e) {	
//			readwrite.closePort();
//			logpage.CollectLogOnFailure(testCaseName,testDescription);
			fail(e);
		}
	}
}
