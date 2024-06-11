package encryption;
//Interface to use with strategy pattern
public abstract class CryptingScheme{

	//All classes can encypt/decrypt
	public abstract void encrypt(Key key, Msg message);
	
	public abstract void decrypt(Key key, Msg message);
	
	//Only homeBase changes key
	protected abstract void changeKey(Key key);
	protected abstract Key getKey();
	
}
