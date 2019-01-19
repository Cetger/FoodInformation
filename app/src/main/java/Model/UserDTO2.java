package Model;

public class UserDTO2 extends BaseDTO
{
    private String Name ;
    private String Surname ;
    private String Email ;
    private String Username ;
    private String Password ;
    private UserDTO2[] Result;
    private boolean isAdmin;
    private boolean isModerator;

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isModerator() {
        return isModerator;
    }

    public void setModerator(boolean moderator) {
        isModerator = moderator;
    }

    public UserDTO2[] getResult() {
        return Result;
    }

    public void setResult(UserDTO2[] result) {
        Result = result;
    }

    public UserDTO2(String username,int createduserid) {
        Username = username;
        super.CreatedUserId = createduserid;
    }

    public UserDTO2(String username) {
        Username = username;
    }

    public String getName() {

        return Name;
    }
    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
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
}
