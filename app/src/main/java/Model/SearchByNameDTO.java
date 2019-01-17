package Model;

public class SearchByNameDTO
{
    private int ID ;
    private String BarcodeId ;
    private String ProductName ;
    private int ProductGroupId ;
    private String FirstImage ;
    private SearchByNameDTO Result[];

    public SearchByNameDTO[] getResult() {
        return Result;
    }

    public void setResult(SearchByNameDTO[] result) {
        this.Result = result;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getBarcodeId() {
        return BarcodeId;
    }

    public void setBarcodeId(String barcodeId) {
        BarcodeId = barcodeId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getProductGroupId() {
        return ProductGroupId;
    }

    public void setProductGroupId(int productGroupId) {
        ProductGroupId = productGroupId;
    }

    public String getFirstImage() {
        return FirstImage;
    }

    public void setFirstImage(String firstImage) {
        FirstImage = firstImage;
    }
}
