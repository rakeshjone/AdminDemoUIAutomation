package Pages;

import Framework.Browser;
import Framework.Util.DriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DashboardPage extends BasePage{
    private WebElement dashboard = DriverManager.getInstance().Driver.findElement(By.xpath("//div[@class='content-header']"));

    public DashboardPage(String title) {
        super(title);
    }

    public void VerifyDashboardPageIsDisplayed(){
        Browser.waitForElementToDisplay(dashboard);
    }
}
