package ir.sajjadyosefi.android.xTubeless.classes.modelY.responses.blog;


import ir.sajjadyosefi.android.xTubeless.classes.modelY.main.TimelineItem;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.responses.basic.ServerResponseBase;

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
