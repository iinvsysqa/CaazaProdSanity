package caaza_testcases;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.AccountsInfoPage;
import pages.DeviceMenuPage;
import pages.HierarchyPage;
import pages.HomePage;
import pages.LandingPage;
import pages.OtpPage;
import pages.Profilepage;
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
	HierarchyPage hierarchypage;
	Profilepage profilepage;

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

	logReadandWrite readwrite = logReadandWrite.getInstance(loadProp("COM"));
	List<String> switchNames = Arrays.asList("Switch1");
	String Hierarchyname="apartment";
	String Oldpassword =loadProp("PASSWORD");
	String GeneratedPassword=updateProperty("PASSWORD", randomCharacters(3, 1)+randomCharacters(2, 2)+randomCharacters(3, 3)+randomCharacters(2, 4));
	String userName = updateProperty("USERNAME", randomCharacters(4,2 ));
	
	public void landingPageCheck() throws Exception {
		landingpage= new LandingPage(driver);
		signuppage = new SignUpPage(driver);
		settingspage = new SettingsPage(driver);
		homepage= new HomePage(driver);
		hierarchypage = new HierarchyPage(driver);
		profilepage = new Profilepage(driver);
		
		logReadandWrite readwrite = logReadandWrite.getInstance(loadProp("COM"));
		try {
//			readwrite.openPort();
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
			signuppage.enteranswer2("demo");
			signuppage.clickSignUpButton();
			Thread.sleep(3000);
			killAndReopenApp();
			hierarchypage.clickStartaNewHomeButton();
			hierarchypage.clickStartanewhometext();
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
			
			//remove hierarchy and delete account
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
			readwrite.closePort();
			//logpage.CollectLogOnFailure(testCaseName,testDescription);
			fail(e);
		}
	}


}	