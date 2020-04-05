package ir.sajjadyosefi.android.xTubeless.classes.model.user;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.internal.Primitives;

import org.litepal.LitePal;
import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.lang.reflect.Type;

import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.activity.account.login.model.IUser;
import ir.sajjadyosefi.android.xTubeless.activity.account.login.presenter.ILoginPresenterI;
import ir.sajjadyosefi.android.xTubeless.activity.common.splashScreen.presenter.ISplashScreenPeresenter;
import ir.sajjadyosefi.android.xTubeless.classes.SAccounts;
import ir.sajjadyosefi.android.xTubeless.classes.StaticValue;
import ir.sajjadyosefi.android.xTubeless.classes.Validator;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.accounting.LoginRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.ServerResponseBase;
import ir.sajjadyosefi.android.xTubeless.utility.DeviceUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException.TUBELESS_DATABASE_ERROR;
import static ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException.TUBELESS_RESPONSE_BODY_IS_NULL;
import static ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.TubelessRetrofitCallbackss.showConnectionLostDialog;

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
	private boolean isAdmin = false;

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
		setAdmin(source.isAdmin());
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

	public void setAdmin(boolean admin) {
		isAdmin = admin;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void CheckUserValidity(ILoginPresenterI presenter , LoginRequest request) {
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
									tmpUser.setAdmin(CheckUserIsAdmin(tmpUser));

									//save to db
									if (Global.user == null){
										if ((new User(tmpUser)).save()){
											Global.user = tmpUser;


											if ((new Validator()).isIranianMobileNumber(request.getUserName()))
												tmpUser.setPassword(request.getPassword());

											presenter.onSuccess(tmpUser);
										}else {
											User dbUser = LitePal.where("userId like ?", tmpUser.getUserId() + "").findFirst(User.class);
											if (dbUser != null) {
												Global.user = dbUser;

												if ((new Validator()).isIranianMobileNumber(request.getUserName()))
													tmpUser.setPassword(request.getPassword());
												presenter.onSuccess(dbUser);
											}else {
												Global.user = null;
												presenter.onThrowException(new TubelessException(TUBELESS_DATABASE_ERROR));
											}
										}
									}else {
										Global.user = tmpUser;

										if ((new Validator()).isIranianMobileNumber(request.getUserName()))
											tmpUser.setPassword(request.getPassword());
										presenter.onSuccess(tmpUser);
									}
								}
							} else {
								if (presenter != null)
									presenter.onThrowException(new TubelessException(responseX.getTubelessException().getCode()));
							}
						}else {
							presenter.onThrowException(new TubelessException(responseX.getTubelessException().getCode()));
						}
					}else {
						presenter.onThrowException(new TubelessException(TUBELESS_RESPONSE_BODY_IS_NULL));
					}
				}catch (Exception sException) {
					if (presenter != null)
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

	public void CheckUserValidity2(ISplashScreenPeresenter presenter , LoginRequest request) {
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
									tmpUser.setAdmin(CheckUserIsAdmin(tmpUser));

									//save to db
									if (Global.user == null){
										if ((new User(tmpUser)).save()){
											Global.user = tmpUser;


											if ((new Validator()).isIranianMobileNumber(request.getUserName()))
												tmpUser.setPassword(request.getPassword());

											presenter.goToMainPage();
										}else {
											User dbUser = LitePal.where("userId like ?", tmpUser.getUserId() + "").findFirst(User.class);
											if (dbUser != null) {
												Global.user = dbUser;

												if ((new Validator()).isIranianMobileNumber(request.getUserName()))
													tmpUser.setPassword(request.getPassword());
												presenter.goToMainPage();
											}else {
												Global.user = null;
											}
										}
									}else {
										Global.user = tmpUser;

										if ((new Validator()).isIranianMobileNumber(request.getUserName()))
											tmpUser.setPassword(request.getPassword());
										presenter.goToMainPage();
									}
								}
							} else {
								if (presenter != null)
									presenter.onThrowException(new TubelessException(responseX.getTubelessException().getCode()));
							}
						}else {
							presenter.onThrowException(new TubelessException(responseX.getTubelessException().getCode()));
						}
					}else {
						presenter.onThrowException(new TubelessException(TUBELESS_RESPONSE_BODY_IS_NULL));
					}
				}catch (Exception sException) {
					if (presenter != null)
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

	public IUser loadUserData(ISplashScreenPeresenter presenter , LoginRequest request) {
		SAccounts sAccounts = new SAccounts(context);
		int accountId = sAccounts.getUserAccountID();

		Global.user = LitePal.where("userId like ?", accountId + "").findFirst(User.class);
		if (Global.user == null){

//			String accountName = sAccounts.getUserAccountName();
//			LoginRequest loginRequest = null;
//			Validator validator = new Validator();
//
//			if (validator.isIranianMobileNumber(accountName)){
//				loginRequest = new LoginRequest(accountName, sAccounts.getUserAccountPassword(), DeviceUtil.GetAndroidId(context));
//			}else if (validator.isIranianMobileNumber(accountName)) {
//				loginRequest = new LoginRequest(accountName, "");
//			}else {
//				loginRequest = new LoginRequest(accountName);
//			}
//			iUser.CheckUserValidity(null, loginRequest);

			CheckUserValidity2(presenter , request);
		}
		return Global.user;
	}


//	private void retry(Call<java.lang.Object> call) {
//		call.clone().enqueue(this);
//	}

	public boolean CheckUserIsAdmin(User user) {
		try {
			if (user.getUserId() == StaticValue.AdminUserID1 ||
					user.getUserId() == StaticValue.AdminUserID2 ||
					user.getUserId() == StaticValue.AdminUserID3 ||

					user.getUserName().equals(StaticValue.AdminMail1) ||
					user.getUserName().equals(StaticValue.AdminMail2) ||
					user.getUserName().equals(StaticValue.AdminMail3) ||

					user.getUserName().equals(StaticValue.AdminMobile1) ||
					user.getUserName().equals(StaticValue.AdminMobile2) ||
					user.getUserName().equals(StaticValue.AdminMobile3))
				return true;
			else
				return false;
		}catch (Exception ex){
			return false;
		}
	}
}
