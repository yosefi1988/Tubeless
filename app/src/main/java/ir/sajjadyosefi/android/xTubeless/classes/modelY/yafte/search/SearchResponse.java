package ir.sajjadyosefi.android.xTubeless.classes.modelY.yafte.search;

import java.util.List;

public class SearchResponse {


    private String success;
    private String type;
    private String message;


    private List<SearchResultObject> data;

    public SearchResponse(String fName, String lName, String fatherName) {

    }


    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public List<SearchResultObject> getData() {
        return data;
    }

    public void setData(List<SearchResultObject> data) {
        this.data = data;
    }

}
