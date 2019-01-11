package ir.sajjadyosefi.android.tubeless.classes.networkLayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import ir.sajjadyosefi.android.tubeless.classes.JsonDateDeserializer;
import ir.sajjadyosefi.android.tubeless.classes.model.Blog.BlogItemListResponse;
import ir.sajjadyosefi.android.tubeless.classes.model.Blog.BlogItemResponse;
import ir.sajjadyosefi.android.tubeless.classes.model.Config.CarCategoriesByCompanyResponse;
import ir.sajjadyosefi.android.tubeless.classes.model.NetworkLayout.ChangePasswordResponse;
import ir.sajjadyosefi.android.tubeless.classes.model.NetworkLayout.DeviceRegisterResponse;
import ir.sajjadyosefi.android.tubeless.classes.model.NetworkLayout.LoginResponse;
import ir.sajjadyosefi.android.tubeless.classes.model.NetworkLayout.MessageResponse;
import ir.sajjadyosefi.android.tubeless.classes.model.NetworkLayout.RegisterResponse;
import ir.sajjadyosefi.android.tubeless.classes.model.NetworkLayout.ServerResponseBase;
import ir.sajjadyosefi.android.tubeless.classes.model.NetworkLayout.ServerResponseConfig;
import ir.sajjadyosefi.android.tubeless.classes.model.NetworkLayout.TimelineResponse;
import ir.sajjadyosefi.android.tubeless.classes.model.NetworkLayout.response.NewYafteResponse;
import ir.sajjadyosefi.android.tubeless.classes.model.radyab.response.GooglePushResponse;

/**
 * Created by sajjad on 7/16/2017.
 */

 public class HttpUrlconnectionHelper implements INetwork{


    //request = "phoneNumber=" + phoneNumber + "&message=" + message
    @Override
    public Object PostJsonToServer(String url ,String request, Class returnClass) {
        URL _url = null;
        HttpURLConnection httpConn = null;
        URLConnection urlConn = null;
        Object serverResponse = null;

        try {
            _url = new URL(url);
            urlConn = _url.openConnection();
            httpConn = (HttpURLConnection) urlConn;

            if(urlConn != null && httpConn != null) {
                httpConn.setAllowUserInteraction(false);
                httpConn.setInstanceFollowRedirects(true);
                httpConn.setDoInput(true);
                httpConn.setDoOutput(true);
                httpConn.setRequestMethod("POST");
            }

            OutputStream os = httpConn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

            writer.write(request);
            writer.flush();
            writer.close();
            os.close();


            int status = httpConn.getResponseCode();
            InputStream in;
            int aaaaaaaaaaa ;
            String aaaaaaaaaaaa;
            if(status >= HttpURLConnection.HTTP_OK) {
                in = httpConn.getErrorStream();
            }else
                in = httpConn.getInputStream();



            //Gson gson = new Gson();
            Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDateDeserializer()).create();
            StringBuilder sb = getStringBuilder(httpConn);

            serverResponse = gson.fromJson(sb.toString(), returnClass);

            int a = 5 + 56 ;
            a += 5 ;
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serverResponse;
    }

    @Override
    public Object PostRequestToFCM (String json, Class tClass) {
        try {
            HttpURLConnection httpcon = (HttpURLConnection) ((new URL("https://fcm.googleapis.com/fcm/send").openConnection()));
            httpcon.setDoOutput(true);
            httpcon.setRequestProperty("Content-Type", "application/json");
            //key = Slave Project key
            httpcon.setRequestProperty ("Authorization", "key=" + Url.SLAVE_FCM_KEY);

            httpcon.setRequestMethod("POST");
            httpcon.connect();
            System.out.println("Connected!");

            byte[] outputBytes = json.getBytes("UTF-8");
            OutputStream os = httpcon.getOutputStream();
            os.write(outputBytes);
            os.close();



            int responseCode = httpcon.getResponseCode(); //can call this instead of con.connect()
            if (responseCode >= 400 && responseCode <= 499) {
                //throw new Exception("Bad authentication status: " + responseCode); //provide a more meaningful exception message
            }
            else {
                // Reading response
                InputStream input = httpcon.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(input);
                String result = null;
                GooglePushResponse googlePushResponse = new GooglePushResponse();
                try {
                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    for (String line; (line = reader.readLine()) != null; ) {
                        System.out.println(line);
                        result = line;
                        System.out.println(result);
                    }
                    System.out.println(result);
                } catch (Exception ex) {

                }

                Gson gson = new Gson();
                googlePushResponse = gson.fromJson(result, GooglePushResponse.class);
                System.out.println("Http POST request sent!");
                return googlePushResponse;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object PostPictureToServer(String url ,String json, Class tClass) {
        return null;
    }

    @Override
    public Object GetDataFromServer(String url ,String requestUrl, Class tClass) {
        URL _url = null;
        HttpURLConnection httpConn = null;
        URLConnection urlConn = null;
        Object serverResponse = null;

        try {
            _url = new URL(url);
            urlConn = _url.openConnection();
            httpConn = (HttpURLConnection) urlConn;

            if(urlConn != null && httpConn != null) {
//                httpConn.setAllowUserInteraction(false);
//                httpConn.setInstanceFollowRedirects(true);
//                httpConn.setDoInput(true);
//                httpConn.setDoOutput(true);
                httpConn.setRequestMethod("GET");

//            httpConn.setRequestProperty("User-Agent", Statics.USER_AGENT);
//            httpConn.setRequestProperty("Host", "localhost");
//            httpConn.setRequestProperty("Cookie", cookie);
            }

            httpConn.connect();
            if (CheckResCode(httpConn)) return null;
            StringBuilder sb = getStringBuilder(httpConn);
            //Gson gson = new Gson();
            Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDateDeserializer()).create();


//            OutputStream os = httpConn.getOutputStream();
//            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
//
//            writer.write(request);
//            writer.flush();
//            writer.close();
//            os.close();
//
//
//            int status = httpConn.getResponseCode();
//            InputStream in;
//            if(status >= HttpURLConnection.HTTP_OK)
//                in = httpConn.getErrorStream();
//            else
//                in = httpConn.getInputStream();

            serverResponse = gson.fromJson(sb.toString() ,tClass);

            int a = 5 + 56 ;
            a += 5 ;
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serverResponse;

    }



//    private static boolean GetDocType(String url) {
////        if(url.contains("timeline/mainlist") || url.contains("/estelam"))
////            return true;
////        else
////            return false;
//
//        return true;
//    }

    private static boolean CheckResCode(HttpURLConnection httpConn) throws IOException {
        int resCode = httpConn.getResponseCode();
        if (resCode != HttpURLConnection.HTTP_OK) {
            return true;
        }
        return false;
    }

//    private static String ClearHtmlElements(String serverString ) {
//        if(isJson) {
//            //Aval json badesh html
//            String tmp = null;
//            Pattern pattern = Pattern.compile("(\\{.*\\:\\{.*\\:.*\\}\\})");
//            Matcher matcher = pattern.matcher(serverString);
//            if (matcher.find()) {
//                tmp = matcher.group(1);
//            }
//            return tmp;
//        }else {
//            //json Dakhele html
//            if (serverString.contains("!DOCTYPE")) {
//                Document doc = Jsoup.parse(serverString);
//                Elements elements = doc.select("body").first().children();
//                String clearText = doc.select("div").first().text();
//                return clearText;
//
//            } else
//                return serverString;
//        }
//    }

    private static Type GetType(String url) {
        if(url.equals(Url.POST_RECEIVE_SERVER_CONFIG3)||url.equals(Url.POST_RECEIVE_SERVER_CONFIG2))
            return ServerResponseConfig.class;
        if(url.equals(Url.POST_ESTELAM))
            return ServerResponseConfig.class;

        if(url.equals(Url.POST_LOGIN))
            return LoginResponse.class;
        if(url.equals(Url.POST_ADD_YAFTE))
            return NewYafteResponse.class;

        if(url.equals(Url.POST_REGISTER))
            return RegisterResponse.class;
        if(url.equals(Url.POST_DEVICE_REGISTER))
            return DeviceRegisterResponse.class;
        if(url.equals(Url.POST_CHANGE_PASSWORD))
            return ChangePasswordResponse.class;
        if(url.equals(Url.POST_SEND_MESSAGE_TO_SERVER))
            return MessageResponse.class;
        if(url.equals(Url.POST_CHANGE_BLOG_ITEM_FAVOURITE_STATUS))
            return ServerResponseBase.class;

        if(url.equals(Url.GET_CAR_CATEGORIES_BY_COMPANY) || url.equals(Url.GET_CAR_CATEGORIES_BY_COMPANY_RECENTLY))
            return CarCategoriesByCompanyResponse.class;


        try {
            if (url.substring(0, 45).contains(Url.GET_RECEIVE_BLOG_ITEMS.substring(0, 45)))
                return BlogItemListResponse.class;

            if (url.substring(0, 45).contains(Url.GET_SEARCH_YAFTE.substring(0, 45)))
                return BlogItemListResponse.class;
        }catch (Exception e){}

        try {
            if(url.substring(0,45).contains(Url.GET_RECEIVE_BLOG_ITEM.substring(0,45)))
            return BlogItemResponse.class;
        }catch (Exception e){}


        //if(url.equals(Url.POST_RECEIVE_TIMELINE))
        if(url.contains("timeline/mainlist"))
            return TimelineResponse.class;
        else
            return null;
    }

    @NonNull
    public static StringBuilder getStringBuilder(HttpURLConnection httpConn) throws IOException {
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



}
