package Model;

public class GroupProductDTO
{
    private String [] BarcodeId ;
    private int  ProductGroupId ;
    private GroupProductDTO Result;

    public GroupProductDTO(String[] barcodeId) {
        BarcodeId = barcodeId;
    }

    public String[] getBarcodeId() {
        return BarcodeId;
    }

    public void setBarcodeId(String[] barcodeId) {
        BarcodeId = barcodeId;
    }

    public int getProductGroupId() {
        return ProductGroupId;
    }

    public void setProductGroupId(int productGroupId) {
        ProductGroupId = productGroupId;
    }

    public GroupProductDTO getResult() {
        return Result;
    }

    public void setResult(GroupProductDTO result) {
        Result = result;
    }
}
