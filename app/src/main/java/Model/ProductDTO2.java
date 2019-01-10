package Model;

import android.text.Editable;

public class ProductDTO2
{
    private String BARCODE;
    private String LanguageId;
    public String getBARCODE() {
        return BARCODE;
    }

    public void setBARCODE(String BARCODE) {
        this.BARCODE = BARCODE;
    }

    public String getLanguageId() {
        return LanguageId;
    }

    public void setLanguageId(String languageId) {
        LanguageId = languageId;
    }

    public ProductDTO2(String BARCODE, String languageId) {
        this.BARCODE = BARCODE;
        this.LanguageId = languageId;
    }
}
