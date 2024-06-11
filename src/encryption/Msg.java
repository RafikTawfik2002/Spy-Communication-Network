package encryption;
import java.util.ArrayList;

public class Msg {
	
	private String msg;
	
	private boolean isEncrypted = false;

	public Msg(String msgContent) {
		this.msg = msgContent;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public char getIndex(int i) {
		return msg.charAt(i);
	}
	
	public void setIndex(int i, char x) {
		msg = msg.substring(0,i) + x + msg.substring(i+1, msgSize());
	}
	
	public int msgSize() {
		return msg.length();
	}
	
	public boolean isEncrypted() {
		return isEncrypted;
	}
	
	public void changeState() {
		isEncrypted = !isEncrypted;
	}
	
	
	
}
