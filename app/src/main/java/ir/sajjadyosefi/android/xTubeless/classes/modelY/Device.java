package ir.sajjadyosefi.android.xTubeless.classes.modelY;

public class Device {
	private static final long serialVersionUID = 1L;

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

}
