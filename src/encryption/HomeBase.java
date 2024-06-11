package encryption;
import java.util.ArrayList;

import fieldBases.ObserverField;
import spies.ObserverSpy;

public class HomeBase extends HomeBaseSubject {
	
	private ArrayList<ObserverField> observers = new ArrayList<ObserverField>();
	private ArrayList<Msg> inbox = new ArrayList<Msg>();
	
	private CryptingScheme scheme;
	private Key baseKey;
	
	//SingleTone Pattern

	private static HomeBase base;
	private HomeBase(Key key, CryptingScheme scheme) {
		this.baseKey = key;
		this.scheme = scheme;
	}
	

	//-------------------------------------------------------------------
	//Private Constructor => SingleTone Pattern
	
	public static HomeBase getInstance(Key key, CryptingScheme scheme) {
		if (base == null) {
			base = new HomeBase(key, scheme);
		}
		return base;
	}
	
	public static HomeBase getInstance() {
		if (base == null) {
			Key nwKey = new Key(3);
			base = new HomeBase(nwKey, new CaesarCipher(nwKey));
		}
		return base;
	}
	
	//-------------------------------------------------------------------
	//Subject Methods => Observer Pattern
	@Override
	public void addFieldBase(ObserverField fieldBase) {
		if (observers.contains(fieldBase) == false) {
		observers.add(fieldBase);
		fieldBase.setCryptionScheme(this.baseKey, this.scheme);
		fieldBase.setKey(this.baseKey);
		}
	}
	@Override
	public void removeFieldBase(ObserverField fieldBase) {
		observers.remove(fieldBase);
	}
	
	//-------------------------------------------------------------------
	//Encryption Methods => protected to not give access to fields and spies
	@Override
	protected void updateCryptingScheme(Key key, CryptingScheme scheme) {
		this.scheme = scheme;
		this.baseKey = key;
		notifySchemeChange();
	}
	
	@Override
	protected void updateKey(Key key) {
		this.scheme.changeKey(key);
		this.baseKey = key;
		notifySchemeChange();
	}
	
	@Override
	protected void notifySchemeChange() {
		for (ObserverField fieldBase : observers) { 		      
	           fieldBase.setKey(baseKey);
	           fieldBase.setCryptionScheme(baseKey, scheme);
	      }
	}
	
	@Override
	protected Key getKey() {
		return this.baseKey;
	}
	//-------------------------------------------------------------------
	//Sending and receiving messages => only base can send but any class can use receive
	//to make homeBase receive
	
	@Override
	protected void sendMessage(ObserverField fieldBase, Msg message) {
		if (observers.contains(fieldBase)) {
			scheme.encrypt(baseKey, message); //Encrypts the message before sending it
			fieldBase.recieve(message);
		}
	}
	@Override
	protected void sendMessage(ObserverSpy fieldSpy, Msg message) {
			scheme.encrypt(baseKey, message); //Encrypts the message before sending it
			fieldSpy.recieve(message);
	}
	
	@Override
	public void receive(Msg message) {
		scheme.decrypt(baseKey, message);
		System.out.println("HomeBase Received: " + message.getMsg()); //For testing purposes
		inbox.add(message);
	}

}
