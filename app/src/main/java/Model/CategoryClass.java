package Model;

public class CategoryClass
{
    private int ID;
    private String CategoryName;
    private CategoryClass [] Result;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public CategoryClass[] getResult() {
        return Result;
    }

    public void setResult(CategoryClass[] result) {
        Result = result;
    }
}
