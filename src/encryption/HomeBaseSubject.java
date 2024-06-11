package encryption;

import fieldBases.ObserverField;
import spies.ObserverSpy;

public abstract class HomeBaseSubject {
	//Protected keyword allows limiting other classes to access
	//to methods they do not need
	public abstract void addFieldBase(ObserverField fieldBase);
	public abstract void removeFieldBase(ObserverField fieldBase);
	
	//Sending Messages
	protected abstract void sendMessage(ObserverField fieldBase, Msg message);
	protected abstract void sendMessage(ObserverSpy fieldSpy, Msg message);
	//Receive 
	public abstract void receive(Msg message);
	
	
	//Encyption scheme
	protected abstract void updateCryptingScheme(Key key, CryptingScheme scheme);
	protected abstract void updateKey(Key key);
	protected abstract Key getKey();
	protected abstract void notifySchemeChange();
	


}
