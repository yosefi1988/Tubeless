package ir.sajjadyosefi.android.tubeless.classes.model.User;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.List;

import ir.sajjadyosefi.android.tubeless.classes.model.Car.Car;
import ir.sajjadyosefi.android.tubeless.classes.model.Post;

public class User extends SugarRecord implements Serializable {

	private static final long serialVersionUID = 1L;
	private String city_location = null;
	private List<Post> PostItems = null ;
	private String pushToken ;
	private String _cookie = "";

	private String loginPhone = null;
	private String loginPassword = null;
	private Device device = null;

	private List<Car> carList = null ;

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

	public String getCookie() {
		return _cookie;
	}

	public void setCookie(String _cookie) {
		this._cookie = _cookie;
	}

	public List<Post> getPostItems() {
		return PostItems;
	}

	public void setPostItems(List<Post> postItems) {
		PostItems = postItems;
	}

	public String getPushToken() {
		return pushToken;
	}

	public void setPushToken(String pushToken) {
		this.pushToken = pushToken;
	}

	public void setCity_location(String city) {
		city_location = city;
	}

//	public String getUser_image() {
//		return "";
//	}

	public String getCity_location() {
		return city_location;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}




	//22


	private int	ApplicationID;
	private long UserID;
	private String UserName;
	private String Password;
	private String Email;
	private String MobileNumber;
	private String UserImage;
	private String ProfileImage;
	private Boolean canSendPicture;


	//////////////////////////////////////////////////////////////////////////////////////////////
	public int getApplicationID() {
		return ApplicationID;
	}

	public void setApplicationID(int applicationID) {
		ApplicationID = applicationID;
	}

	public long getUserID() {
		return UserID;
	}

	public void setUserID(long userID) {
		UserID = userID;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getMobileNumber() {
		return MobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		MobileNumber = mobileNumber;
	}

	public String getUserImage() {
		return UserImage;
	}

	public void setUserImage(String userImage) {
		UserImage = userImage;
	}

	public String getProfileImage() {
		return ProfileImage;
	}

	public void setProfileImage(String profileImage) {
		ProfileImage = profileImage;
	}

	public Boolean getCanSendPicture() {
		return canSendPicture;
	}

	public void setCanSendPicture(Boolean canSendPicture) {
		this.canSendPicture = canSendPicture;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////


	public List<Car> getCarList() {
		return carList;
	}

	public void setCarList(List<Car> carList) {
		this.carList = carList;
	}

}
