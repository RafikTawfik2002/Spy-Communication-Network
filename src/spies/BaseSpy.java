package spies;

import encryption.HomeBase;
import encryption.Msg;
import fieldBases.ObserverField;

public interface BaseSpy {

	public void deadSpy();
	//Sending Messages
	public void sendMessage(ObserverField fieldBase, Msg message);
	public void sendMessage(HomeBase homeBase, Msg message);
}
