package Model;

public class LoginClass extends BaseDTO
{
    private String Username;
    private String Password;
    private String Email;
    private LoginClass Result;
    private boolean IsAdmin;
    private boolean IsModerator;

    public boolean isAdmin() {
        return IsAdmin;
    }

    public void setAdmin(boolean admin) {
        IsAdmin = admin;
    }

    public boolean isModerator() {
        return IsModerator;
    }

    public void setModerator(boolean moderator) {
        IsModerator = moderator;
    }

    public LoginClass getResult() {
        return Result;

    }

    public void setResult(LoginClass result) {
        Result = result;
    }

    public LoginClass(String password, String username, String email) {
        Username = username;
        Password = password;
        Email = email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
