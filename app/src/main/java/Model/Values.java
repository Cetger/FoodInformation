package Model;

import java.util.List;

import Remote.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;

public class Values
{
    private String name;
    private String[] surname;
    private int age;
    class mysecondmodel
    {
        public int notlari;
    }
    private mysecondmodel mysecondmodels[];
    private String BarcodeNumber;

    public String getname() {
        return name;
    }

    public String[] getsurname() {
        return surname;
    }

    public int getage() {
        return age;
    }

    public mysecondmodel[] getMysecondmodels() {
        return mysecondmodels;
    }

}
