package sensors;

import utilities.SignalPack;

public interface Sensor {
	public SignalPack getSignal(); //This should block until it returns a signal
}
