package utilities;

import sdlNetwork.Action;
import sdlNetwork.Connection;
import sdlNetwork.State;

public class HelperMethods {
	
	/**
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
