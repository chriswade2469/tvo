package utils;

import static utils.DriverFactory.getDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

/**
 * @author Chris Wade
 */

public class ExecuteJavaScript {

    private ExecuteJavaScript() {
        // Suppress default constructor for noninstantiability
    }

    public static Object executeJavaScript(String script, Object args) {

        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        Object object = js.executeScript(script, args);
        return object;
    }

    public static void scrollIntoView(WebElement element) {
        executeJavaScript("arguments[0].scrollIntoView(false);", element);
    }

}
