package utilities;

import sdlNetwork.Action;
import sdlNetwork.Connection;
import sdlNetwork.State;

public class UtilityMethods {
	
	/**
	 * runs the actions in the connection and returns the endState
	 * placed here because it's called from a few different places
	 * 
	 * @return endState of connection
	 */
	public static State runConnection(Connection connection){
		for(Action a: connection.getActions()){
			new Thread(a).start();
		}
		return connection.getEndState();
	}
}
