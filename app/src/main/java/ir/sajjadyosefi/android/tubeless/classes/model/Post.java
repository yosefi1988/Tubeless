package ir.sajjadyosefi.android.tubeless.classes.model;


import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.List;

public class Post extends SugarRecord implements Serializable  {

	private static final long serialVersionUID = 1L;

	private String user_image = null;

	private List<String> feed_image = null;
	private String feed_id = null;
	private String item_id = null;
	private String user_id = null;
	private String feed_total_like = null;
	private String total_comment = "0";
	private String full_name = null;
	private boolean feed_is_liked_b = false;
	private String _content = null;
	private String parent_user_id = null;
	private String _view = null;
	private String _temp_id = null;
	private String _date = null;
	private String type_id = null;
	private String feed_content = null;
	private String feed_info = null;
	private String feed_status = null;
	private String feed_title = null;
	private String feed_link = null;
	private String LOCAL_ID = null;



	private String feed_icon = null;
	private String feed_custom_html = null;






	private String Recive_Order = null;
	private String privacy = null;
	private String onPage = null;
	private String type = null;
	private String linkUrl = null;
	private String linkTitle = null;
	private String linkDescription = null;
	private String time_update = null;



	public String getTime_update() {
		return time_update;
	}

	public void setTime_update(String time_update) {
		this.time_update = time_update;
	}

	public String getFeed_icon() {
		return feed_icon;
	}

	public void setFeed_icon(String feed_icon) {
		this.feed_icon = feed_icon;
	}


	public String getFeed_custom_html() {
		return feed_custom_html;
	}

	public void setFeed_custom_html(String feed_custom_html) {
		this.feed_custom_html = feed_custom_html;
	}




	public String getLinkDescription() {
		return linkDescription;
	}

	public void setLinkDescription(String linkDescription) {
		this.linkDescription = linkDescription;
	}

	public String getRecive_Order() {
		return Recive_Order;
	}

	public void setRecive_Order(String recive_Order) {
		Recive_Order = recive_Order;
	}

	public String getPrivacy() {
		return privacy;
	}

	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}

	public String getOnPage() {
		return onPage;
	}

	public void setOnPage(String onPage) {
		this.onPage = onPage;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getLinkTitle() {
		return linkTitle;
	}

	public void setLinkTitle(String linkTitle) {
		this.linkTitle = linkTitle;
	}






	public String getLOCAL_ID() {
		return LOCAL_ID;
	}

	public void setLOCAL_ID(String LOCAL_ID) {
		this.LOCAL_ID = LOCAL_ID;
	}






	public String getType_id() {
		return type_id;
	}
	public void setType_id(String type_id) {
		this.type_id = type_id;
	}
	public String getFeed_content() {
		return feed_content;
	}
	public void setFeed_content(String feed_content) {
		this.feed_content = feed_content;
	}
	public String getFeed_info() {
		return feed_info;
	}
	public void setFeed_info(String feed_info) {
		this.feed_info = feed_info;
	}
	public String getFeed_status() {
		return feed_status;
	}
	public void setFeed_status(String feed_status) {
		this.feed_status = feed_status;
	}
	public String getFeed_title() {
		return feed_title;
	}
	public void setFeed_title(String feed_title) {
		this.feed_title = feed_title;
	}
	public String getFeed_link() {
		return feed_link;
	}
	public void setFeed_link(String feed_link) {
		this.feed_link = feed_link;
	}

	public String get_temp_id() {
		return _temp_id;
	}
	public void set_temp_id(String _temp_id) {
		this._temp_id = _temp_id;
	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public void setTotal_comment(String comment) {
		total_comment = comment;
	}
	public void setFeed_is_liked_b(boolean like) {
		feed_is_liked_b = like;
	}
	public void setFeed_id(String id) {
		feed_id = id;
	}
	public void setParent_user_id(String id) {
		parent_user_id = id;
	}
	public void setUser_id(String id) {
		user_id = id;
	}
	public void setFeed_total_like(String like) {
		feed_total_like = like;
	}
	public void setView(String view) {
		_view = view;
	}
	public void setFull_name(String writer) {
		full_name = writer;
	}
	public void setContent(String description) {
		_content = description;
	}
	public	void setDate(String pubdate) {
		_date = pubdate;
	}
	public void setUser_image(String image) {
		user_image = image;
	}
	public String getTotal_comment() {
		return total_comment;
	}
	public String getFeed_id() {
		return feed_id;
	}
	public String getParent_user_id() {
		return parent_user_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public String getView() {
		return _view;
	}
	public String getFeed_total_like() {
		return feed_total_like;
	}
	public String getFull_name() {
		return full_name;
	}
	public boolean getFeed_is_liked_b() {
		return feed_is_liked_b;
	}
	public String getContent() {
		return _content;
	}
	public String getDate() {
		return _date;
	}

	public String getUser_image() {
		return null;
	}
	public String getUser_imageForDatabase() {
		return user_image;
	}


	public List<String> getFeed_image() {
		return feed_image;
	}

	public void setFeed_image(List<String> feed_image) {
		this.feed_image = feed_image;
	}



}
