package sensors;

import utilities.SignalPack;

public interface Sensor {
	public SignalPack checkSignal(); //This should block until it returns a signal
}
