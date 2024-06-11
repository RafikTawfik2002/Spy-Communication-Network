package fieldBases;

import spies.ObserverSpy;


public interface SpyField {
	//Interface for spy
	public void addSpy(ObserverSpy spy);

	public void deadSpy(ObserverSpy spy);
}
