package Model;

public class LanguageAndProductDTO
{
    private String BarcodeId ;
    private String LanguageCode;

    public LanguageAndProductDTO(String barcodeId, String languageCode) {
        BarcodeId = barcodeId;
        LanguageCode = languageCode;
    }

    public String getBarcodeId() {
        return BarcodeId;
    }

    public void setBarcodeId(String barcodeId) {
        BarcodeId = barcodeId;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public void setLanguageCode(String languageCode) {
        LanguageCode = languageCode;
    }
}
