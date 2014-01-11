package sensors;

import utilities.Signal;

public interface Sensor {
	public Signal getSignal(); //This should block until it returns a signal
}
