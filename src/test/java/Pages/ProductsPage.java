package Pages;

import Framework.Browser;
import Framework.Util.DriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductsPage extends BasePage{

    private WebElement products = DriverManager.getInstance().Driver.findElement(By.xpath("//div[@class='content-header clearfix']/h1"));

    public ProductsPage(String title) {
        super(title);
    }

    public void VerifyProductsPageIsDisplayed(){
        Assert.assertTrue("Products page not displayed correctly.",products.isDisplayed());
    }
}
