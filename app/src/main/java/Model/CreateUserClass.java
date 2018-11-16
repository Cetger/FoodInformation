package Model;

public class CreateUserClass
{
    private String Name;
    private String Surname;
    private String Email;
    private String Password;
    private String Username;
    private String message;

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

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CreateUserClass(String name, String surname, String email, String password, String username) {
        Name = name;
        Surname = surname;
        Email = email;
        Password = password;
        Username = username;

    }
}
