package ir.sajjadyosefi.android.tubeless.classes.model;

/**
 * Created by sajjad on 7/31/2017.
 */

public class Country {
    private int ID;
    private String NameEnglish;
    private String NameCode;
    private String NameLocal;

    public String getNameEnglish() {
        return NameEnglish;
    }

    public void setNameEnglish(String nameEnglish) {
        NameEnglish = nameEnglish;
    }

    public String getNameCode() {
        return NameCode;
    }

    public void setNameCode(String nameCode) {
        NameCode = nameCode;
    }

    public String getNameLocal() {
        return NameLocal;
    }

    public void setNameLocal(String nameLocal) {
        NameLocal = nameLocal;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }


}
