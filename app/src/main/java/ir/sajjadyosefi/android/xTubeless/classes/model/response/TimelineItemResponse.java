package ir.sajjadyosefi.android.xTubeless.classes.model.response;


import ir.sajjadyosefi.android.xTubeless.classes.model.post.NewTimelineItem;

/**
 * Created by sajjad on 1/20/2018.
 */


public class TimelineItemResponse extends ServerResponseBase {

    public NewTimelineItem timelineItem ;

    public NewTimelineItem getTimelineItem() {
        return timelineItem;
    }

    public void setTimelineItem(NewTimelineItem timelineItem) {
        this.timelineItem = timelineItem;
    }


}
