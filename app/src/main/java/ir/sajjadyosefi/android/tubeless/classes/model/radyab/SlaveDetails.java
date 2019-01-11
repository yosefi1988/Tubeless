package ir.sajjadyosefi.android.tubeless.classes.model.radyab;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;


/**
 * Created by sajjad on 5/13/2018.
 */

public class SlaveDetails implements Serializable {

    private static final long serialVersionUID = -222864131214757024L;


    @DatabaseField(generatedId = true)
    public int slaveId;

    @DatabaseField
    public String carName;

    @DatabaseField
    public String carPicture;

    @DatabaseField
    public String shomareShasi;

    @DatabaseField
    public String shomareMotor;

    @DatabaseField
    public String shomarePelak;

    @DatabaseField
    public float arzJoghrafi;

    @DatabaseField
    public float tolJoghrafi;

    @DatabaseField
    public String slavePushNotificationToken;

    @DatabaseField
    public String slavePhoneNumber;

    @DatabaseField
    public boolean isSlaveTokenValid;

    @DatabaseField
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