package Model;

public class CategoryNameDTO
{
    public String ID ;
    public String CategoryName ;
    public String LanguageCode ;

    public String getID() {
        return ID;
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
    }
}
