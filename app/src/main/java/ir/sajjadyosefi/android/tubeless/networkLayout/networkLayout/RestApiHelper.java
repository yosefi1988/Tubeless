package ir.sajjadyosefi.android.tubeless.networkLayout.networkLayout;

import android.content.Context;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import ir.sajjadyosefi.android.tubeless.classes.CommonClass;
import ir.sajjadyosefi.android.xTubeless.Fragment.ListFragment;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.Blog.BlogItem;

import ir.sajjadyosefi.android.xTubeless.classes.modelY.Blog.BlogItemListResponse;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.Blog.BlogItemResponse;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.Config.CarCategoriesByCompanyResponse;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.NetworkLayout.response.NewYafteResponse;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.RequestConfirmPayment;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.RequestEstelam;

import ir.sajjadyosefi.android.xTubeless.classes.modelY.UpdateRequest;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.Device;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.Message;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.NetworkLayout.ChangePasswordResponse;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.NetworkLayout.DeviceRegisterResponse;

import ir.sajjadyosefi.android.xTubeless.classes.modelY.NetworkLayout.MessageResponse;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.NetworkLayout.RegisterResponse;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.NetworkLayout.ServerResponseConfig;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.NetworkLayout.TimelineResponse;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.responses.LoginResponse;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.responses.basic.ServerResponseBase;
import ir.sajjadyosefi.android.xTubeless.classes.model.user.User;
import ir.sajjadyosefi.android.tubeless.Global;
import ir.sajjadyosefi.android.tubeless.R;

public class RestApiHelper {
    HttpUrlconnectionHelper httpUrlconnectionHelper = new HttpUrlconnectionHelper();

//    //OK
//    public Object checkMessage(String phoneNumber, String message) {
//        return httpUrlconnectionHelper.PostJsonToServer("" +
//                "phoneNumber=" + phoneNumber +
//                "&message=" + message,
//                Url.POST_SEND_MESSAGE_TO_SERVER);
//    }
    //OK
    public Object changeBlogItemFavouriteStatus(long UserId, int BlogId,boolean NewFavorativeStatus) {
        return httpUrlconnectionHelper.PostJsonToServer(
                Url.POST_CHANGE_BLOG_ITEM_FAVOURITE_STATUS,"" +
                "UserId=" + UserId +
                "&NewFavorativeStatus=" + NewFavorativeStatus +
                "&BlogId=" + BlogId,
                Object.class);
    }
    //OK
    public ServerResponseBase deleteBlogItem(long UserId, int BlogId) {
        return (ServerResponseBase) httpUrlconnectionHelper.PostJsonToServer(
                Url.POST_DELETE_BLOG_ITEM,"" +
                        "LoggedinUserId=" + UserId +
                        "&BlogId=" + BlogId,
                ServerResponseBase.class);
    }


    //OK
    public ServerResponseConfig checkServerConfig(UpdateRequest updateRequest) {

        if (Global.user != null) {
            ServerResponseConfig serverResponseConfig = (ServerResponseConfig) httpUrlconnectionHelper.PostJsonToServer("" +

                            Url.POST_RECEIVE_SERVER_CONFIG2,
                            "androidId=" + updateRequest.deviceDetails.getAndroidId() + "&" +
                            "deviceId=" + updateRequest.deviceDetails.getAndroidId() + "&" +
                            "ApplicationID=17&" +
                            "UserID=" + Global.user.getUserId() + "&" +
                            "currentAppVersion=" + updateRequest.sbankDetails.getVersion(),
                    ServerResponseConfig.class);
            return serverResponseConfig;
        }else {
            ServerResponseConfig serverResponseConfig = (ServerResponseConfig) httpUrlconnectionHelper.PostJsonToServer("" +
                            Url.POST_RECEIVE_SERVER_CONFIG3,
                            "androidId=" + updateRequest.deviceDetails.getAndroidId() + "&" +
                            "deviceId=" + updateRequest.deviceDetails.getAndroidId() + "&" +
                            "ApplicationID=17&" +
                            "UserID=20053&" +
                            "currentAppVersion=" + updateRequest.sbankDetails.getVersion(),
                            ServerResponseConfig.class
                    );
            return serverResponseConfig;
        }
    }

    //OK
    public CarCategoriesByCompanyResponse carCategoriesByCompany() {
        CarCategoriesByCompanyResponse serverResponseConfig = (CarCategoriesByCompanyResponse) httpUrlconnectionHelper.GetDataFromServer(Url.GET_CAR_CATEGORIES_BY_COMPANY,null,CarCategoriesByCompanyResponse .class );

        return serverResponseConfig;
    }

    //OK
    public CarCategoriesByCompanyResponse carCategoriesByCompanyRecently() {
        return carCategoriesByCompanyRecently(0);
    }
    public CarCategoriesByCompanyResponse carCategoriesByCompanyRecently(long userid) {

        CarCategoriesByCompanyResponse serverResponseConfig = (CarCategoriesByCompanyResponse) httpUrlconnectionHelper.PostJsonToServer(Url.GET_CAR_CATEGORIES_BY_COMPANY_RECENTLY,"UserID=" + userid,CarCategoriesByCompanyResponse.class);
        return serverResponseConfig;
    }

    //OK
    public LoginResponse Login(Context context, User user, Device device) {
        LoginResponse aaaaaaaaaaaaaaa = (LoginResponse) httpUrlconnectionHelper.PostJsonToServer(

                        Url.POST_LOGIN,
                        "ApplicationId=17&" +
//                        "phoneNumber=" + user.getLoginPhone() + "&" +
//                        "password=" + user.getLoginPassword()+ "&" +
                        "AndroidId=" + device.getAndroidID(),
                LoginResponse.class);
        return  aaaaaaaaaaaaaaa;
    }


    //OK
    public NewYafteResponse NewYafte(Context context, BlogItem blogItem) {
        NewYafteResponse aaaaaaaaaaaaaaa = (NewYafteResponse) httpUrlconnectionHelper.PostJsonToServer(
                Url.POST_ADD_YAFTE,
                "ApplicationId=17&" +
                        "UserId=" +             blogItem.getUserID() + "&" +
                        "CategoryId=" +         blogItem.getCategoryID()+ "&" +  //16 - 17 - 18
                        "Title=" +              blogItem.getTitle()  + "&" +
                        "TitlePicture=" +       blogItem.getTitlePicture() + "&" +
                        "Statement=" +          blogItem.getStatement() + "&" +
                        "TextPicture=" +        blogItem.getTextPicture() + "&" +
                        "Text=" +               blogItem.getText(),
                        NewYafteResponse.class
                );
        return  aaaaaaaaaaaaaaa;
    }

    //OK
    public RegisterResponse Register(Context context, User user) {
//        RegisterResponse aaaaaaaaaaaaaaa = (RegisterResponse) httpUrlconnectionHelper.PostJsonToServer(
//                        Url.POST_REGISTER,
//                        "ApplicationId=17&" +
////                        "VersionID=" +      user.getDevice().getVersionID() + "&" +
////                        "AndroidID=" +      user.getDevice().getAndroidID()+ "&" +
////                        "SERIAL=" +         user.getDevice().getSERIAL()  + "&" +
////                        "MODEL=" +          user.getDevice().getMODEL() + "&" +
////                        "BuildID=" +        user.getDevice().getBuildID() + "&" +
////                        "AndroidVersion=" + user.getDevice().getAndroidVersion() + "&" +
////                        "AndroidAPI=" +     user.getDevice().getAndroidAPI() + "&" +
////                        "MANUFACTURER=" +   user.getDevice().getMANUFACTURER() + "&" +
////                        "BRAND=" +          user.getDevice().getBRAND() + "&" +
////                        "BOARD=" +          user.getDevice().getBOARD() + "&" +
////                        "DISPLAY=" +        user.getDevice().getDISPLAY() ,
//                //+ "&" +
////                        "phoneNumber=" +    user.getLoginPhone() + "&" +
////                        "MobileNumber=" +   user.getLoginPhone() + "&" +
////                        "Password=" +       user.getLoginPassword(),
//                        RegisterResponse.class
//                );
//        return  aaaaaaaaaaaaaaa;
        return null;
    }
    //OK
    public MessageResponse SendMessage(Context context, Message message) {
        MessageResponse aaaaaaaaaaaaaaa = (MessageResponse) httpUrlconnectionHelper.PostJsonToServer(
                Url.POST_SEND_MESSAGE_TO_SERVER,
                "ApplicationId=17&" +
                        "phoneNumber=" +            message.getPhoneNumber() + "&" +
                        "text=" +                  message.getTitle() + "&" +
                        "text=" +                   message.getText() + "&" +
                        "type=" +                   message.getType(),
                MessageResponse.class
                );
        return  aaaaaaaaaaaaaaa;
    }

    public static final int CAR_PIC = 11;

//    public Object sendImageToServer(Context context ,File file ,int PictureType) {
//        //PictureType: 1 = Car Picture
//        //PictureType: 2 =
//
//        File uploadFile1 = file;
//        try {
//            if (PictureType == CAR_PIC) {
//                MultipartHttpUtils multipart = new MultipartHttpUtils(
//                        Url.POST_ADD_CAR_PICTURE + "user/upload-profile-picture/" //+ userId
//                        , "UTF-8"
//                        , "");
//                multipart.addFormField("core[security_token]", "");
//                multipart.addFilePart("image", uploadFile1);
//                String response = multipart.PostJsonToServer();
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        return null;
//    }

    //OK
    public ChangePasswordResponse ChangePassword(Context context, User user) {
        ChangePasswordResponse aaaaaaaaaaaaaaa = (ChangePasswordResponse) httpUrlconnectionHelper.PostJsonToServer(
                Url.POST_CHANGE_PASSWORD,
                "ApplicationId=17&" +
                        "VersionID=100" + "&" +
                        "phoneNumber=" +           user.getMobileNumber() + "&" +
                        "Password=" +              user.getPassword() + "&" +
                        "OldPassword=" +           user.getPassword()// + "&" +
//                        "NewPassword=" +           user.getLoginPassword() + "&" +
//                        "ConfermPassword=" +       user.getLoginPassword()
                ,
                ChangePasswordResponse.class
                );
        return  aaaaaaaaaaaaaaa;
    }

    //OK
    public DeviceRegisterResponse Register(Context context, Device device) {
        DeviceRegisterResponse aaaaaaaaaaaaaaa = (DeviceRegisterResponse) httpUrlconnectionHelper.PostJsonToServer(
                Url.POST_DEVICE_REGISTER,
                "ApplicationId=17&" +
                        "VersionID=" +      device.getVersionID() + "&" +
                        "AndroidID=" +      device.getAndroidID()+ "&" +
                        "SERIAL=" +         device.getSERIAL()  + "&" +
                        "MODEL=" +          device.getMODEL() + "&" +
                        "BuildID=" +        device.getBuildID() + "&" +
                        "AndroidVersion=" + device.getAndroidVersion() + "&" +
                        "AndroidAPI=" +     device.getAndroidAPI() + "&" +
                        "MANUFACTURER=" +   device.getMANUFACTURER() + "&" +
                        "BRAND=" +          device.getBRAND() + "&" +
                        "BOARD=" +          device.getBOARD() + "&" +
                        "DISPLAY=" +        device.getDISPLAY() ,
                DeviceRegisterResponse.class
                );
        return  aaaaaaaaaaaaaaa;
    }



    @NonNull
    private StringBuilder getStringBuilder(HttpURLConnection httpConn) throws IOException {
        InputStream in = httpConn.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb;
    }


    @NonNull
    private String getStringBuilderToString(HttpURLConnection httpConn) throws IOException {
        StringBuilder stringBuilder = getStringBuilder(httpConn);
        return new String(stringBuilder);
    }

    private boolean CheckResCode(HttpURLConnection httpConn) throws IOException {
        int resCode = httpConn.getResponseCode();
        if (resCode != HttpURLConnection.HTTP_OK) {
            return true;
        }
        return false;
    }

    //OK
    public Object Estelam(RequestEstelam requestEstelam) {
        return httpUrlconnectionHelper.PostJsonToServer(Url.POST_ESTELAM
                ,"" +
                        "AndroidID=" +          requestEstelam.AndroidID + "&" +
                        "AndroidVersion=" +     requestEstelam.AndroidVersion + "&" +

                        "AppID=" +              requestEstelam.AppID + "&" +
                        "AppVersion=" +         requestEstelam.AppVersion + "&" +
                        "BASE_VERSION_CODES=" + requestEstelam.BASE_VERSION_CODES + "&" +
                        "BOARD=" +              requestEstelam.BOARD + "&" +
                        "BRAND=" +              requestEstelam.BRAND + "&" +
                        "BuildID=" +            requestEstelam.BuildID + "&" +
                        "BuildUSER=" +          requestEstelam.BuildUSER + "&" +
                        "FINGERPRINT=" +        requestEstelam.FINGERPRINT + "&" +
                        "HOST=" +               requestEstelam.HOST + "&" +
                        "INCREMENTAL_VERSION=" + requestEstelam.INCREMENTAL_VERSION + "&" +
                        "MANUFACTURER=" +       requestEstelam.MANUFACTURER + "&" +
                        "MODEL=" +              requestEstelam.MODEL + "&" +
                        "RELEASE_VERSION=" +    requestEstelam.RELEASE_VERSION + "&" +
                        "SDK=" +                requestEstelam.SDK + "&" +
                        "SERIAL=" +             requestEstelam.SERIAL + "&" +
                        "DISPLAY=" +            requestEstelam.DISPLAY + "&" +
                        "TYPE=" +               requestEstelam.TYPE ,
                Object.class
                );
    }

    //OK
    public TimelineResponse GetTimeline(Context context, int mIndex) {
//        TimelineResponse aaaaaaaaaaaaaaa = (TimelineResponse) httpUrlconnectionHelper.GetDataFromServer(
////                String.format(Url.POST_RECEIVE_TIMELINE,mIndex) ,"" +
////                        "AppID=" + context.getString(R.string.app_id) + "&" +
////                        "AppVersion=" + CommonClass.GetAppVersion(context),
////                TimelineResponse.class
////                        );
//        return  aaaaaaaaaaaaaaa;


        TimelineResponse timelineResponse = new TimelineResponse();
        timelineResponse.setTimelineBase(ListFragment.createData());
        return timelineResponse;

    }


    public Object ConfirmPayment( RequestConfirmPayment requestConfirmPayment) {
        int a = 5 + 56 ;
        a += 5 ;
        return httpUrlconnectionHelper.PostJsonToServer(
                Url.POST_CONFIRM_PAYMENT,"" +
                        "AndroidID=" +          requestConfirmPayment.getAndroidID() + "&" +
                        "AppID=" +              requestConfirmPayment.getAppID() + "&" +
                        "RefId=" +              requestConfirmPayment.getRefId() + "&" +
                        "Price=" +              requestConfirmPayment.getPrice() + "&" +
                        "Email=" +              requestConfirmPayment.getEmail() + "&" +
                        "phone=" +              requestConfirmPayment.getPhone(),
                Object.class
                );
//        try {
//            URL url = new URL(Url.POST_CONFIRM_PAYMENT);
//            URLConnection urlConn = url.openConnection();
//            HttpURLConnection httpConn = (HttpURLConnection) urlConn;
//            httpConn.setAllowUserInteraction(false);
//            httpConn.setInstanceFollowRedirects(true);
//            httpConn.setDoInput(true);
//            httpConn.setDoOutput(true);
//
//            httpConn.setRequestMethod("POST");
//            OutputStream os = httpConn.getOutputStream();
//
////            System.setProperty("http.proxyHost", "proxy.example.com");
////            System.setProperty("http.proxyPort", "8080");
//
//            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
//
//            String request = null;
//            request = "" +
//                    "AndroidID=" +          requestConfirmPayment.getAndroidID() + "&" +
//                    "AppID=" +              requestConfirmPayment.getAppID() + "&" +
//                    "RefId=" +              requestConfirmPayment.getRefId() + "&" +
//                    "Price=" +              requestConfirmPayment.getPrice() + "&" +
//                    "Email=" +              requestConfirmPayment.getEmail() + "&" +
//                    "phone=" +              requestConfirmPayment.getPhoneNumber() + "&" ;
//
//
//            writer.write(request);
//            writer.flush();
//            writer.close();
//            os.close();
//
//            Gson gson = new Gson();
//
//
//
//            int responseCode = httpConn.getResponseCode(); //can call this instead of con.connect()
//            if (responseCode >= 400 && responseCode <= 499) {
//                Log.i("xxx","Bad authentication status: " + responseCode); //provide a more meaningful exception message
//                return null;
//            }
//            else {
//                StringBuilder sb = getStringBuilder(httpConn);
//                try {
//                    ServerResponseConfig serverResponse = gson.fromJson(sb.toString().substring(0,sb.toString().indexOf("<!DO")), ServerResponseConfig.class);
//                    return  serverResponse;
//                }catch (Exception ex){
//                    ex.printStackTrace();
//                    return null;
//                }
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//            return nxxxxxxxxull;
//        } catch (ProtocolException e) {
//            e.printStackTrace();
//            return null;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }

    }

    public BlogItemListResponse GetBlogList(Context mContext, int mIndex, int idHeader) {
        if(idHeader != 0) {
            BlogItemListResponse aaaaaaaaaaaaaaa = (BlogItemListResponse) httpUrlconnectionHelper.GetDataFromServer(String.format(Url.GET_RECEIVE_BLOG_ITEMS, idHeader, mIndex)
                    , "" +
                            "AppID=" + mContext.getString(R.string.app_id) + "&" +
                            "AppVersion=" + CommonClass.GetAppVersion(mContext),
                    BlogItemListResponse.class
                    );
            return aaaaaaaaaaaaaaa;
        }else {
            BlogItemListResponse aaaaaaaaaaaaaaa = (BlogItemListResponse) httpUrlconnectionHelper.GetDataFromServer(
                    String.format(Url.GET_RECEIVE_BLOG_ITEMS_NEW, mIndex), "" +
                            "AppID=" + mContext.getString(R.string.app_id) + "&" +
                            "AppVersion=" + CommonClass.GetAppVersion(mContext),
                    BlogItemListResponse.class
                    );
            return aaaaaaaaaaaaaaa;
        }
    }

    public BlogItemListResponse GetSearchYafteResult(Context mContext, String term, int idHeader) {
        BlogItemListResponse aaaaaaaaaaaaaaa = (BlogItemListResponse) httpUrlconnectionHelper.GetDataFromServer(
                String.format(Url.GET_SEARCH_YAFTE, term,idHeader), "" +
                        "AppID=" + mContext.getString(R.string.app_id) + "&" +
                        "AppVersion=" + CommonClass.GetAppVersion(mContext),
                BlogItemListResponse.class
                );
        return aaaaaaaaaaaaaaa;
    }

    public BlogItemResponse GetBlog(Context mContext, int idBlog) {
        BlogItemResponse aaaaaaaaaaaaaaa = (BlogItemResponse) httpUrlconnectionHelper.GetDataFromServer(
                String.format(Url.GET_RECEIVE_BLOG_ITEM,idBlog,49),
                "" +
                        "blogid=" + idBlog + "&" +
                        "LoggedinUserId=" + idBlog,
                BlogItemResponse.class
                );
        return  aaaaaaaaaaaaaaa;
    }

//    public Comments getComments(String cookie, String pageId, String ItemId, String pageNumber, String typeid) {
//
//        try {
//
//            //URL url = new URL(WebConf.MAIN_URL + "pages/comments/" + pageId + "/" + feedId + "/" + pageNumber);
//            URL url = new URL(String.format(WebConf_v2.GET_COMMENTS , typeid , ItemId , pageNumber ));
//
//
//            URLConnection urlConn = url.openConnection();
//
//            HttpURLConnection httpConn = (HttpURLConnection) urlConn;
//            httpConn.setAllowUserInteraction(false);
//            httpConn.setInstanceFollowRedirects(true);
//            /*httpConn.setDoInput(true);
//            httpConn.setDoOutput(true);*/
//
//            httpConn.setRequestProperty("User-Agent", Statics.USER_AGENT);
////            httpConn.setRequestProperty("Host", "localhost");
//            httpConn.setRequestProperty("Cookie", cookie);
//
//            httpConn.setRequestMethod("GET");
//            httpConn.connect();
//
//            if (CheckResCode(httpConn)) return null;
//
//            StringBuilder sb = getStringBuilder(httpConn);
//
//            Log.e("Comment List", sb.toString());
//
//            Comments comments = new Comments();
//            Gson gson = new Gson();
//            comments = gson.fromJson(sb.toString(),Comments.class);
//            if (comments.getComments().size() > 0)
//                return comments;
//            else
//                return  new Comments();
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (ProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//
//    }
}
