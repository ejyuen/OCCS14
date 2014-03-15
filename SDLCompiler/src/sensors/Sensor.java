package sensors;

import utilities.SignalList;

public interface Sensor {
	public SignalList getSignal(); //This should block until it returns a signal
}
