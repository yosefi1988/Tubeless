package ir.sajjadyosefi.android.tubeless.classes.model.User;

import com.orm.SugarRecord;

import java.io.Serializable;

public class Message extends SugarRecord implements Serializable {

	private int	ApplicationID;
	private String phoneNumber;
	private String title;
	private String text;
	private String type;

	public int getApplicationID() {
		return ApplicationID;
	}

	public void setApplicationID(int applicationID) {
		ApplicationID = applicationID;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
