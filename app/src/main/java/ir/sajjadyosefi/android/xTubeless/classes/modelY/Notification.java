package ir.sajjadyosefi.android.xTubeless.classes.modelY;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import ir.sajjadyosefi.android.xTubeless.classes.model.NotifyItem;


public class Notification implements Serializable {

	private static final long serialVersionUID = 1L;

	//private int count = 0;
	private List<NotifyItem> items;

//	public int getCount() {
//		return count;
//	}
//
//	public void setCount(int count) {
//		this.count = count;
//	}

	public List<NotifyItem> getItems() {
		return items;
	}

	public void setItems(List<NotifyItem> items) {
		this.items = items;
	}


	public Notification() {
		items = new Vector<NotifyItem>(0);
	}

	public void addItem(NotifyItem item) {
		items.add(item);
	}


}
