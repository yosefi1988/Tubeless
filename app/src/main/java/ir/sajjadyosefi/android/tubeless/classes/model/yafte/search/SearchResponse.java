package ir.sajjadyosefi.android.tubeless.classes.model.yafte.search;

import java.util.List;

public class SearchResponse {


    private String success;
    private String type;
    private String message;


    private List<SearchResultObject> data;

//    private String fName;
//    private String lName;
//    private String fatherName;
//    private String nationalCode;
//    private String type;


    public SearchResponse(String fName, String lName, String fatherName) {
//        this.fName = fName;
//        this.lName = lName;
//        this.fatherName = fatherName;
    }

//    public SearchResponse(String nationalCode) {
//        this.nationalCode = nationalCode;
//    }
//
//    public String getfName() {
//        return fName;
//    }
//
//    public void setfName(String fName) {
//        this.fName = fName;
//    }
//
//    public String getlName() {
//        return lName;
//    }
//
//    public void setlName(String lName) {
//        this.lName = lName;
//    }
//
//    public String getFatherName() {
//        return fatherName;
//    }
//
//    public void setFatherName(String fatherName) {
//        this.fatherName = fatherName;
//    }
//
//    public String getNationalCode() {
//        return nationalCode;
//    }
//
//    public void setNationalCode(String nationalCode) {
//        this.nationalCode = nationalCode;
//    }



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
