package caaza_testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AccountsInfoPage;
import pages.DeviceMenuPage;
import pages.HomePage;
import pages.LandingPage;
import pages.OtpPage;
import pages.Profilepage;
import pages.SettingsPage;
import pages.SignInPage;
import pages.StoreLogPage;
import utils.logReadandWrite;
import wrappers.MobileAppWrappers;

public class TC02_SignIn_Check extends MobileAppWrappers {

	LandingPage landingpage;
	SignInPage loginpage;
	HomePage homepage;
	OtpPage otppage;
	SettingsPage settingspage;
	AccountsInfoPage accountinfopage;
	DeviceMenuPage devicesettingpage;
	StoreLogPage logpage;
	Profilepage profilepage;
	
	@BeforeClass
	public void startTestCase() {
		testCaseName = "TC_02_SignIn_check";
		testDescription = "Login with valid user and log-out";
	}

	
	@Test(priority = 1)
	public void TC_02_SignIn_Check() throws Exception {
		initAndriodDriver();
		landingPageCheck();
	}


	public void landingPageCheck() throws Exception {
		landingpage= new LandingPage(driver);
		settingspage = new SettingsPage(driver);
		homepage= new HomePage(driver);
		profilepage = new Profilepage(driver);
		logReadandWrite readwrite = logReadandWrite.getInstance(loadProp("COM"));
		try {
//			readwrite.openPort();
			uninstall_reinstall();
			landingpage.clickLandingPageNextBtn();	
			landingpage.clickLandingPageNextBtn();			
			landingpage.enterUserName("Demouserauto");
			landingpage.enterPassword("Welcome@123");
			landingpage.clickSignInButton();
			homepage.clickSettingsButton();
			settingspage.clickLogOutButton();
			settingspage.clickLogOutConfirmButton();
			landingpage.checkSignInPageTitle();
			Thread.sleep(5000);
			
//			readwrite.closePort();
		}
		catch (Exception e) {
			readwrite.closePort();
			logpage.CollectLogOnFailure(testCaseName,testDescription);
			fail(e);
		}
	}


}	

