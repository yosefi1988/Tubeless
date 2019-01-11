package ir.sajjadyosefi.android.tubeless.classes.model;

import java.util.List;

import ir.sajjadyosefi.android.tubeless.classes.model.Car.Car;

/**
 * Created by sajjad on 7/31/2017.
 */

public class Company {
    private int ID;
    private Country Country;
    private String Logo;
    private String DescriptionEn;
    private String DescriptionAr;
    private String DescriptionFa;
    private String NameEn;
    private String NameAr;
    private String NameFa;
    private String Name;
    private String Description;

    private List<Car> CarList;

    public List<Car> getCarList() {
        return CarList;
    }

    public void setCarList(List<Car> carList) {
        CarList = carList;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public ir.sajjadyosefi.android.tubeless.classes.model.Country getCountry() {
        return Country;
    }

    public void setCountry(ir.sajjadyosefi.android.tubeless.classes.model.Country country) {
        Country = country;
    }

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String logo) {
        Logo = logo;
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

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }






}
