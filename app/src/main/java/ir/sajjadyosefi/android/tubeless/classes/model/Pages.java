package ir.sajjadyosefi.android.tubeless.classes.model;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import ir.sajjadyosefi.android.tubeless.classes.model.Responses.ServerStatus;

public class Pages implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Page> pages;
	ServerStatus resultStatus;

	public Pages() {
		pages = new Vector<Page>(0);
	}

	public void addItem(Page item) {
		pages.add(item);
	}

	public Page getPage(int location) {
		try {
			return pages.get(location);
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

	public List<Page> getPages() {
		return pages;
	}



	public ServerStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ServerStatus resultStatus) {
		this.resultStatus = resultStatus;
	}



	////////////////////////// db ///////////////////////////

	public static Pages getOfflinePages() {
		Pages Pages = new Pages();
		List<Page> pages = Page.listAll(Page.class);
		for (int i = 0 ; i < pages.size() ; i ++ ){
			Pages.addItem(pages.get(i));
		}
		return Pages;
	}
	public static boolean DeleteOfflinePages() {
		try {
			List<Page> books = Page.listAll(Page.class);
			Page.deleteAll(Page.class);
			return true;
		}
		catch (Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	public static boolean insertPages(Pages pages) {
		try {
			for (int i = 0; i < pages.getPages().size(); i++) {
				Page page = pages.getPage(i);
				page.save();
			}
			return true;
		}
		catch (Exception ex){
			ex.printStackTrace();
			return false;
		}
	}

}
