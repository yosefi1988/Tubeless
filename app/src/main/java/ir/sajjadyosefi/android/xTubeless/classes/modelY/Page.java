package ir.sajjadyosefi.android.xTubeless.classes.modelY;

import com.orm.SugarRecord;

import java.io.Serializable;


public class Page {

	private String title = null;
	private String notify = null;
	private String user_image = null;
	public String cover = null;
	private String page_id = null;


	public String getPage_id() {
		return page_id;
	}

	public void setPage_id(String page_id) {
		this.page_id = page_id;
	}





	public String getCover() {
		return cover;
//
//
//		try {
//			if(cover!= null && cover.length()>2)
//				return WebConf_v2.PAGE_IMAGE + String.format(cover, WebConf_v2.PAGE_IMAGE_500);
//			return "null";
//		}catch (Exception ex)
//		{
//			ex.printStackTrace();
//			return "";
//		}
	}

	public void setCover(String cover) {
		this.cover = cover;
	}





	public void setTitle(String title) {
		this.title = title;
	}
	public void setUser_image(String image) {
		user_image = image;
	}
	public String getTitle() {
		return title;
	}

	public String getUser_image() {
		return user_image;

	}
	public void setNotify(String notify) {
		this.notify = notify;
	}
	public String getNotify() {
		return notify;
	}


}
