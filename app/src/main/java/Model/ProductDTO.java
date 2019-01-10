package Model;

public class ProductDTO  extends BaseDTO
{
    private ProductDTO Result;
    private String BarcodeId ;
    private String ProductName ;
    private int ProductGroupId ;

    public ProductDTO getResult() {
        return Result;
    }

    public void setResult(ProductDTO result) {
        Result = result;
    }

    //
    private byte[] FirstImage ;
    private byte[] SecondImage ;
    private byte[] ThirdImage ;
    private CategoryNameDTO ProductCategory;
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

    public byte[] getFirstImage() {
        return FirstImage;
    }

    public void setFirstImage(byte[] firstImage) {
        FirstImage = firstImage;
    }

    public ProductDTO(String barcodeId) {
        BarcodeId = barcodeId;
    }

    public ProductDTO(String barcodeId, String productName, int productGroupId, byte[] firstImage, byte[] secondImage, byte[] thirdImage, CategoryNameDTO productCategoryDTO) {
        BarcodeId = barcodeId;
        ProductName = productName;
        ProductGroupId = productGroupId;
        FirstImage = firstImage;
        SecondImage = secondImage;
        ThirdImage = thirdImage;
        ProductCategory = productCategoryDTO;
    }

    public byte[] getSecondImage() {
        return SecondImage;
    }

    public void setSecondImage(byte[] secondImage) {
        SecondImage = secondImage;
    }

    public byte[] getThirdImage() {
        return ThirdImage;
    }

    public void setThirdImage(byte[] thirdImage) {
        ThirdImage = thirdImage;
    }

    public CategoryNameDTO getProductCategoryDTO() {
        return ProductCategory;
    }

    public void setProductCategoryDTO(CategoryNameDTO productCategoryDTO) {
        ProductCategory = productCategoryDTO;
    }

    // public ICollection<ProductContent> ProductContents ;
    //

}
