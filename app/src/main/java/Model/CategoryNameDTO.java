package Model;

public class CategoryNameDTO extends BaseDTO
{
    private String ID ;
    private String CategoryName ;
    private String LanguageCode ;
    private CategoryNameDTO Result;

    public CategoryNameDTO getResult() {
        return Result;
    }

    public void setResult(CategoryNameDTO result) {
        Result = result;
    }

    public String getID() {
        return ID;
    }

    public CategoryNameDTO(String categoryName, String languageCode,int userID) {
        CategoryName = categoryName;
        LanguageCode = languageCode;
        super.CreatedUserId = userID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public void setLanguageCode(String languageCode) {
        LanguageCode = languageCode;
    }

    public CategoryNameDTO(String ID) {
        this.ID = ID;
        super.Id =  Integer.valueOf(ID);
    }
}
