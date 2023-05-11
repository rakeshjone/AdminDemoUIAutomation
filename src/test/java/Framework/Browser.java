package Framework;

import Framework.Util.ConfigurationManager;
import Framework.Util.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.JavascriptExecutor;

import java.util.function.BooleanSupplier;

public class Browser {

    public static void waitForElementToDisplay(WebElement element){
        Retry(()->element.isDisplayed() && element.isEnabled());
    }

    public static void waitForTitle(String title){
        Retry(()->
                DriverManager.getInstance().getPageTitle().contains(title));
    }

    public static void clickOnElement(WebElement element){
        waitForElementToDisplay(element);
        element.click();
    }

    public static void waitUntilAttributeValueIs(WebElement element, String propertyName, String expectedValue){
        Retry(()->element.getAttribute(propertyName).equals(expectedValue));
    }

    public static String getTextFromElement(WebElement element){
        Retry(()-> element.isDisplayed() && element.isEnabled());
        return element.getText();
    }

    public static void WaitForReady(){
        DriverManager.getInstance().pageReady();
    }

    private static void Retry(BooleanSupplier function)
    {
        int count = 0;
        Exception exception = null;
        String exceptionMessage = "";
        int retryInterval = Integer.parseInt(ConfigurationManager.getInstance().getProperty("Polling"));
        int timeOut = Integer.parseInt(ConfigurationManager.getInstance().getProperty("Timeout"));
        float temp = ((float) retryInterval/1000) % 60;
        int retryCount = (int) (timeOut/ temp);
        do
        {
            try
            {
                if (function.getAsBoolean()) {
                    return;
                }
                else{
                    Thread.sleep(retryInterval);
                    count++;
                }
            }
            catch (Exception ex)
            {
                exception = ex;
                count++;
            }
        } while (count != retryCount);
        System.out.println(exceptionMessage = "Retry Timed Out while trying to execute - " + new Throwable().getStackTrace()[1].getMethodName());
        throw new RuntimeException(exceptionMessage + exception);
    }
}