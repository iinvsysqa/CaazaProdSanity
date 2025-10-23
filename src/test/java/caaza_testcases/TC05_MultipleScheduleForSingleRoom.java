package caaza_testcases;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.AccountsInfoPage;
import pages.AddDevicePage;
import pages.Analytics;
import pages.DeviceMenuPage;
import pages.HierarchyPage;
import pages.HomePage;
import pages.LandingPage;
import pages.OtpPage;
import pages.Profilepage;
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
	HierarchyPage hierarchypage;
	Profilepage profilepage;
	

	@BeforeClass
	public void startTestCase() {
		testCaseName = "TC05_MultipleSchedule_Timer";
		testDescription = "Multiple Schedule and Timer";
	}

	
	@Test(priority = 4)
	public void TC05_MultipleSchedule_Timer() throws Exception {
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
		hierarchypage = new HierarchyPage(driver);
		profilepage = new Profilepage(driver);
		
		logReadandWrite readwrite = logReadandWrite.getInstance(loadProp("COM"));
		List<String> switchNames = Arrays.asList("Switch1");
		String Hierarchyname="apartment";
		String Oldpassword =loadProp("PASSWORD");
		String GeneratedPassword=updateProperty("PASSWORD", randomCharacters(3, 1)+randomCharacters(2, 2)+randomCharacters(3, 3)+randomCharacters(2, 4));
		String userName = updateProperty("USERNAME", randomCharacters(4,2 ));
		try {
//			readwrite.openPort();
			
			uninstall_reinstall();
			landingpage.clickLandingPageNextBtn();			
			landingpage.clickSignUpLink();
			signuppage.enterUserName(userName);
			signuppage.enterName(userName);
			signuppage.enterPassword(Oldpassword);
			signuppage.enterConfirmPassword(Oldpassword);
			signuppage.clickSignUpcheckbox();
			signuppage.clickSignUpNextButton();
			signuppage.enteranswer1("demo");
			signuppage.enteranswer2("demo");
			signuppage.clickSignUpButton();
			killAndReopenApp();
			hierarchypage.clickStartaNewHomeButton();
			hierarchypage.clickStartanewhometext();
			hierarchypage.enterHierarchyText(1, Hierarchyname);
			hierarchypage.clickCreateHierarchybtn();
			hierarchypage.addHierarchy_oneOption();
			
			
			//For schedules
			homepage.enterFirstcard();
			adddevicepage.pair(2);
			adddevicepage.EnterNode(node,switchNames);			
			analytics.navigateAnalyticsPage();
			analytics.getenergydurationvalue();
			homepage.navigateback();
			homepage.enterFirstcard();
			schedular.createSchedules(1, 2, 2, 2);//mention switches count ,mention the time to start ,how many schedules need to keep,schedule duration like 1 min or 2 min
			homepage.navigateback();
			homepage.navigateback();
			Thread.sleep(60000*4);
			homepage.enterFirstcard();
			Thread.sleep(5000);
			homepage.navigateback();
			
			analytics.navigateAnalyticsPage();
			analytics.checkenrgyduration(4);
			homepage.navigateback();
			
			//for timer
			analytics.navigateAnalyticsPage();
			analytics.getenergydurationvalue();
			homepage.navigateback();
			homepage.enterFirstcard();
			schedular.SetTimerForSwitches(1);
			homepage.navigateback();
			Thread.sleep(60000*1);
			
			homepage.enterFirstcard();
			Thread.sleep(5000);
			homepage.navigateback();
			
			analytics.navigateAnalyticsPage();
			analytics.checkenrgyduration(1);
			homepage.navigateback();
			
			//resetdevice
			homepage.enterFirstcard();
			settingspage.openMenuPage();
			settingspage.navigateSettingspage();
			settingspage.resetDevice();
			settingspage.navigateback();
			
			profilepage.clickApartmentIcon();
			profilepage.clickMenubaricon();
			profilepage.clickAddEditbtn();
			profilepage.DeleteHierarchy(1,Hierarchyname);
			profilepage.clickHierarchy_BackButton();
			
			profilepage.navigateSettingsbtn();
			profilepage.navigateProfileSettingsPage();
			profilepage.deleteAccount();
			profilepage.confirmDelete();
			profilepage.checkSignInButton();
//			readwrite.closePort();
		}
		catch (Exception e) {	
//			readwrite.closePort();
//			logpage.CollectLogOnFailure(testCaseName,testDescription);
			fail(e);
		}
	}
}
