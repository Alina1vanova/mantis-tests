package pft.tests;

import pft.data.ContactData;
import pft.data.GroupData;
import pft.helper.ApplicationManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TestBase {

    protected static ApplicationManager app;
    private int checkCounter;
    private int checkFrequency;

    @BeforeSuite
    public void setUp() throws Exception {
        String configFile = System.getProperty("configFile", "application.properties");
        Properties properties = new Properties();
        properties.load(new FileReader(new File(configFile)));
        app = new ApplicationManager(properties);
        checkCounter = 0;
        checkFrequency = Integer.parseInt(properties.getProperty("check.frequency", "0"));
    }

    protected boolean timeToCheck() {
        checkCounter++;
        if (checkCounter > checkFrequency) {
            checkCounter = 0;
            return true;
        } else {
            return false;
        }
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }

    public static List<Object[]> wrapGroupsDataProvider(List<GroupData> groups) {
        List<Object[]> list = new ArrayList<Object[]>();
        for (GroupData group : groups) {
            list.add(new Object[]{group});
        }
        return list;
    }

    public static List<Object[]> wrapContactsDataProvider(List<ContactData> contacts) {
        List<Object[]> list = new ArrayList<Object[]>();
        for (ContactData contact : contacts) {
            list.add(new Object[]{contact});
        }
        return list;
    }
}
