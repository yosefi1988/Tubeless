package ir.sajjadyosefi.android.tubeless.classes.model;


import java.util.Date;

/**
 * Created by sajjad on 7/29/2017.
 */

public class TimelineItemBase {

    public int ID_TimelineType;
    public int ID_Instance;

    public Date getInsertTime() {
        return InsertTime;
    }

    public void setInsertTime(Date insertTime) {
        InsertTime = insertTime;
    }

    public Date InsertTime;
    public String Title;
    public String Nmae;
    public Object Object;

    public java.lang.Object getObject() {
        return Object;
    }

    public void setObject(java.lang.Object object) {
        Object = object;
    }


    public int getID_TimelineType() {
        return ID_TimelineType;
    }

    public void setID_TimelineType(int ID_TimelineType) {
        this.ID_TimelineType = ID_TimelineType;
    }

    public int getID_Instance() {
        return ID_Instance;
    }

    public void setID_Instance(int ID_Instance) {
        this.ID_Instance = ID_Instance;
    }

//     public Date getInsertTime() {
//         return InsertTime;
//     }
//
//     public void setInsertTime(Date insertTime) {
//         InsertTime = insertTime;
//     }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getNmae() {
        return Nmae;
    }

    public void setNmae(String nmae) {
        Nmae = nmae;
    }

//    public Date getInsertTime() {
//        return InsertTime;
//    }
//
//    public void setInsertTime(Date insertTime) {
//        InsertTime = insertTime;
//    }
}
