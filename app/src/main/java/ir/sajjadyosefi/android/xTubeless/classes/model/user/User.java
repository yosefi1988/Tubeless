package ir.sajjadyosefi.android.xTubeless.classes.model.user;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.internal.Primitives;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.lang.reflect.Type;
import java.util.List;

import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.account.login.model.IUser;
import ir.sajjadyosefi.android.xTubeless.activity.account.login.view.ILoginView;
import ir.sajjadyosefi.android.xTubeless.activity.account.login.presenter.IPresenter;
import ir.sajjadyosefi.android.xTubeless.classes.SAccounts;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.accounting.LoginRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.responses.LoginResponse;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.Exception.TubelessException;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.Post;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.Device;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.Car.Car;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.responses.basic.ServerResponseBase;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.TubelessRetrofitCallbackss;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retroftPost.PostRetrofitCallback;
import ir.sajjadyosefi.android.xTubeless.utility.DeviceUtil;
import ir.sajjadyosefi.android.xTubeless.utility.xUtility.AndroidHardware;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static ir.sajjadyosefi.android.xTubeless.classes.modelY.Exception.TubelessException.TUBELESS_DATABASE_ERROR;
import static ir.sajjadyosefi.android.xTubeless.classes.modelY.Exception.TubelessException.TUBELESS_RESPONSE_BODY_IS_NULL;
import static ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.TubelessRetrofitCallback.showConnectionLostDialog;

public class User extends LitePalSupport implements IUser {

	private Context context;
	//_____________ ok ________________
	@Column(unique = true, defaultValue = "unknown")
	private long userId;
	private String userName;
	private String email;
	private String mobileNumber;
	private String userImage;
	private String ProfileImage;
	public long balanse;

	private String Password;
	private String loginPhone = null;
	private String loginPassword = null;
	//_________________________________

//	@Column(ignore = true)
//	private String city_location = null;
//
//	@Column(ignore = true)
//	private List<Post> PostItems = null ;
//
//	@Column(ignore = true)
//	private List<Car> carList = null ;
//
//	@Column(ignore = true)
//	private String pushToken ;
//
//	@Column(ignore = true)
//	private String _cookie = "";


//	private Device device = null;

//	private int	ApplicationID;
//


//	private Boolean canSendPicture;

	public User(Context context) {

		this.context = context;
	}

	public User(User source) {
		setUserId(source.getUserId());
		setUserName(source.getUserName());
		setEmail(source.getEmail());
		setMobileNumber(source.getMobileNumber());
		setUserImage(source.getUserImage());
		setProfileImage(source.getProfileImage());

		setBalanse(source.getBalanse());
		setPassword(source.getPassword());
		setLoginPassword(getLoginPassword());
		setLoginPhone(source.getLoginPhone());
	}

	public User() {

	}


	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public String getProfileImage() {
		return ProfileImage;
	}

	public void setProfileImage(String profileImage) {
		ProfileImage = profileImage;
	}

	public long getBalanse() {
		return balanse;
	}

	public void setBalanse(long balanse) {
		this.balanse = balanse;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getLoginPhone() {
		return loginPhone;
	}

	public void setLoginPhone(String loginPhone) {
		this.loginPhone = loginPhone;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public void CheckUserValidity(IPresenter presenter , LoginRequest request) {


		Callback callback = new Callback() {
			@Override
			public void onResponse(Call call, Response response) {
//				t_afterGetResponse();

				Gson gson = new Gson();
				JsonElement jsonElement = gson.toJsonTree(response.body());
				ServerResponseBase responseX = null;

				try {
					if (response.body() == null){
						presenter.onThrowException(new TubelessException(TUBELESS_RESPONSE_BODY_IS_NULL));
					}

					responseX = gson.fromJson(jsonElement.getAsString(), ServerResponseBase.class);
					if (response.body() != null ) {
						if (responseX.getTubelessException().getCode() != 0) {
							if (responseX.getTubelessException().getCode() > 0) {
								if (call != null && response != null) {
									Object object = gson.fromJson(jsonElement.getAsString(), (Type) User.class);
									User tmpUser = Primitives.wrap(User.class).cast(object);

									//save to db
									if (Global.user == null){
										if ((new User(tmpUser)).save()){
											Global.user = tmpUser;
											presenter.onSuccess(tmpUser);
										}else {
											Global.user = null;
											presenter.onThrowException(new TubelessException(TUBELESS_DATABASE_ERROR));
										}
									}else {
										Global.user = tmpUser;
										presenter.onSuccess(tmpUser);
									}
								}
							} else {
								presenter.onThrowException(new TubelessException(responseX.getTubelessException().getCode()));
							}
						}else {
							presenter.onThrowException(new TubelessException(responseX.getTubelessException().getCode()));
						}
					}else {
						presenter.onThrowException(new TubelessException(TUBELESS_RESPONSE_BODY_IS_NULL));
					}
				}catch (Exception sException) {
					presenter.onThrowException(sException);
				}
			}

			@Override
			public void onFailure(Call call, Throwable t) {
//				presenter.onThrowException(t);


				try {
					showConnectionLostDialog(context, null , new Runnable() {
						@Override
						public void run() {
							retry(call);
						}
					});
				}catch (Exception ex){
					int aX =0 ;
					aX  ++;

				}

			}

			private void retry(Call call) {
				call.clone().enqueue(this);
			}
		};
		Global.apiManagerTubeless.loginOrRregisterMVP(request, callback);
	}



//	private void retry(Call<java.lang.Object> call) {
//		call.clone().enqueue(this);
//	}


}
