package ir.sajjadyosefi.android.xTubeless.classes.model.response;



import java.util.ArrayList;
import java.util.List;

import ir.sajjadyosefi.android.xTubeless.classes.model.post.TimelineItem;

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
