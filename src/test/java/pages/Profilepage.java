package pages;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import wrappers.GenericWrappers;

public class Profilepage extends GenericWrappers{

	private AndroidDriver driver;
	public static String ConfiguredRouter;
	
	public Profilepage(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait=new WebDriverWait(driver, 10);
	}

	
	// Locate all elements on the page

	@FindBy(xpath = "//*[@resource-id='Tab_Settings_Icon']")
	private WebElement settingsicon;
	@FindBy(xpath = "//*[@resource-id='SettingScreen_Username']")
	private WebElement settingspage_Username;
	@FindBy(xpath = "//*[@resource-id='BackIcon_Component_Title']")
	private WebElement profilepage_Title;
	@FindBy(xpath = "//*[@resource-id='Profile_EditButton']")
	private WebElement profilepage_editBtn;

	@FindBy(xpath = "//*[@resource-id='Profile_UserNameText']")
	private WebElement profilepage_name;
	@FindBy(xpath = "//*[@resource-id='Profile_DOBLabel']")
	private WebElement profilepage_DOB;
	@FindBy(xpath = "//*[@resource-id='Profile_GenderLabel']")
	private WebElement profilepage_Gender;
	@FindBy(xpath = "//*[@resource-id='Profile_ChangePasswordText']")
	private WebElement profilepage_chnagepasswordbtn;
	@FindBy(xpath = "//*[@resource-id='Profile_DeleteAccountText']")
	private WebElement profilepage_DeleteAccountBtn;
	@FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"T\"]")
	private WebElement ProfilepageProfile_EditBtn;
	@FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"DOB\"]")
	private WebElement ProfilepageProfile_DOBbtn;
	@FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Gender\"]")
	private WebElement ProfilepageProfile_Genderbtn;
	@FindBy(xpath = "//*[@resource-id='SaveButton']")
	private WebElement ProfilepageProfile_SaveBtn;
	@FindBy(xpath = "//android.widget.TextView[@text=\"Choose from gallery\"]")
	private WebElement profilepageprofile_choosefromgallery;
	@FindBy(xpath = "//android.widget.TextView[@text=\"Take photo\"]")
	private WebElement profilepageprofile_takephotoBtn;
	@FindBy(xpath = "//android.widget.TextView[@text=\"ok\"]")
	private WebElement profilepageprofile_popupokBtn;
	@FindBy(xpath = "//android.widget.TextView[@text=\"Male\"]")
	private WebElement profilepageprofile_male;
	@FindBy(xpath = "//*[@resource-id='']")
	private WebElement profilepageprofile_female;
	@FindBy(xpath = "//*[@resource-id='']")
	private WebElement profilepageprofile_other;
	@FindBy(xpath = "//*[@resource-id='ChangePassword_CloseIcon']")
	private WebElement ChangePassword_CloseIcon;
	@FindBy(xpath = "//*[@resource-id='ChangePassword_CurrentPassword']")
	private WebElement ChangePassword_CurrentPassword;
	@FindBy(xpath = "//*[@resource-id='ChangePassword_NewPassword']")
	private WebElement ChangePassword_NewPassword;
	@FindBy(xpath = "//*[@resource-id='ChangePassword_ConfirmPassword']")
	private WebElement ChangePassword_ConfirmPassword;
	@FindBy(xpath = "//*[@resource-id='ChangePassword_ForgotPasswordText']")
	private WebElement ChangePassword_ForgotPasswordText;
	@FindBy(xpath = "//*[@resource-id=' Change PasswordText']")
	private WebElement  ChangePasswordBTN;
	@FindBy(xpath = "//*[@resource-id='ThemedButton_Text_ConfirmDelete']")
	private WebElement ConfirmDelete;
	@FindBy(xpath = "//*[@resource-id='Alert_SecondaryButtonText']")
	private WebElement cancelBtn;
//	@FindBy(xpath = "//*[@resource-id='']")
//	private WebElement ;
//	@FindBy(xpath = "//*[@resource-id='']")
//	private WebElement ;
	
	
	
	
//	Waitandverifytext();
	public void navigateSettingsbtn() {
		clickbyXpath(settingsicon, "Settings Icon");
	}
	public void verifySettingsPageUsername(String text) {
		verifyTextContainsByXpath(settingspage_Username, text, "SettingspageUsername");
	}
	public void navigateProfileSettingsPage() {
		clickbyXpath(settingspage_Username, "ProfileSettingsBtn");
	}
	public void verifyProfilename(String text) {
		verifyTextContainsByXpath(profilepage_name, text, "Profilename");
	}
	
	public void navigateProfileSettingsProfileeditpage() {
		clickbyXpath(profilepage_editBtn, "Profilepage Profile Edit button");
	}
	
	String imagepath="C:\\\\Users\\\\QA Automation\\\\eclipse-workspace\\\\Caaza_Automation\\\\profilimagepng.png";
	
	public void SetProfileimage() {
		
		clickbyXpath(ProfilepageProfile_EditBtn,"Profilepage profile Edit btn" ) ;
//		clickbyXpath(profilepageprofile_choosefromgallery,"Choose from gallery btn" ) ;
		profilepageprofile_choosefromgallery.sendKeys(imagepath);
		
	}
	public void setDOB() {
		ProfilepageProfile_DOBbtn.sendKeys("31/03/2001");
	}
}
