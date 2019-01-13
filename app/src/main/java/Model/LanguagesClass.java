package Model;

public class LanguagesClass
{
    private int ID;
    private String LanguageCode;
    private String LanguageName;
    private String NativeLanguageName;
    private LanguagesClass[] Result;

    public LanguagesClass(String languageCode) {
        LanguageCode = languageCode;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public void setLanguageCode(String languageCode) {
        LanguageCode = languageCode;
    }

    public String getLanguageName() {
        return LanguageName;
    }

    public void setLanguageName(String languageName) {
        LanguageName = languageName;
    }

    public String getNativeLanguageName() {
        return NativeLanguageName;
    }

    public void setNativeLanguageName(String nativeLanguageName) {
        NativeLanguageName = nativeLanguageName;
    }

    public LanguagesClass[] getResult() {
        return Result;
    }

    public void setResult(LanguagesClass[] result) {
        Result = result;
    }
}
