package Model;

public class UpdateCategoryNameDTO
{
    private String OldCategoryName;
    private String NewCategoryName;

    public String getOldCategoryName() {
        return OldCategoryName;
    }

    public void setOldCategoryName(String oldCategoryName) {
        OldCategoryName = oldCategoryName;
    }

    public String getNewCategoryName() {
        return NewCategoryName;
    }

    public void setNewCategoryName(String newCategoryName) {
        NewCategoryName = newCategoryName;
    }
}
