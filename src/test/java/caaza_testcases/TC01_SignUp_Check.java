package caaza_testcases;

import java.util.UUID;

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
import pages.SignUpPage;
import pages.StoreLogPage;
import utils.logReadandWrite;
import wrappers.MobileAppWrappers;

public class TC01_SignUp_Check extends MobileAppWrappers {

	LandingPage landingpage;
	SignUpPage signuppage;
	HomePage homepage;
	OtpPage otppage;
	SettingsPage settingspage;
	AccountsInfoPage accountinfopage;
	DeviceMenuPage devicesettingpage;
	StoreLogPage logpage;
	Profilepage profilepage;
	
	@BeforeClass
	public void startTestCase() {
		testCaseName = "TC_01_SignUp with Valid creds";
		testDescription = "Sign Up with valid user ";
		dataSheetName="Automation";
	}

	
	@Test(priority = 0)
	public void TC_01_SignUpcheck() throws Exception {
		initAndriodDriver();
		landingPageCheck();
	}


	public void landingPageCheck() throws Exception {
		landingpage= new LandingPage(driver);
		signuppage = new SignUpPage(driver);
		settingspage = new SettingsPage(driver);
		homepage= new HomePage(driver);
		profilepage = new Profilepage(driver);
		logReadandWrite readwrite = logReadandWrite.getInstance(loadProp("COM"));
		try {
			readwrite.openPort();
			
			
			uninstall_reinstall();
			landingpage.clickLandingPageNextBtn();	
			landingpage.clickSignUpLink();
			String randomStr = UUID.randomUUID().toString().replaceAll("[^a-zA-Z]", "").substring(0, 4);
			signuppage.enterUserName(randomStr);
			signuppage.enterName(randomStr);
			signuppage.enterPassword("Welcome@123");
			signuppage.enterConfirmPassword("Welcome@123");
			signuppage.clickSignUpcheckbox();
			signuppage.clickSignUpNextButton();
			signuppage.enteranswer1("demo");
			signuppage.enteranswer2("Demo");
			signuppage.clickSignUpButton();
			killAndReopenApp();
			Thread.sleep(5000);
			profilepage.navigateSettingsbtn();
			profilepage.navigateProfileSettingsPage();
			profilepage.deleteAccount();
			profilepage.confirmDelete();
			profilepage.checkSignInButton();
			readwrite.closePort();
		}
		catch (Exception e) {
			readwrite.closePort();
			logpage.CollectLogOnFailure(testCaseName,testDescription);
			fail(e);
		}
	}


}	

