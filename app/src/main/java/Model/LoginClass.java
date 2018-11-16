package Model;

public class LoginClass
{
    private String UsernameOrEmail;
    private String Password;

    public String getUsernameOrEmail() {
        return UsernameOrEmail;
    }

    public LoginClass(String usernameOrEmail, String password) {
        UsernameOrEmail = usernameOrEmail;
        Password = password;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        UsernameOrEmail = usernameOrEmail;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
