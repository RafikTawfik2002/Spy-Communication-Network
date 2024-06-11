package fieldBases;

import encryption.CryptingScheme;
import encryption.Key;
import encryption.Msg;

public interface ObserverField {
	//Register
	public void regeister();
	public void unregister();
	//Message and encryption
	public void recieve(Msg message);
	public void setKey(Key key);
	public void setCryptionScheme(Key key, CryptingScheme scheme);
}
