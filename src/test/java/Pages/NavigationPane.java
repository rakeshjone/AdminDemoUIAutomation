package Pages;

import Framework.Browser;
import Framework.Util.DriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class NavigationPane {
    WebElement burgerMenu = DriverManager.getInstance().Driver.findElement(By.id("nopSideBarPusher"));
    WebElement sideNav = DriverManager.getInstance().Driver.findElement(By.xpath(".//aside/div[contains(@class,'sidebar')]"));
    WebElement dashBoardNavItem = DriverManager.getInstance().Driver.findElement(By.xpath(".//aside//a/p[text()=' Dashboard']/ancestor::li[@class='nav-item']"));
    List<WebElement> mainNavTree = DriverManager.getInstance().Driver.findElements(By.xpath(".//aside//a/p[text()=' Dashboard']/ancestor::li[@class='nav-item']/following-sibling::*"));


    public void expandMainNavTreeInSideMenu(String linkName){
        boolean found = false;
             for (WebElement webElement : mainNavTree) {
                 WebElement label = webElement.findElement(By.xpath("./a/p"));
                 if (label.getText().trim().equals(linkName)){
                     Browser.clickOnElement(webElement);
                     Browser.waitUntilAttributeValueIs(webElement,"class","nav-item has-treeview menu-is-opening menu-open");
                     found = !found;
                     break;
                 }
             }
        Assert.assertTrue("Unable to expand main nav tree for: "+linkName,found);
    }

    public void clickOnPageNavigationLinkInMainNavTreeMenu(String linkName){
        boolean found = false;
        WebElement openedMainNavTreeItem = DriverManager.getInstance().Driver.findElement(By.xpath(".//aside//a/p[text()=' Dashboard']/ancestor::li[@class='nav-item']/following-sibling::li[@class='nav-item has-treeview menu-is-opening menu-open']"));
        List<WebElement> subMenuItems = openedMainNavTreeItem.findElements(By.xpath("./ul/li[@class='nav-item']"));
        for (WebElement webElement:subMenuItems) {
            if (webElement.findElement(By.xpath(".//p")).getText().equals(linkName)){
                Browser.clickOnElement(webElement);
                found = !found;
                break;
            }
        }
        Assert.assertTrue("Unable to click on main nav tree item: "+linkName,found);
    }

    public void expandSubNavTreeInSideMenu(String linkName){
        boolean found = false;
        WebElement openedMainNavTreeItem = DriverManager.getInstance().Driver.findElement(By.xpath(".//aside//a/p[text()=' Dashboard']/ancestor::li[@class='nav-item']/following-sibling::li[@class='nav-item has-treeview menu-is-opening menu-open']"));
        List<WebElement> subTreeItems = openedMainNavTreeItem.findElements(By.xpath(".//li[@class='nav-item has-treeview']"));
        for (WebElement webElement:subTreeItems) {
            if (webElement.findElement(By.xpath("./a/p")).getText().trim().equals(linkName)){
                Browser.clickOnElement(webElement);
                Browser.waitUntilAttributeValueIs(webElement,"class","nav-item has-treeview menu-is-opening menu-open");
                found =true;
                break;
            }
        }
        Assert.assertTrue("Unable to expand sub nav tree item: "+linkName,found);
    }

    public void clickOnPageNavigationLinkInSubNavTreeMenu(String linkName){
        boolean found = false;
        WebElement openedSubNavTreeItem = DriverManager.getInstance().Driver.findElement(By.xpath(".//aside//a/p[text()=' Dashboard']/ancestor::li[@class='nav-item']/following-sibling::li[@class='nav-item has-treeview menu-is-opening menu-open']//li[@class='nav-item has-treeview menu-is-opening menu-open']"));
        List<WebElement> subMenuItems = openedSubNavTreeItem.findElements(By.xpath("./ul/li[@class='nav-item']"));
        for (WebElement webElement:subMenuItems) {
            if (webElement.findElement(By.xpath("./a/p")).getText().equals(linkName)){
                Browser.clickOnElement(webElement);
                found = true;
                break;
            }
        }
        Assert.assertTrue("Unable to click on link in sub nav tree item: "+linkName,found);
    }

    public void clickOnLogoutButton(){
        WebElement logout = DriverManager.getInstance().Driver.findElement(By.xpath("//*[@id='navbarText']//a[text()='Logout']/.."));
        Browser.clickOnElement(logout);
    }

}
