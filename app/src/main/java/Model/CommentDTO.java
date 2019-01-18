package Model;
public class CommentDTO
{
    private String UserComment ;
    private int ProductContentId ;
    private String Username ;
    private String Name ;
    private String Surname ;

    public CommentDTO(String userComment) {
        UserComment = userComment;
    }

    public String getUserComment() {
        return UserComment;
    }

    public void setUserComment(String userComment) {
        UserComment = userComment;
    }

    public int getProductContentId() {
        return ProductContentId;
    }

    public void setProductContentId(int productContentId) {
        ProductContentId = productContentId;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
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
}
