package Model;
public class CommentDTO {
    private String UserComment ;
    private int ProductContentId ;
    private ContentDTO[] results;

    public ContentDTO[] getResults() {
        return results;
    }

    public void setResults(ContentDTO[] results) {
        this.results = results;
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
}
