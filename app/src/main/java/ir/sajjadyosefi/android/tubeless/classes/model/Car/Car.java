package ir.sajjadyosefi.android.tubeless.classes.model.Car;

import java.util.Date;

import ir.sajjadyosefi.android.tubeless.classes.model.Company;

/**
 * Created by sajjad on 7/31/2017.
 */

public class Car {
    private int ID;
    private Company company;
    private CarClass carClass;
    private CarType carType;
    private Date DesignDate;
    private String CompanyCategory;
    private String DescriptionEn;
    private String DescriptionAr;
    private String DescriptionFa;
    private String NameEn;
    private String NameAr;
    private String NameFa;
    private Object price;

    //radyab
    private String mobileNumber;
    private String token;


    public Date getDesignDate() {
        return DesignDate;
    }

    public void setDesignDate(Date designDate) {
        DesignDate = designDate;
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public CarClass getCarClass() {
        return carClass;
    }

    public void setCarClass(CarClass carClass) {
        this.carClass = carClass;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    //public Date getDesignDate() {
    //    return DesignDate;
    //}

    //public void setDesignDate(Date designDate) {
    //    DesignDate = designDate;
    //}

    public String getCompanyCategory() {
        return CompanyCategory;
    }

    public void setCompanyCategory(String companyCategory) {
        CompanyCategory = companyCategory;
    }

    public String getDescriptionEn() {
        return DescriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        DescriptionEn = descriptionEn;
    }

    public String getDescriptionAr() {
        return DescriptionAr;
    }

    public void setDescriptionAr(String descriptionAr) {
        DescriptionAr = descriptionAr;
    }

    public String getDescriptionFa() {
        return DescriptionFa;
    }

    public void setDescriptionFa(String descriptionFa) {
        DescriptionFa = descriptionFa;
    }

    public String getNameEn() {
        return NameEn;
    }

    public void setNameEn(String nameEn) {
        NameEn = nameEn;
    }

    public String getNameAr() {
        return NameAr;
    }

    public void setNameAr(String nameAr) {
        NameAr = nameAr;
    }

    public String getNameFa() {
        return NameFa;
    }

    public void setNameFa(String nameFa) {
        NameFa = nameFa;
    }

    public Object getPrice() {
        return price;
    }

    public void setPrice(Object price) {
        this.price = price;
    }


    public  String getMobileNumber() {
        return mobileNumber;
    }
    public void   setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public String getToken() {
        return token;
    }
    public void   setToken(String token) {
        this.token = token;
    }
}
