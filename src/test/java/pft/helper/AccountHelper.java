package pft.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pft.data.User;

/**
 * Created by linka on 20.04.2015.
 */


public class AccountHelper extends WebDriverBaseHelper {

    public AccountHelper(ApplicationManager applicationManager) {
        super(applicationManager);
    }

    public void signup(User user) {
        openUrl("/");
        click(By.cssSelector(".bracket-link>a"));
        type(By.name("username"), user.getLogin());
        type(By.xpath("//tr[3]/td[2]/input"), user.getEmail());
        click(By.cssSelector("input.button"));

        WebElement errorMessage = findElement(By.cssSelector("table.width50 tbody tr td p"));
        if (errorMessage != null) {
            throw new RuntimeException(errorMessage.getText());
        }

        pause(3000);

        Msg msg = manager.getMailHelper().getNewMail(user.getLogin(), user.getPassword());
        String confirmationLink = msg.getConfirmationLink();
        openAbsoluteUrl(confirmationLink);

        type(By.name("password"), user.getPassword());
        type(By.name("password_confirm"), user.getPassword());
        click(By.cssSelector("input.button"));
    }

    private void pause(int pause) {
        try {
            Thread.sleep(pause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void login(User user) {
        openUrl("/");
        type(By.name("username"), user.getLogin());
        type(By.name("password"), user.getPassword());
        click(By.cssSelector("input.button"));
    }

    public String loggedUser() {
        WebElement element = findElement(By.cssSelector("td.login-info-left span"));
        return element.getText();
    }

}
