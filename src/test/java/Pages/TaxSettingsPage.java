package Pages;

import Framework.Util.DriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TaxSettingsPage extends BasePage{
    WebElement taxSettings = DriverManager.getInstance().Driver.findElement(By.xpath("//div[@class='content-header clearfix']/h1"));

    public TaxSettingsPage(String title) {
        super(title);
    }

    public void VerifyTaxSettingsPageIsDisplayed(){
        Assert.assertTrue("TaxSettings page not displayed correctly.",taxSettings.isDisplayed());
    }
}
