package tests;

import static pages.SignUpPage.*;
import static utils.FileUtil.*;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 * @author Chris Wade
 */

public class SignUpPageTest extends BaseTest {

    @Test
    public void testCreateAccount() {

        signUpPage = open();
        confirmationPage = signUpPage.enterFirstName("Automation").enterLastName("Developer")
                .selectRole("Teacher")
                .selectBoard("York Catholic DSB").selectSchool("Holy Spirit")
                .enterEmail("test16@ycdsb.ca").enterConfirmEmail("test16@ycdsb.ca")
                .enterPassword("qwertyui").enterConfirmPassword("qwertyui")
                .selectSource("TeachOntario")
                .selectAgreementCheckbox()
                .selectEmailPromosCheckbox()
                .register();

        assertTrue(confirmationPage.pageUrlContains("confirm"));

    }

    @Test
    public void testCancelButton() {

        signUpPage = open();
        loginPage = signUpPage.enterFirstName("Automation").enterLastName("Developer")
                .selectRole("Consultant")
                .selectBoard("TVO").selectSchool("TVO")
                .enterEmail("test@tvo.ca").enterConfirmEmail("test@tvo.ca")
                .enterPassword("qwertyui").enterConfirmPassword("qwertyui")
                .selectSource("TeachOntario")
                .selectAgreementCheckbox()
                .cancel();

        assertTrue(loginPage.pageUrlContains("login"));

    }

    @Test
    public void verifySelectSchoolDropdownIsDisplayedAfterSelectingBoard() {

        signUpPage = open();
        signUpPage = signUpPage.enterFirstName("Automation").enterLastName("Developer")
                .selectRole("Administrator")
                .selectBoard("Peel DSB");

        assertTrue(signUpPage.selectSchoolDropdownBoxIsDisplayed());

    }

    @Test
    public void verifyClickHereLinkIsDisplayedAfterSelectingBoard() {

        signUpPage = open();
        signUpPage = signUpPage.enterFirstName("Automation").enterLastName("Developer")
                .selectRole("Faculty Instructor")
                .selectBoard("University of Toronto");

        assertTrue(signUpPage.clickHereLinkIsDisplayed());

    }

    @Test
    public void testClickHereLink() {

        signUpPage = open();
        signUpPage = signUpPage.enterFirstName("Automation").enterLastName("Developer")
                .selectRole("Superintendent")
                .selectBoard("Trent University").selectClickHere();

        assertTrue(signUpPage.addYourSchoolTextBoxIsDisplayed());

    }

    @Test
    public void verifyClickHereLinkDisablesSelectSchoolDropdown() {

        signUpPage = open();
        signUpPage = signUpPage.enterFirstName("Automation").enterLastName("Developer")
                .selectRole("Teacher")
                .selectBoard("Bluewater DSB").selectClickHere();

        assertTrue(!signUpPage.selectSchoolDropdownBoxIsEnabled());

    }

    @Test
    public void testCancelLink() {

        signUpPage = open();
        signUpPage = signUpPage.enterFirstName("Automation").enterLastName("Developer")
                .selectRole("Superintendent")
                .selectBoard("TVO").selectClickHere()
                .enterSchoolName("Test Automation School").selectCancel();

        assertTrue(!signUpPage.addYourSchoolTextBoxIsDisplayed());

    }

    @Test
    public void verifyOtherSourceTextBoxIsEnabledAfterSelectingSourceOther() {

        signUpPage = open();
        signUpPage = signUpPage.enterFirstName("Automation").enterLastName("Developer")
                .selectRole("Public Librarian")
                .selectBoard("Algoma DSB").selectSchool("Central Avenue")
                .enterEmail("test@adsb.on.ca").enterConfirmEmail("test@adsb.on.ca")
                .enterPassword("qwertyui").enterConfirmPassword("qwertyui")
                .selectSource("Other");

        assertTrue(signUpPage.otherSourceTextBoxIsEnabled());

    }

    @Test
    public void testTermsOfServiceLink() {

        signUpPage = open();
        termsAndConditionsPage = signUpPage.enterFirstName("Automation").enterLastName("Developer")
                .selectRole("Teacher")
                .goToTermsAndConditionsPage();

        assertTrue(termsAndConditionsPage.pageUrlContains("termsandconditions"));

    }

    @Test
    public void testPrivacyPolicyLink() {

        signUpPage = open();
        privacyPolicyPage = signUpPage.enterFirstName("Automation").enterLastName("Developer")
                .selectRole("Teacher")
                .goToPrivacyPolicyPage();

        assertTrue(privacyPolicyPage.pageUrlContains("privacypolicy"));

    }

    @Test
    public void verifyFirstNameInvalidCharactersErrorMessage() {

        signUpPage = open();
        signUpPage = signUpPage.enterFirstName("Automation!@#$%").enterLastName("Developer")
                .selectRole("Instructional Leader")
                .selectBoard("Durham DSB").selectSchool("Winchester")
                .enterEmail("test@ddsb.ca").enterConfirmEmail("test@ddsb.ca")
                .enterPassword("qwertyui").enterConfirmPassword("qwertyui")
                .selectSource("Other").enterOtherSource("Other Source")
                .selectAgreementCheckbox()
                .registerExpectingError();

        String actualErrorMessage = signUpPage.getFirstNameInvalidCharactersErrorMsg();
        String expectedErrorMessage = getSignUpPageErrorMessages().get(1);

        assertEquals(actualErrorMessage, expectedErrorMessage);

    }

    @Test
    public void verifyPasswordLengthErrorMessage() {

        signUpPage = open();
        signUpPage = signUpPage.enterFirstName("Automation").enterLastName("Developer")
                .selectRole("Instructional Leader")
                .selectBoard("Durham DSB").selectSchool("Winchester")
                .enterEmail("test@ddsb.ca").enterConfirmEmail("test@ddsb.ca")
                .enterPassword("qw123").enterConfirmPassword("qw123")
                .selectSource("Conference")
                .selectAgreementCheckbox()
                .registerExpectingError();

        String actualErrorMessage = signUpPage.getPasswordLengthErrorMsg();
        String expectedErrorMessage = getSignUpPageErrorMessages().get(11);

        assertEquals(actualErrorMessage, expectedErrorMessage);

    }

}
