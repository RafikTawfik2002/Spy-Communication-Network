package encryption;

public class CaesarCipher extends CryptingScheme {

	//CharOffset is the key value
	private int charOffset;
	private Key key;
	
	
	public CaesarCipher(Key key) {
		this.setKey(key);
		this.charOffset = key.getValue();
	}
	
	@Override
	public void encrypt(Key key, Msg message) {
		
		if(message.isEncrypted()) {
			return;
		}
		
		message.changeState();
		
		if(this.getKey() == key) {
			for (int i = 0; i < message.msgSize(); i ++) {
				char m = message.getIndex(i);
				int code = m;
				if (code >= 65 && code <= 90) {
					code = 65 + (((code - 65) + charOffset) % 26);
				}
				else if (code >= 97 && code <= 122) {
					code = 97 + (((code - 97) + charOffset) % 26);
				}
				message.setIndex(i, (char)code);
				
			}
		}
	}

	@Override
	public void decrypt(Key key, Msg message) {
		if(message.isEncrypted() == false) {
			return;
		}
		
		message.changeState();
		
		if(this.getKey() == key) {
			for (int i = 0; i < message.msgSize(); i ++) {
				char m = message.getIndex(i);
				int code = m;
				if (code >= 65 && code <= 90) {
					code = 90 - trueModulo((90 - code)  + charOffset, 26);
				}
				else if (code >= 97 && code <= 122) {
					code = 122 - trueModulo((122 - code)  + charOffset, 26);
				}
				message.setIndex(i, (char)code);
				
			}
		}
	
	}
	
	protected Key getKey() {
		return this.key;
	}

	protected int getCharOffset() {
		return charOffset;
	}
	protected void changeKey(Key key) {
		this.setKey(key);
		this.charOffset = key.getValue();
		
	}
	
	protected int trueModulo(int n, int m) {
		return (((n % m) + m) % m);
	}

	protected void setKey(Key key) {
		this.key = key;
	}
	
	


}
