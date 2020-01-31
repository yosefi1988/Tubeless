package ir.sajjadyosefi.android.xTubeless.classes.modelY.NetworkLayout;

import java.util.List;

import ir.sajjadyosefi.android.xTubeless.classes.modelY.post.IItems;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.responses.basic.ServerResponseBase;

/**
 * Created by sajjad on 3/2/2017.
 */
public class TimelineResponse extends ServerResponseBase {

    public List<IItems> TimelineBase ;
//    public List<TimelineItem> Items;

    public List<IItems> getTimelineBase() {
        return TimelineBase;
    }

    public void setTimelineBase(List<IItems> timelineBase) {
        TimelineBase = timelineBase;
    }


//    public List<TimelineItem> getItems() {
//        return Items;
//    }

//    public void setItems(List<TimelineItem> items) {
//        Items = items;
//    }

//    public java.lang.Object getObject() {
//        return Object;
//    }
//
//    public void setObject(java.lang.Object object) {
//        Object = object;
//    }

}

