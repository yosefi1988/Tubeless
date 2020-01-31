package ir.sajjadyosefi.android.xTubeless.classes.modelY.radyab;

import java.io.Serializable;


/**
 * Created by sajjad on 5/13/2018.
 */

public class SlaveDetails implements Serializable {

    private static final long serialVersionUID = -222864131214757024L;


    public int slaveId;


    public String carName;


    public String carPicture;


    public String shomareShasi;


    public String shomareMotor;


    public String shomarePelak;


    public float arzJoghrafi;


    public float tolJoghrafi;


    public String slavePushNotificationToken;


    public String slavePhoneNumber;


    public boolean isSlaveTokenValid;


    public boolean isDefault;

    public void setSlavePhoneNumber(String slavePhoneNumber) {
        this.slavePhoneNumber = slavePhoneNumber;
    }

    public boolean isSlaveTokenValid() {
        return isSlaveTokenValid;
    }

    public void setSlaveTokenValid(boolean slaveTokenValid) {
        isSlaveTokenValid = slaveTokenValid;
    }

    public int getSlaveId() {
        return slaveId;
    }

    public void setSlaveId(int slaveId) {
        this.slaveId = slaveId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarPicture() {
        return carPicture;
    }

    public void setCarPicture(String carPicture) {
        this.carPicture = carPicture;
    }

    public String getShomareShasi() {
        return shomareShasi;
    }

    public void setShomareShasi(String shomareShasi) {
        this.shomareShasi = shomareShasi;
    }

    public String getShomareMotor() {
        return shomareMotor;
    }

    public void setShomareMotor(String shomareMotor) {
        this.shomareMotor = shomareMotor;
    }

    public String getShomarePelak() {
        return shomarePelak;
    }

    public void setShomarePelak(String shomarePelak) {
        this.shomarePelak = shomarePelak;
    }

    public float getArzJoghrafi() {
        return arzJoghrafi;
    }

    public void setArzJoghrafi(float arzJoghrafi) {
        this.arzJoghrafi = arzJoghrafi;
    }

    public float getTolJoghrafi() {
        return tolJoghrafi;
    }

    public void setTolJoghrafi(float tolJoghrafi) {
        this.tolJoghrafi = tolJoghrafi;
    }

    // Default constructor is needed for the SQLite, so make sure you also have it
    public SlaveDetails(){

    }

    public String getSlavePushNotificationToken() {
        return slavePushNotificationToken;
    }

    public void setSlavePushNotificationToken(String slavePushNotificationToken) {
        this.slavePushNotificationToken = slavePushNotificationToken;
    }

    public String getSlavePhoneNumber() {
        return slavePhoneNumber;
    }


    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }


}