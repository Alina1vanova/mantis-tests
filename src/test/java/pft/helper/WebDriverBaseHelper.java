package pft.helper;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public abstract class WebDriverBaseHelper extends BaseHelper {

    protected WebDriver driver;

    public WebDriverBaseHelper(ApplicationManager manager) {
        super(manager);
        this.manager = manager;
        this.driver = manager.getDriver();
    }

    public void openUrl(String url) {
        driver.get(manager.getProperty("baseURL") + url);
    }

    protected void openAbsoluteUrl(String string) {
        driver.get(string);
    }


    public void click(By by) {
        driver.findElement(by).click();
    }

    protected void type(By locator, String name) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(name);
    }

    protected WebElement findElement(By linkText) {
        try {
            return driver.findElement(linkText);
        } catch (Exception e) {
            return null;
        }
    }

    public String getText(By by) {
        return driver.findElement(by).getText();
    }
}
