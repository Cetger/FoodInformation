package Model;

public class ModeratorDTO extends BaseDTO
{
    private String Username ;
    private boolean IsModerator ;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public boolean isModerator() {
        return IsModerator;
    }

    public void setModerator(boolean moderator) {
        IsModerator = moderator;
    }
}