package Model;

public class NutritionFacts
{
    private String ID  ;
    private String BarcodeId  ;
    private String LanguageCode;
    private String Energy  ;
    private String Fat  ;
    private String SaturatedFattyAcids  ;
    private String TransFattyAcids  ;
    private String Carbohydrate  ;
    private String Protein  ;
    private String Salt  ;

    public NutritionFacts(String energy, String fat, String saturatedFattyAcids, String transFattyAcids, String carbohydrate, String protein, String salt) {
        Energy = energy;
        Fat = fat;
        SaturatedFattyAcids = saturatedFattyAcids;
        TransFattyAcids = transFattyAcids;
        Carbohydrate = carbohydrate;
        Protein = protein;
        Salt = salt;
    }

    public NutritionFacts(String ID) {
        this.ID = ID;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public void setLanguageCode(String languageCode) {
        LanguageCode = languageCode;
    }

    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public String getEnergy() {
        return Energy;
    }
    public void setEnergy(String energy) {
        Energy = energy;
    }
    public String getFat() {
        return Fat;
    }
    public void setFat(String fat) {
        Fat = fat;
    }
    public String getSaturatedFattyAcids() {
        return SaturatedFattyAcids;
    }
    public void setSaturatedFattyAcids(String saturatedFattyAcids) {
        SaturatedFattyAcids = saturatedFattyAcids;
    }

    public String getTransFattyAcids() {
        return TransFattyAcids;
    }
    public void setTransFattyAcids(String transFattyAcids) {
        TransFattyAcids = transFattyAcids;
    }
    public String getCarbohydrate() {
        return Carbohydrate;
    }
    public void setCarbohydrate(String carbohydrate) {
        Carbohydrate = carbohydrate;
    }
    public String getProtein() {
        return Protein;
    }
    public void setProtein(String protein) {
        Protein = protein;
    }
    public String getSalt() {
        return Salt;
    }
    public void setSalt(String salt) {
        Salt = salt;
    }
    public String getBarcodeId() {
        return BarcodeId;
    }
    public void setBarcodeId(String barcodeId) {
        BarcodeId = barcodeId;
    }

}
