package utilities;

import java.util.ArrayList;

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
		new Thread(new ActionRunner(connection.getActions())).start();
		return connection.getEndState();
	}
	
	static class ActionRunner implements Runnable{
		ArrayList<Action> actions;
		public ActionRunner(ArrayList<Action> actions){
			this.actions = actions;
		}
		
		public void run() {
			for(Action a: actions){
				a.run();
			}
		}
		
	}
}
