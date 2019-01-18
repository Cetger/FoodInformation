package Model;
public class CommentDTO extends BaseDTO
{
    private String UserComment ;
    private String ProductContentId ;
    private String Username ;
    private String Name ;
    private String Surname ;

    public CommentDTO(String userComment,String productContentId,int userID) {
        UserComment = userComment;
        ProductContentId=productContentId;
        super.CreatedUserId = userID;
    }

    public String getUserComment() {
        return UserComment;
    }

    public void setUserComment(String userComment) {
        UserComment = userComment;
    }

    public String getProductContentId() {
        return ProductContentId;
    }

    public void setProductContentId(String productContentId) {
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
