package pages;

import java.util.List;
import static utils.DriverFactory.getDriver;
import static utils.PropertyUtil.getUrlProperty;
import static utils.ExecuteJavaScript.scrollIntoView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Chris Wade
 */

public class SignUpPage extends BasePage {

    private static final Logger log = LogManager.getLogger();
    private static String url = getUrlProperty("prodUrl");
    private String expectedPageTitle = "mPower | Educator Login";
    private String expectedUrlPart = "/signup";

    @FindBy(id = "firstName")
    private WebElement firstNameTextBox;

    @FindBy(id = "lastName")
    private WebElement lastNameTextBox;

    @FindBy(css = "select[id='educatorRoleId']")
    private WebElement selectRoleDropdownBox;

    @FindBy(css = "select[id='boardId']")
    private WebElement selectBoardDropdownBox;

    @FindBy(css = "select[id='schoolId']")
    private WebElement selectSchoolDropdownBox;

    @FindBy(css = "select[id='schoolId'] option")
    private List<WebElement> schoolOptionsList;

    @FindBy(xpath = "//a[contains(text(), 'click here')]")
    private WebElement clickHereLink;

    @FindBy(id = "tempSchoolName")
    private WebElement addYourSchoolTextBox;

    @FindBy(xpath = "//a[contains(text(), 'Cancel')]")
    private WebElement cancelLink;

    @FindBy(id = "email")
    private WebElement emailTextBox;

    @FindBy(id = "userEmailConfirm")
    private WebElement confirmEmailTextBox;

    @FindBy(id = "userPassword")
    private WebElement passwordTextBox;

    @FindBy(id = "userPasswordConfirm")
    private WebElement confirmPasswordTextBox;

    @FindBy(css = "select[id='source']")
    private WebElement selectSourceDropdownBox;

    @FindBy(id = "sourceOther")
    private WebElement otherSourceTextBox;

    @FindBy(css = "label a[href*='termsandconditions']")
    private WebElement termsOfServiceLink;

    @FindBy(css = "label a[href*='privacypolicy']")
    private WebElement privacyPolicyLink;

    @FindBy(xpath = "//input[@id='agreement']")
    private WebElement agreementCheckbox;

    @FindBy(xpath = "//input[@id='emailPromos']")
    private WebElement emailPromosCheckbox;

    @FindBy(css = "button[id='registerSubmit']")
    private WebElement registerBtn;

    @FindBy(css = "button[class*='btn-cancel']")
    private WebElement cancelBtn;

    @FindBy(xpath = "//div[contains(text(), 'first')][contains(text(), 'alphanumeric')]")
    private WebElement firstNameInvalidCharactersErrorMsg;

    @FindBy(xpath = "//div[contains(text(), 'password')][contains(text(), 'characters')]")
    private WebElement passwordLengthErrorMsg;

    public SignUpPage(WebDriver driver) {
        super(driver);

        if (!pageUrlContains(expectedUrlPart)) {
            log.error(getPageLoadError());
            log.error(getCurrentPageUrl());
            throw new IllegalStateException(getPageLoadError());
        }
    }

    public static SignUpPage open() {
        load(url);
        return pageFactoryObject(getDriver(), SignUpPage.class);
    }

    public SignUpPage enterFirstName(String firstName) {
        waitUntilElementIsVisible(firstNameTextBox);
        type(firstNameTextBox, firstName);
        return this;
    }

    public SignUpPage enterLastName(String lastName) {
        type(lastNameTextBox, lastName);
        return this;
    }

    public SignUpPage selectRole(String role) {
        click(selectRoleDropdownBox);
        selectDropdownOption(selectRoleDropdownBox, role);
        return this;
    }

    public SignUpPage selectBoard(String board) {
        click(selectBoardDropdownBox);
        selectDropdownOption(selectBoardDropdownBox, board);
        return this;
    }

    public SignUpPage selectSchool(String school) {
        waitAndClick(selectSchoolDropdownBox);
        waitUntilAllElementsAreVisible(schoolOptionsList);
        selectDropdownOption(selectSchoolDropdownBox, school);
        return this;
    }

    public SignUpPage selectClickHere() {
        waitAndClick(clickHereLink);
        return this;
    }

    public SignUpPage enterSchoolName(String schoolName) {
        type(addYourSchoolTextBox, schoolName);
        return this;
    }

    public SignUpPage selectCancel() {
        click(cancelLink);
        return this;
    }

    public SignUpPage enterEmail(String email) {
        type(emailTextBox, email);
        return this;
    }

    public SignUpPage enterConfirmEmail(String email) {
        type(confirmEmailTextBox, email);
        return this;
    }

    public SignUpPage enterPassword(String password) {
        type(passwordTextBox, password);
        return this;
    }

    public SignUpPage enterConfirmPassword(String password) {
        type(confirmPasswordTextBox, password);
        return this;
    }

    public SignUpPage selectSource(String source) {
        click(selectSourceDropdownBox);
        selectDropdownOption(selectSourceDropdownBox, source);
        return this;
    }

    public SignUpPage enterOtherSource(String otherSource) {
        type(otherSourceTextBox, otherSource);
        return this;
    }

    public TermsAndConditionsPage goToTermsAndConditionsPage() {
        scrollIntoView(termsOfServiceLink);
        click(termsOfServiceLink);
        waitUntilUrlContains("termsandconditions");
        return pageFactoryObject(getDriver(), TermsAndConditionsPage.class);
    }

    public PrivacyPolicyPage goToPrivacyPolicyPage() {
        scrollIntoView(privacyPolicyLink);
        click(privacyPolicyLink);
        waitUntilUrlContains("privacypolicy");
        return pageFactoryObject(getDriver(), PrivacyPolicyPage.class);
    }

    public SignUpPage selectAgreementCheckbox() {
        click(agreementCheckbox);
        return this;
    }

    public SignUpPage selectEmailPromosCheckbox() {
        click(emailPromosCheckbox);
        return this;
    }

    public ConfirmationPage register() {
        scrollIntoView(registerBtn);
        click(registerBtn);
        waitUntilUrlContains("confirm");
        return pageFactoryObject(getDriver(), ConfirmationPage.class);
    }

    public LoginPage cancel() {
        scrollIntoView(cancelBtn);
        click(cancelBtn);
        waitUntilUrlContains("login");
        return pageFactoryObject(getDriver(), LoginPage.class);
    }

    public SignUpPage registerExpectingError() {
        scrollIntoView(registerBtn);
        click(registerBtn);
        return this;
    }

    public boolean selectSchoolDropdownBoxIsDisplayed() {
        return elementIsDisplayed(selectSchoolDropdownBox);
    }

    public boolean selectSchoolDropdownBoxIsEnabled() {
        return elementIsEnabled(selectSchoolDropdownBox);
    }

    public boolean clickHereLinkIsDisplayed() {
        return elementIsDisplayed(clickHereLink);
    }

    public boolean addYourSchoolTextBoxIsDisplayed() {
        return elementIsDisplayed(addYourSchoolTextBox);
    }

    public boolean otherSourceTextBoxIsEnabled() {
        return elementIsEnabled(otherSourceTextBox);
    }

    public String getFirstNameInvalidCharactersErrorMsg() {
        scrollIntoView(firstNameInvalidCharactersErrorMsg);
        return getElementInnerText(firstNameInvalidCharactersErrorMsg);
    }

    public String getPasswordLengthErrorMsg() {
        return getElementInnerText(passwordLengthErrorMsg);
    }

    public String getExpectedPageTitle() {
        return expectedPageTitle;
    }

}
