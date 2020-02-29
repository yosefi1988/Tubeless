package ir.sajjadyosefi.android.xTubeless.classes.model.response;


import ir.sajjadyosefi.android.xTubeless.classes.model.TimelineItem;

/**
 * Created by sajjad on 1/20/2018.
 */


public class TimelineItemResponse extends ServerResponseBase {

    public TimelineItem timelineItem ;

    public TimelineItem getTimelineItem() {
        return timelineItem;
    }

    public void setTimelineItem(TimelineItem timelineItem) {
        this.timelineItem = timelineItem;
    }


}
