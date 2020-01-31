package ir.sajjadyosefi.android.xTubeless.classes.modelY;


import java.util.Date;

import ir.sajjadyosefi.android.xTubeless.classes.modelY.Car.Car;


public class PriceItem {
    public Car car;
    public PriceType priceType;



    public Currency currency;
    public Country country;

    public Date getModelDate() {
        return modelDate;
    }

    public void setModelDate(Date modelDate) {
        this.modelDate = modelDate;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public Date modelDate;
    public Date insertDate;
    public String optons;
    public String carPrice;
    public String PriceChange;
    public boolean Showen;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public PriceType getPriceType() {
        return priceType;
    }

    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
    }


    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

//    public Date getModelDate() {
//        return modelDate;
//    }
//
//    public void setModelDate(Date modelDate) {
//        this.modelDate = modelDate;
//    }
//
//    public Date getInsertDate() {
//        return insertDate;
//    }
//
//    public void setInsertDate(Date insertDate) {
//        this.insertDate = insertDate;
//    }

    public String getOptons() {
        return optons;
    }

    public void setOptons(String optons) {
        this.optons = optons;
    }

    public String getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(String carPrice) {
        this.carPrice = carPrice;
    }

    public String getPriceChange() {
        return PriceChange;
    }

    public void setPriceChange(String priceChange) {
        PriceChange = priceChange;
    }

    public boolean isShowen() {
        return Showen;
    }

    public void setShowen(boolean showen) {
        Showen = showen;
    }




}
