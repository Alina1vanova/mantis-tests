package pft.tests;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pft.data.User;
import pft.helper.AccountHelper;
import pft.helper.JamesHelper;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.fail;

public class SignupTest extends TestBase {

    public User user = new User().setLogin("testuser1").setPassword("123456")
            .setEmail("testuser1@localhost.localdomain");

    private JamesHelper james;
    private AccountHelper accHelper;

    @BeforeClass
    public void createMailUser() {
        accHelper = app.getAccountHelper();
        james = app.getJamesHelper();
        if (! james.doesUserExist(user.getLogin())) {
            james.createUser(user.getLogin(), user.getPassword());
        }
    }

    @AfterClass
    public void deleteMailUser() {
        if (james.doesUserExist(user.getLogin())) {
            james.deleteUser(user.getLogin());
        }
    }

    @Test
    public void newUserShouldSignup() throws Exception {
        accHelper.signup(user);
        accHelper.login(user);
        assertThat(accHelper.loggedUser(), equalTo(user.getLogin()));
    }

    @Test
    public void existingUserShouldNotSignup() throws Exception {
        try {
            accHelper.signup(user);
        } catch (Exception e) {
            assertThat(e.getMessage(), containsString("That username is already being used"));
            return;
        }
        fail("Exception expected");
    }

}
