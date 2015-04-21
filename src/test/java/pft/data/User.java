package pft.data;

/**
 * Created by linka on 20.04.2015.
 */
public class User {


        private String id;
        private String login;
        private String password;
        private String email;

        public String getId() {
            return id;
        }

        public User setId(String id) {
            this.id = id;
            return this;
        }

        public User setLogin(String login) {
            this.login = login;
            return this;
        }

        public User setPassword(String password) {
            this.password = password;
            return this;
        }

        public User setEmail(String email) {
            this.email = email;
            return this;
        }

        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }

        public String getEmail() {
            return email;
        }

    }
