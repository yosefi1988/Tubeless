package ir.sajjadyosefi.android.xTubeless.classes.modelY;

import org.w3c.dom.Comment;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import ir.sajjadyosefi.android.xTubeless.classes.modelY.responses.basic.ServerStatus;


public class Comments implements Serializable {

	@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
	private static final long serialVersionUID = 1L;
	private int _reply_length = 0;
	private List<Comment> comments;
	ServerStatus resultStatus;


	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public ServerStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ServerStatus resultStatus) {
		this.resultStatus = resultStatus;
	}



	public Comments() {
		comments = new Vector<Comment>(0);
	}
	public void addItem(Comment item) {
		comments.add(item);
	}
	public Comment getComment(int location) {
		return comments.get(location);
	}
	public int getItemCount() {
		return comments.size();
	}
	public void removeItem(int item) {
		comments.remove(item);
	}
	public void addLength(int i) {
		_reply_length += i;
	}
	public int getLength() {
		return _reply_length;
	}


	///////////////////////////////// db //////////////////////////////////

	static String DB_TABLE = "Comment_v2" ;



}
