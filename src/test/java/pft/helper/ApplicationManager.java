package pft.helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.Properties;


/**
 * Created by linka on 24.02.2015.
 */

public class ApplicationManager {
    private WebDriver driver;

    private HibernateHelper hibernateHelper;
    private AccountHelper accountHelper;
    private MailHelper mailHelper;
    private JamesHelper jamesHelper;

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    private Properties properties;


    public ApplicationManager(Properties properties) {
        this.properties = properties;
    }

    public void stop() {
        driver.quit();
    }

    public MailHelper getMailHelper() {
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }
    public JamesHelper getJamesHelper() {
        if (jamesHelper == null) {
            jamesHelper = new JamesHelper(this);
        }
        return jamesHelper;
    }

    public AccountHelper getAccountHelper() {
        if (accountHelper == null) {
            accountHelper = new AccountHelper(this);
        }
        return accountHelper;
    }

    public HibernateHelper getHibernateHelper() {
        if (hibernateHelper == null) {
            hibernateHelper = new HibernateHelper(this);
        }
        return hibernateHelper;
    }

    public WebDriver getDriver() {
        if (driver == null) {
            String browser = properties.getProperty("browser");
            if ("firefox".equals(browser)) {
                //  FirefoxProfile profile = new FirefoxProfile();
                driver = new FirefoxDriver();
            }
            // driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            else if ("chrome".equals(browser)) {
                //  System.setProperty("webdriver.chrome.driver", "D:\\BrowserDrivers\\chromedriver.exe");
                driver = new ChromeDriver();
            } else {
                throw new Error("Unsupported browser: " + browser);
            }
            driver.get(properties.getProperty("baseURL"));
        }
        return driver;
    }
}

