package ir.sajjadyosefi.android.xTubeless.classes.modelY;

import android.util.Log;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import ir.sajjadyosefi.android.xTubeless.classes.modelY.NetworkLayout.ResponseStatus;


public class Posts implements Serializable {
	private static final long serialVersionUID = 1L;


	private List<Post> Items;
	private ResponseStatus resultStatus = new ResponseStatus();



	public List<Post> getItems() {
		return Items;
	}
	public void setItems(List<Post> items) {
		this.Items = items;
	}
	public ResponseStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResponseStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	public Posts() {
		Items = new Vector<Post>(0);
	}
	public void addItem(Post item) {
		Items.add(item);
	}

	public void removeItem(int item) {
		Items.remove(item);
	}


	public static Boolean LikeOrDislikePost(Post post ,boolean typ) {
		try {
//			List<Post> postPrepareToDB = Post.find(Post.class , "itemid = ? and typeid = ? ", post.getItem_id() , post.getType_id());
//			for (Post item:  postPrepareToDB) {
//				item.setFeed_is_liked_b(typ);
//				item.save();
//			}
//			Log.e("Updated : " , post.getItem_id() + "-" + post.getType_id());
			return true;
		}
		catch (Exception ex){
			ex.printStackTrace();
			Log.e("Not Updated : " , post.getItem_id() + "-" + post.getType_id());
			return false;
		}
	}

	public static boolean deleteOfflinePost(Post post) {
		try {
//			List<Post> postPrepareToDB = Post.find(Post.class , "itemid = ? and typeid = ? and parentUserId = ? ", post.getItem_id() , post.getType_id() , post.getParent_user_id() );
//			for (Post item:  postPrepareToDB) {
//				item.delete();
//			}
//			Log.e("Deleted : " , post.getItem_id() + "-" + post.getType_id());
			return true;
		}
		catch (Exception ex){
			ex.printStackTrace();
			Log.e("Not Deleted : " , post.getItem_id() + "-" + post.getType_id());
			return false;
		}
	}

	public static boolean deleteOfflinePost(String itemid,String typeid) {
		try {
//			List<Post> postPrepareToDB = Post.find(Post.class , "itemid = ? and typeid = ? ",itemid  , typeid );
//			for (Post item:  postPrepareToDB) {
//				item.delete();
//			}
//			Log.e("Deleted : " , itemid + "-" + typeid);
			return true;
		}
		catch (Exception ex){
			ex.printStackTrace();
			Log.e("Not Deleted : " , itemid + "-" + typeid);
			return false;
		}
	}

}
