package ir.sajjadyosefi.android.tubeless.classes.model.radyab;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.Date;


/**
 * Created by sajjad on 5/13/2018.
 */

public class CarLogDetails implements Serializable {

    private static final long serialVersionUID = -222864131214757024L;

    public static final String ID = "id";

    @DatabaseField(generatedId = true)
    public int Id;

    @DatabaseField
    public float arzJoghrafi;

    @DatabaseField
    public float tolJoghrafi;

    public String address;

    @DatabaseField
    public Date date;

    @DatabaseField
    public String commnet;

    @DatabaseField
    public int type;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    public SlaveDetails slaveId;

    public static String getID() {
        return ID;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCommnet() {
        return commnet;
    }

    public void setCommnet(String commnet) {
        this.commnet = commnet;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public SlaveDetails getSlaveId() {
        return slaveId;
    }

    public void setSlaveId(SlaveDetails slaveId) {
        this.slaveId = slaveId;
    }

    // Default constructor is needed for the SQLite, so make sure you also have it
    public CarLogDetails(){

    }


}