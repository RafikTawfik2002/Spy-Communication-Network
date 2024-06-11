package spies;

import java.util.ArrayList;

import encryption.CryptingScheme;
import encryption.HomeBase;
import encryption.Key;
import encryption.Msg;
import fieldBases.ObserverField;
import fieldBases.SpyField;

public class Spy implements BaseSpy, ObserverSpy {
	private ArrayList<Msg> inbox = new ArrayList<Msg>();

	private Key spyKey;
	private CryptingScheme scheme;
	
	private SpyField fieldBase;
	
	public Spy(SpyField fieldBase) {
		this.fieldBase = fieldBase;
		fieldBase.addSpy(this);
	}
	
	//BaseSpy methods =>only a spy has access to these methods
	//Assumption: if a spy dies a device will automatically invoke spy.deadSpy()
	//to notify field base
	@Override
	public void deadSpy() {
		this.fieldBase.deadSpy(this);
		
	}

	//-------------------------------------------------------------------
	//Encryption Methods
	
	@Override
	public void setKey(Key key) {
		this.spyKey = key;
		
	}

	@Override
	public void setCryptionScheme(Key key, CryptingScheme scheme) {
		this.spyKey = key;;
		this.scheme = scheme;
		
	}

	//-------------------------------------------------------------------
	//Sending and receiving messages => only spy can send but any class can use receive
	//to make spy receive
	

	@Override
	public void sendMessage(ObserverField fieldBase, Msg message) {
		fieldBase.recieve(message);
		
	}

	@Override
	public void sendMessage(HomeBase homeBase, Msg message) {
		homeBase.receive(message);
		
	}
	
	@Override
	public void recieve(Msg message) {
		scheme.decrypt(spyKey, message);
		System.out.println("Spy recieved: " + message.getMsg());
		inbox.add(message);
		
		
	}

}
