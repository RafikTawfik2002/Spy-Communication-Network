package encryption;

import fieldBases.FieldBase;
import spies.Spy;

public class Runner {
	public static void main(String []args) {
		Msg message = new Msg("Very important message from HomeBase");
		
		Key nwKey = new Key(2);
		
		CaesarCipher scheme = new CaesarCipher(nwKey);
		
		CaeserCipherExtra nwScheme = new CaeserCipherExtra(nwKey, scheme);
		
		nwScheme = new CaeserCipherExtra(nwKey, nwScheme);
		nwScheme = new CaeserCipherExtra(nwKey, nwScheme);
		nwScheme = new CaeserCipherExtra(nwKey, nwScheme);
		
		
		
		
		HomeBase homeBase = HomeBase.getInstance(nwKey, nwScheme);
		
		FieldBase fieldOne = new FieldBase();
		
		
		
		//homeBase.sendMessage(fieldOne, message);
		
		fieldOne.unregister();
		
		
		fieldOne.regeister();
		
		Spy spy1 = new Spy(fieldOne);
		//homeBase.sendMessage(fieldOne, message);
		//Change key before sending a message to a spy
		
		Key newerKey = new Key(21);
		homeBase.updateKey(newerKey);
		
		
		homeBase.sendMessage(spy1, message);
	
		

		
		//spy1.sendMessage(homeBase, message);
		
	}
}
