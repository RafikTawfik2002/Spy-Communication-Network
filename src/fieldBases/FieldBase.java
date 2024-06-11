package fieldBases;

import java.util.ArrayList;

import encryption.CryptingScheme;
import encryption.HomeBase;
import encryption.Key;
import encryption.Msg;
import spies.ObserverSpy;
import spies.Spy;
//FieldBases are both subjects and observers
public class FieldBase implements ObserverField, BaseField, SpyField {

	private ArrayList<Msg> inbox = new ArrayList<Msg>();
	private ArrayList<ObserverSpy> spies = new ArrayList<ObserverSpy>();
	//The list is static since a deadSpy can't register to any fieldBase
	private static ArrayList<ObserverSpy> deadSpies = new ArrayList<ObserverSpy>();
	
	private boolean isRegister = false;
	private HomeBase homeBase = HomeBase.getInstance();
	private Key fieldKey;
	private CryptingScheme scheme;
	
	public FieldBase() {
		regeister();
	}
	
	//---------------------------------------------------------------
	//FieldBase is an observer to the homeBase. homeBase is a subject
	@Override
	public void regeister() {
		//homeBase.getKey(); Such line isn't possible since getKey is protected
		if (isRegister == false) {
		homeBase.addFieldBase(this);
		isRegister = true;
		}
	}

	//Used to go dark
	@Override
	public void unregister() {
		if (isRegister == true) {
		homeBase.removeFieldBase(this);
		isRegister = false;
		}
	}
	
	//-------------------------------------------------------------
	//Encryption Methods => pushes updates to spies in the network
	
	@Override
	public void setKey(Key key) {
		this.fieldKey = key;	
		for(ObserverSpy spy: spies) {
			spy.setKey(key);
		}
	}

	@Override
	public void setCryptionScheme(Key key, CryptingScheme scheme) {
		this.fieldKey = key;
		this.scheme = scheme;
		for(ObserverSpy spy: spies) {
			spy.setKey(key);
			spy.setCryptionScheme(key, scheme);
		}
		
	}
	

	//-------------------------------------------------------------
	//FieldBase is a subject to the spies. Spies are observers
	

	@Override
	public void addSpy(ObserverSpy spy) {
		if (spies.contains(spy) == false && deadSpies.contains(spy) == false)
		spies.add(spy);
		spy.setKey(fieldKey);
		spy.setCryptionScheme(fieldKey, scheme);
	}
	
	//Only used inside the class when spy dies 
	//Through interface segregation, fieldBase is the only class
	//allowed to call this method
	@Override
	public void deadSpy(ObserverSpy spy) {
		spies.remove(spy);
		deadSpies.add(spy);
	}


	//-------------------------------------------------------------
	//Encryption methods
	@Override
	public void sendMessage(HomeBase homeBase, Msg message) {
		scheme.encrypt(fieldKey, message);
		homeBase.receive(message);
	};
	@Override
	public void sendMessage(ObserverSpy fieldSpy, Msg message) {
		scheme.encrypt(fieldKey, message);
		fieldSpy.recieve(message);
		
	};
	
	@Override
	public void recieve(Msg message) {
		scheme.decrypt(fieldKey, message);
		System.out.println("FieldBase Recieved: " + message.getMsg());
		inbox.add(message);
		
	}
	




}