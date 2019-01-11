package ir.sajjadyosefi.android.tubeless.classes.model.Car;

/**
 * Created by sajjad on 7/31/2017.
 */

public class CarType {

    private int ID;
    private int CompanyID;
    private String DescriptionFa;
    private String DescriptionAr;
    private String DescriptionEn;
    private String CarTypeName;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCompanyID() {
        return CompanyID;
    }

    public void setCompanyID(int companyID) {
        CompanyID = companyID;
    }

    public String getDescriptionFa() {
        return DescriptionFa;
    }

    public void setDescriptionFa(String descriptionFa) {
        DescriptionFa = descriptionFa;
    }

    public String getDescriptionAr() {
        return DescriptionAr;
    }

    public void setDescriptionAr(String descriptionAr) {
        DescriptionAr = descriptionAr;
    }

    public String getDescriptionEn() {
        return DescriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        DescriptionEn = descriptionEn;
    }

    public String getCarTypeName() {
        return CarTypeName;
    }

    public void setCarTypeName(String carTypeName) {
        CarTypeName = carTypeName;
    }



}
