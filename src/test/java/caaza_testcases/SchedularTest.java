package caaza_testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.AccountsInfoPage;
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

public class SchedularTest extends MobileAppWrappers{
	LandingPage landingpage;
	SignUpPage signuppage;
	HomePage homepage;
	OtpPage otppage;
	SettingsPage settingspage;
	AccountsInfoPage accountinfopage;
	DeviceMenuPage devicesettingpage;
	StoreLogPage logpage;
//	Schedularpage schedular;
	Schedulartestpage test;

	@BeforeClass
	public void startTestCase() {
		testCaseName = "TC_01_SignUp with Valid creds";
		testDescription = "Sign Up with valid user and log-out";
	}

	
	//@Test(priority = 0)
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
//		schedular= new Schedularpage(driver);
		
		logReadandWrite readwrite = logReadandWrite.getInstance(loadProp("COM"));
		try {
//			readwrite.openPort();
//			schedular.addScheduleButton();
//			schedular.createSchedules(3, 2, 3);//mention the time to start ,how many schedules need to keep,schedule duration like 1 min or 2 min
//			schedular.backToHomepage();
			
			
			test.createSchedulesFromConfig();
			
//			readwrite.closePort();
		}
		catch (Exception e) {	
//			readwrite.closePort();
//			logpage.CollectLogOnFailure(testCaseName,testDescription);
			fail(e);
		}
	}
}
