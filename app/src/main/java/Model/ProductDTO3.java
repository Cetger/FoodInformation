package Model;

public class ProductDTO3  extends BaseDTO
{
    public ProductDTO[] getResult() {
        return Result;
    }

    public void setResult(ProductDTO[] result) {
        Result = result;
    }

    private ProductDTO[] Result;
    private String BarcodeId ;
    private String ProductName ;
    private int ProductGroupId ;
    //
    private String FirstImage ;
    private String SecondImage ;
    private String ThirdImage ;
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

    public String getFirstImage() {
        return FirstImage;
    }

    public void setFirstImage(String firstImage) {
        FirstImage = firstImage;
    }

    public ProductDTO3(String barcodeId) {
        BarcodeId = barcodeId;
    }

    public ProductDTO3(String barcodeId, String productName, int productGroupId, String firstImage, String secondImage, String thirdImage, CategoryNameDTO productCategoryDTO) {
        BarcodeId = barcodeId;
        ProductName = productName;
        ProductGroupId = productGroupId;
        FirstImage = firstImage;
        SecondImage = secondImage;
        ThirdImage = thirdImage;
        ProductCategory = productCategoryDTO;
    }

    public String getSecondImage() {
        return SecondImage;
    }

    public void setSecondImage(String secondImage) {
        SecondImage = secondImage;
    }

    public String getThirdImage() {
        return ThirdImage;
    }

    public void setThirdImage(String thirdImage) {
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
