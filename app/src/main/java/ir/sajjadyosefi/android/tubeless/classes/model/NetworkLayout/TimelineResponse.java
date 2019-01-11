package ir.sajjadyosefi.android.tubeless.classes.model.NetworkLayout;

import java.util.List;

import ir.sajjadyosefi.android.tubeless.classes.model.TimelineItemBase;

/**
 * Created by sajjad on 3/2/2017.
 */
public class TimelineResponse extends ServerResponseBase {

    public List<TimelineItemBase> TimelineBase ;
//    public List<TimelineItem> Items;

    public List<TimelineItemBase> getTimelineBase() {
        return TimelineBase;
    }

    public void setTimelineBase(List<TimelineItemBase> timelineBase) {
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

