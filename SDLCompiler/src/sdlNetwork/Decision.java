package sdlNetwork;

import utilities.UtilityMethods;

public class Decision extends Action{
	private String name;
	private Connection trueConnection;
	private Connection falseConnection;
	
	public Decision(String name, Connection trueConnection, Connection falseConnection){
		super(name);
		this.trueConnection = trueConnection;
		this.falseConnection = falseConnection;
	}
	
	/**
	 * makes a decision by running the method in the name. If there are multiple 
	 * methods then it makes a decision based on the first method that returns a boolean
	 * value. 
	 * 
	 * If none of the methods return a boolean value, it chooses trueConnection
	 * 
	 * After making decision it runs the actions on the chosen connection.
	 * 
	 * @return the endState of the chosen connection
	 */
	public State makeDecision(){
		boolean decisionValue = true;
		for(Object o : runMethods()){
			if(o instanceof Boolean){
				decisionValue = (Boolean) o;
			}
		}
		
		if(decisionValue){
			return UtilityMethods.runConnection(trueConnection);
		} else {
			return UtilityMethods.runConnection(falseConnection);
		}
	}
}
