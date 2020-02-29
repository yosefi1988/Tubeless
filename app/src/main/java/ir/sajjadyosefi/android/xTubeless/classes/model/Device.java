package ir.sajjadyosefi.android.xTubeless.classes.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.activity.common.splashScreen.model.IRegisterDeviceModel;
import ir.sajjadyosefi.android.xTubeless.activity.common.splashScreen.presenter.ISplashScreenPeresenter;
//import ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException;
//import ir.sajjadyosefi.android.xTubeless.classes.model.response.TimelineListResponse;
import ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.DeviceRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.TimelineListResponse;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.TubelessRetrofitCallbackss;
import ir.sajjadyosefi.android.xTubeless.utility.DeviceUtil;
import retrofit2.Call;

import static android.content.Context.MODE_PRIVATE;
import static ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException.DEVICE_NOT_REGISTER;


public class Device implements IRegisterDeviceModel {
	Context context;

	private int	ApplicationID;
	private int	VersionID;
	private String AndroidID;
	private String SERIAL;
	private String MODEL;
	private String BuildID;
	private String AndroidVersion;
	private int    AndroidAPI;
	private String MANUFACTURER;
	private String BRAND;
	private String BOARD;
	private String DISPLAY;

	public Device(Context context) {
		this.context = context;

		this.setAndroidID(DeviceUtil.GetAndroidId(context));
		this.setApplicationID(17);                                                                //OK
		this.setVersionID(100);                                                                   //OK
//                this.setBOARD(CommonClass.GetAppVersion(context));                                        //new
		this.setBRAND(Build.BRAND);                                                               //OK
		//this.setBuildID(Build.VERSION.SDK_INT + "-" + Build.VERSION.RELEASE  + "-" + Build.ID);   //new
		this.setDISPLAY(Build.DISPLAY);                                                           //
		this.setMANUFACTURER(Build.MANUFACTURER);                                                 //OK
		this.setMODEL(Build.MODEL);                                                               //OK
		this.setSERIAL(Build.SERIAL);                                                             //OK

		this.setBuildID(Build.ID);                            //new
		this.setAndroidVersion(Build.VERSION.RELEASE);        //new
		this.setAndroidAPI(Build.VERSION.SDK_INT);            //new
	}

	public int getApplicationID() {
		return ApplicationID;
	}

	public void setApplicationID(int applicationID) {
		ApplicationID = applicationID;
	}

	public int getVersionID() {
		return VersionID;
	}

	public void setVersionID(int versionID) {
		VersionID = versionID;
	}

	public String getAndroidID() {
		return AndroidID;
	}

	public void setAndroidID(String androidID) {
		AndroidID = androidID;
	}

	public String getSERIAL() {
		return SERIAL;
	}

	public void setSERIAL(String SERIAL) {
		this.SERIAL = SERIAL;
	}

	public String getMODEL() {
		return MODEL;
	}

	public void setMODEL(String MODEL) {
		this.MODEL = MODEL;
	}

	public String getBuildID() {
		return BuildID;
	}

	public void setBuildID(String buildID) {
		BuildID = buildID;
	}

	public String getMANUFACTURER() {
		return MANUFACTURER;
	}

	public void setMANUFACTURER(String MANUFACTURER) {
		this.MANUFACTURER = MANUFACTURER;
	}

	public String getBRAND() {
		return BRAND;
	}

	public void setBRAND(String BRAND) {
		this.BRAND = BRAND;
	}

	public String getBOARD() {
		return BOARD;
	}

	public void setBOARD(String BOARD) {
		this.BOARD = BOARD;
	}

	public String getDISPLAY() {
		return DISPLAY;
	}

	public void setDISPLAY(String DISPLAY) {
		this.DISPLAY = DISPLAY;
	}


	public String getAndroidVersion() {
		return AndroidVersion;
	}

	public void setAndroidVersion(String androidVersion) {
		AndroidVersion = androidVersion;
	}

	public int getAndroidAPI() {
		return AndroidAPI;
	}

	public void setAndroidAPI(int androidAPI) {
		AndroidAPI = androidAPI;
	}

	@Override
	public void tryToRegisterDevice(ISplashScreenPeresenter peresenter, DeviceRequest deviceRequest) {

//			new TubelessException(DEVICE_REGISTER_SUCCESSFULL));
//			new TubelessException(DEVICE_NOT_REGISTER));


		TubelessRetrofitCallbackss ssssssss = new TubelessRetrofitCallbackss(context, TimelineListResponse.class) {
			@Override
			public void t_beforeSendRequest() {

			}

			@Override
			public void t_afterGetResponse() {

			}

			@Override
			public void t_complite() {

			}

			@Override
			public void t_responseNull() {
				peresenter.onThrowException(new TubelessException(DEVICE_NOT_REGISTER));
			}

			@Override
			public void t_retry(Call<Object> call) {

			}

			@Override
			public void t_onSuccess(Object response) {
				TimelineListResponse responseX = (TimelineListResponse) response;
				peresenter.onSuccess();

			}
		};
		Global.apiManagerTubeless.deviceRregister(deviceRequest, ssssssss);
	}

	@Override
	public boolean checkIsFirstRun() {
		SharedPreferences prefs = null;
		prefs =  context.getSharedPreferences("ir.sajjadyosefi.android.tubeless", MODE_PRIVATE);
		return prefs.getBoolean("firstrun", true);
	}

	@Override
	public boolean setFirstRunIsDone() {
		SharedPreferences prefs = null;
		prefs =  context.getSharedPreferences("ir.sajjadyosefi.android.tubeless", MODE_PRIVATE);

		try {
			prefs.edit().putBoolean("firstrun", false).commit();
			return true;
		}catch (Exception e) {
			return false;
		}
	}
}
