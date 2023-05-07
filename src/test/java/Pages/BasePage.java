package Pages;

import Framework.Browser;

public class BasePage {

    BasePage(String title){
        Browser.waitForTitle(title);
    }
}
