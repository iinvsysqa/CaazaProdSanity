package caaza_testcases;

import java.util.UUID;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.AccountsInfoPage;
import pages.DeviceMenuPage;
import pages.HomePage;
import pages.LandingPage;
import pages.OtpPage;
import pages.SettingsPage;
import pages.SignUpPage;
import pages.StoreLogPage;
import utils.logReadandWrite;
import wrappers.MobileAppWrappers;

public class TC03_SingUp_Create_hierarchy extends MobileAppWrappers {

	LandingPage landingpage;
	SignUpPage signuppage;
	HomePage homepage;
	OtpPage otppage;
	SettingsPage settingspage;
	AccountsInfoPage accountinfopage;
	DeviceMenuPage devicesettingpage;
	StoreLogPage logpage;

	@BeforeClass
	public void startTestCase() {
		testCaseName = "TC_01_SignUp with Valid creds and Create Hierarchy";
		testDescription = "Sign Up with valid user and Create Hierrachy";
	}

	
	@Test(priority = 2)
	public void TC03_SingUp_Create_hierarchy_Method() throws Exception {
		initAndriodDriver();
		landingPageCheck();
	}


	public void landingPageCheck() throws Exception {
		landingpage= new LandingPage(driver);
		signuppage = new SignUpPage(driver);
		settingspage = new SettingsPage(driver);
		homepage= new HomePage(driver);
		logReadandWrite readwrite = logReadandWrite.getInstance(loadProp("COM"));
		try {
			readwrite.openPort();
			
			landingpage.clickSignUpLink();
			String randomStr = UUID.randomUUID().toString().replaceAll("[^a-zA-Z]", "").substring(0, 4);
			signuppage.enterUserName(randomStr);
			signuppage.enterName(randomStr);
			signuppage.enterPassword("Welcome@123");
			signuppage.enterConfirmPassword("Welcome@123");
			signuppage.clickSignUpcheckbox();
			signuppage.clickSignUpNextButton();
			signuppage.enteranswer1("demo");
			signuppage.enteranswer2("demo");
			signuppage.clickSignUpButton();
			homepage.enterHierarchyText(1, "Apartment");
			homepage.clickHierarchyAddButton(1);
			homepage.enterHierarchyText(2, "Block");
			homepage.clickHierarchyAddButton(2);
			homepage.enterHierarchyText(3, "Floor");
			homepage.clickHierarchyAddButton(3);
			homepage.enterHierarchyText(4, "Room");
			homepage.clickCreateHierarchybtn();
			homepage.addHierarchyOption();
			
			Thread.sleep(5000);
			readwrite.closePort();
		}
		catch (Exception e) {
			readwrite.closePort();
			//logpage.CollectLogOnFailure(testCaseName,testDescription);
			fail(e);
		}
	}


}	