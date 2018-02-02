package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage extends BasePage {

    private static final Logger log = LogManager.getLogger();
    private String expectedPageTitle = "mPower | Educator Login";
    private String expectedUrlPart = "/confirm";

    public ConfirmationPage(WebDriver driver) {
        super(driver);

        if (!pageUrlContains(expectedUrlPart)) {
            log.error(getPageLoadError());
            log.error(getCurrentPageUrl());
            throw new IllegalStateException(getPageLoadError());
        }
    }

    public String getExpectedPageTitle() {
        return expectedPageTitle;
    }

}
