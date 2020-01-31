package ir.sajjadyosefi.android.xTubeless.classes.modelY.request.files;


import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.accounting.LoginRequest;

public class SendFileRequest extends LoginRequest {

    private String serialRequestCode;
    private String fileType;
    private String senderType;

    public SendFileRequest(String userName, String password, String androidID) {
        super(userName, password, androidID);
    }

//    public SendFileRequest() {
//        super();
//    }


    public String getSerialRequestCode() {
        return serialRequestCode;
    }

    public void setSerialRequestCode(String serialRequestCode) {
        this.serialRequestCode = serialRequestCode;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getSenderType() {
        return senderType;
    }

    public void setSenderType(String senderType) {
        this.senderType = senderType;
    }

}
