package encryption;

public class Key {
	
	private int keyValue;
	
	public Key(int keyValue) {
		this.keyValue = keyValue;
	}

	protected int getValue() {
		return keyValue;
	}
	
	protected int setValue(int keyValue) {
		return keyValue;
	}

}
