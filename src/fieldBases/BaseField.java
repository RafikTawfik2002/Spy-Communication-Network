package fieldBases;

import encryption.HomeBase;
import encryption.Msg;
import spies.ObserverSpy;

public interface BaseField {
	//Methods to communicate with spies
	//Through interface segregation, homeBase and spy won't have access to these methods
	
	public void sendMessage(HomeBase homeBase, Msg message);
	public void sendMessage(ObserverSpy fieldSpy, Msg message);
}
