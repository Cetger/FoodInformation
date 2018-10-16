package Model;

import java.util.List;

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

    public Values getValues()
    {
        return this;
    }
}
