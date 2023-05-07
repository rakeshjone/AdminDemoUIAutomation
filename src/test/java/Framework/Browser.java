package Framework;

import Framework.Util.ConfigurationManager;
import Framework.Util.DriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import java.util.function.BooleanSupplier;

public class Browser {

    public static void waitForElementToDisplay(WebElement element){
        Retry(()->element.isDisplayed());
    }

    public static void waitForTitle(String title){
        System.out.println("waiting for page: "+title+" to load.");
        Retry(()->
                DriverManager.getInstance().getPageTitle().contains(title));
    }

    public static void clickOnElement(WebElement element){
        Retry(()->element.isDisplayed() && element.isEnabled());
        element.click();
    }

    private static void Retry(BooleanSupplier function)
    {
        System.out.println("in retry");
        int count = 0;
        Exception exception = null;
        String exceptionMessage = "";
        int retryInterval = Integer.parseInt(ConfigurationManager.getInstance().getProperty("Polling"));
        int retryCount = 20; //read timeout and convert
        do
        {
            try
            {
                if (function.getAsBoolean()) {
                    System.out.println("found element");
                    return;
                }
                else{
                    Thread.sleep(retryInterval);
                    System.out.println("retry: "+count);
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