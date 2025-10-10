package caaza_testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.AccountsInfoPage;
import pages.AddDevicePage;
import pages.Analytics;
import pages.DeviceMenuPage;
import pages.HomePage;
import pages.LandingPage;
import pages.OtpPage;
import pages.Schedularpage;
import pages.Schedulartestpage;
import pages.SettingsPage;
import pages.SignUpPage;
import pages.StoreLogPage;
import utils.logReadandWrite;
import wrappers.MobileAppWrappers;

public class TC05_MultipleScheduleForSingleRoom extends MobileAppWrappers{
	LandingPage landingpage;
	SignUpPage signuppage;
	HomePage homepage;
	SettingsPage settingspage;
	StoreLogPage logpage;
	Schedularpage schedular;
	Schedulartestpage test;
	Analytics analytics;
	AddDevicePage adddevicepage;

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
		
		logReadandWrite readwrite = logReadandWrite.getInstance(loadProp("COM"));
		try {
//			readwrite.openPort();
//			schedular.addScheduleButton();
//			schedular.createSchedules(3, 2, 3);
			
			uninstall_reinstall();
			landingpage.clickLandingPageNextBtn();			
			landingpage.enterUserName(loadProp("USERNAME"));
			landingpage.enterPassword(loadProp("PASSWORD"));
			landingpage.clickSignInButton();
			
			
			
			homepage.enterFirstcard();
			adddevicepage.pair(2);
			adddevicepage.waitForVerificationComplete();
			adddevicepage.enterPanelName("PNL1");
			adddevicepage.clickAddPanelSaveBtn();
			Thread.sleep(5000);
			adddevicepage.enterSwitchName("Switch1");
			adddevicepage.clickSwitchTypeDropdown();
			adddevicepage.clickFanType();
			adddevicepage.clickAddswitchSaveBtn();
			adddevicepage.clickOkButton();
			
			analytics.navigateAnalyticsPage();
			analytics.getenergydurationvalue();
			homepage.homepagenavigation();
			homepage.enterFirstcard();
			schedular.createSchedules(1, 2, 2, 2);//mention switches count ,mention the time to start ,how many schedules need to keep,schedule duration like 1 min or 2 min
			homepage.homepagenavigation();
			homepage.homepagenavigation();
			Thread.sleep(60000*4);
			analytics.navigateAnalyticsPage();
			analytics.checkenrgyduration(4);
			
			
//			readwrite.closePort();
		}
		catch (Exception e) {	
//			readwrite.closePort();
//			logpage.CollectLogOnFailure(testCaseName,testDescription);
			fail(e);
		}
	}
}
