package tests;

import pages.*;
import listeners.*;
import static utils.DriverFactory.*;
import org.testng.annotations.*;

/**
 * @author Chris Wade
 */

@Listeners({SuiteListener.class, TestListener.class, ClassListener.class})
public class BaseTest {

    SignUpPage signUpPage;
    LoginPage loginPage;
    ConfirmationPage confirmationPage;
    TermsAndConditionsPage termsAndConditionsPage;
    PrivacyPolicyPage privacyPolicyPage;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {
        setDriver(createDriver(browser));
        getDriver().manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        quitDriver();
    }

}
