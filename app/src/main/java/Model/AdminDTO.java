package Model;

public class AdminDTO extends BaseDTO
{
    private String Username ;
    private boolean IsAdmin ;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public boolean isAdmin() {
        return IsAdmin;
    }

    public void setAdmin(boolean admin) {
        IsAdmin = admin;
    }
}
