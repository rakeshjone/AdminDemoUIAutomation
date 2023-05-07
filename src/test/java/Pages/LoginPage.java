package Pages;

import Framework.Browser;
import Framework.Util.DriverManager;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage{

    private WebElement loginButton = DriverManager.Driver.findElement(By.xpath("//div/button"));

    public LoginPage(String title){
        super(title);
    }

    public void clickOnLoginButton(){
        System.out.println("click on login");
        Browser.clickOnElement(loginButton);
    }

}
