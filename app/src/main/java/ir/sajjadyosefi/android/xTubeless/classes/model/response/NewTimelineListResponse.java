package ir.sajjadyosefi.android.xTubeless.classes.model.response;



import java.util.ArrayList;
import java.util.List;

import ir.sajjadyosefi.android.xTubeless.classes.model.post.NewTimelineItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.TimelineItem;

/**
 * Created by sajjad on 1/20/2018.
 */

public class NewTimelineListResponse extends ServerResponseBase {

    List<NewTimelineItem> timelineList = new ArrayList<NewTimelineItem>();

    public List<NewTimelineItem> getTimelineList() {
        return timelineList;
    }

    public void setTimelineList(List<NewTimelineItem> timelineList) {
        this.timelineList = timelineList;
    }
}
