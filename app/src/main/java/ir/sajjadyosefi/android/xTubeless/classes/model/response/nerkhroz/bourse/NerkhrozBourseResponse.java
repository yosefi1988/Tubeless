
package ir.sajjadyosefi.android.xTubeless.classes.model.response.nerkhroz.bourse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NerkhrozBourseResponse {

    @Expose
    private NerkhrozBourse[] main = null;

    public NerkhrozBourse[] getMain() {
        return main;
    }

    public void setMain(NerkhrozBourse[] main) {
        this.main = main;
    }



}
