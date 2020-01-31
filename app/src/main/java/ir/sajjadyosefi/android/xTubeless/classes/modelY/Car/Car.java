package ir.sajjadyosefi.android.xTubeless.classes.modelY.Car;

import java.util.Date;

import ir.sajjadyosefi.android.xTubeless.classes.modelY.Company;

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
    private String Description;
    private String Name;
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

    public Object getPrice() {
        return price;
    }

    public void setPrice(Object price) {
        this.price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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
