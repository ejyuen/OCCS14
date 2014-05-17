package utilities;

/**
 * This is the class that contains all the methods that are in a state machine
 * 
 * It's important to note that the method must have 1 parameter that is a String array
 * 
 * @author Matthew Li
 *
 */
public class ActionPack {
	
	public static void test(int i, boolean b, double s){
		System.out.println("" + i + " " + b + " " + s);
	}
	
	public static void defaultAction(String[] params){
		//do something here
	}
	
	public static void StartCar(String[] params){
		System.out.println("Starting Car");
	}
	
	public static void DriveCar(String[] params){
		System.out.println("Driving to food");
	}
	
	public static void GetFood(String[] params){
		System.out.println("Getting food");
	}
	
	public static void GiveMoney(String[] params){
		System.out.println("Giving Money");
	}
	
	public static void EatFood(String[] params){
		System.out.println("Eating food");
	}
	
	
}
