package encryption;

public class CaeserCipherExtra extends CaesarCipher{

	private CaesarCipher scheme;
	private int charOffset;

	
	//Assumption: key provided has to be the same as scheme's key
	public CaeserCipherExtra(Key key, CaesarCipher scheme) {
		super(key);
		this.scheme = scheme;
		this.charOffset = 1+ ((int)(Math.random() * 10)*scheme.getCharOffset()) % 26; 
		//The extra layer works by having a different offset
		//Which is the original offset plus a random number
	}
	
	@Override
	public void encrypt(Key key, Msg message) {
		if(message.isEncrypted()) {
			return;
		}
		
		scheme.encrypt(key, message);
		
		
		//Makes sure the first encryption worked
		if(scheme.getKey() == key && message.isEncrypted()) { 
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
		System.out.println("Another encryption: " + message.getMsg()+ "");
		System.out.println("With an offset of: " + charOffset + "\n");
	}

	@Override
	public void decrypt(Key key, Msg message) {
		if(message.isEncrypted() == false) {
			return;
		}
		//Decryption works the same way in reverse order
		if(scheme.getKey() == key) {
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
		
		System.out.println("After one decryption: " + message.getMsg()+ "");
		System.out.println("With an offset of: " + charOffset + "\n");
		scheme.decrypt(key, message);
		

		
	
	}

	@Override
	protected int getCharOffset() {
		return charOffset;	
		}
	
	protected void changeKey(Key key) {
		this.scheme.changeKey(key);
		this.setKey(key);


	}
	

}
