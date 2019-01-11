package ir.sajjadyosefi.android.tubeless.classes.model;

/**
 * Created by sajjad on 7/31/2017.
 */

public class Currency {
    public int ID ;
    public String Name_English;
    public String Name_Code;
    public String Name_Local;
    public String Sign;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName_English() {
        return Name_English;
    }

    public void setName_English(String name_English) {
        Name_English = name_English;
    }

    public String getName_Code() {
        return Name_Code;
    }

    public void setName_Code(String name_Code) {
        Name_Code = name_Code;
    }

    public String getName_Local() {
        return Name_Local;
    }

    public void setName_Local(String name_Local) {
        Name_Local = name_Local;
    }

    public String getSign() {
        return Sign;
    }

    public void setSign(String sign) {
        Sign = sign;
    }


}
