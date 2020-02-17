package ir.sajjadyosefi.android.xTubeless.classes.modelY;

import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;


/**
 * Created by sajjad on 12/22/2016.
 */
public class UpdateRequest{

    Context context;
    public SbankDetails sbankDetails ;
    public DeviceDetails deviceDetails ;


    public UpdateRequest(Context context) {
        this.context = context;
        this.sbankDetails = new SbankDetails();
        this.deviceDetails = new DeviceDetails();

        //init sbankDetails;
        sbankDetails.installDate = "xx/xx/xx";

        ////////////////////////////////////////////////////version//////////////////////////////////////////////////
//        sbankDetails.version = CommonClass.GetAppVersion(context);

        //F
        sbankDetails.countOfRunSbank = -1;
        //Q
        sbankDetails.bankAccountsCount = -1;
        //Q
        sbankDetails.transactionsCount = -1 ;
        //F
        sbankDetails.maxOfCountTransactions = -1 ;
        //Q
        sbankDetails.sumInTransaction = -1 ;
        //Q
        sbankDetails.sumOutTransaction = -1 ;

        //////////////////////////////////////////////////init deviceDetails//////////////////////////////////////////////////

        //androidId
        deviceDetails.androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        //p
        deviceDetails.androidVersion = "1" ;

        //imei
//        deviceDetails.imei = ((TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
//        deviceDetails.imei = "";
        deviceDetails.phoneNumber = "1" ;

        //P
        deviceDetails.model = "1" ;
        deviceDetails.manufacturer = "1" ;
        deviceDetails.pushToken = "1" ;
    }

    public class SbankDetails {

        String installDate;
        String version ;
        int countOfRunSbank;
        int bankAccountsCount;
        int transactionsCount;
        int maxOfCountTransactions;
        float sumInTransaction;
        float sumOutTransaction;

        public String getInstallDate() {
            return installDate;
        }

        public void setInstallDate(String installDate) {
            this.installDate = installDate;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public int getCountOfRunSbank() {
            return countOfRunSbank;
        }

        public void setCountOfRunSbank(int countOfRunSbank) {
            this.countOfRunSbank = countOfRunSbank;
        }

        public int getBankAccountsCount() {
            return bankAccountsCount;
        }

        public void setBankAccountsCount(int bankAccountsCount) {
            this.bankAccountsCount = bankAccountsCount;
        }

        public int getTransactionsCount() {
            return transactionsCount;
        }

        public void setTransactionsCount(int transactionsCount) {
            this.transactionsCount = transactionsCount;
        }

        public int getMaxOfCountTransactions() {
            return maxOfCountTransactions;
        }

        public void setMaxOfCountTransactions(int maxOfCountTransactions) {
            this.maxOfCountTransactions = maxOfCountTransactions;
        }

        public float getSumInTransaction() {
            return sumInTransaction;
        }

        public void setSumInTransaction(float sumInTransaction) {
            this.sumInTransaction = sumInTransaction;
        }

        public float getSumOutTransaction() {
            return sumOutTransaction;
        }

        public void setSumOutTransaction(float sumOutTransaction) {
            this.sumOutTransaction = sumOutTransaction;
        }

    }

    public class DeviceDetails {

        String androidId ;
        String androidVersion ;
        String imei ;
        String phoneNumber;
        String model;
        String manufacturer;
        String pushToken;

        public String getAndroidId() {
            return androidId;
        }

        public void setAndroidId(String androidId) {
            this.androidId = androidId;
        }

        public String getAndroidVersion() {
            return androidVersion;
        }

        public void setAndroidVersion(String androidVersion) {
            this.androidVersion = androidVersion;
        }

        public String getImei() {
            return imei;
        }

        public void setImei(String imei) {
            this.imei = imei;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getManufacturer() {
            return manufacturer;
        }

        public void setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
        }

        public String getPushToken() {
            return pushToken;
        }

        public void setPushToken(String pushToken) {
            this.pushToken = pushToken;
        }

    }
}
