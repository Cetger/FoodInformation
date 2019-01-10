package Model;

public class BarcodeDTO
{
    public String BarcodeId ;

    public BarcodeDTO(String barcodeId) {
        BarcodeId = barcodeId;
    }

    public String getBarcodeId() {

        return BarcodeId;
    }

    public void setBarcodeId(String barcodeId) {
        BarcodeId = barcodeId;
    }
}
