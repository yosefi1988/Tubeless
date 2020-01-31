package ir.sajjadyosefi.android.xTubeless.classes.modelY.responses.blog;


import java.util.ArrayList;
import java.util.List;

import ir.sajjadyosefi.android.xTubeless.classes.modelY.main.TimelineItem;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.responses.basic.ServerResponseBase;

/**
 * Created by sajjad on 1/20/2018.
 */

public class TimelineListResponse extends ServerResponseBase {

    List<TimelineItem> timelineList = new ArrayList<TimelineItem>();

    public List<TimelineItem> getTimelineList() {
        return timelineList;
    }

    public void setTimelineList(List<TimelineItem> timelineList) {
        this.timelineList = timelineList;
    }
}
