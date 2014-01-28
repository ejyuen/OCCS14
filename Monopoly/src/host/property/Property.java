package host.property;

import host.player.Company;

public class Property {
	private Company owner;
	private int propertyID; //look in PropertyList for which number corresponds to which property
	private int houses;
	
	public Property(int id){
		propertyID = id;
		houses = 0;
		owner = null;
	}
	
	/**
	 * 
	 * @return how much you should pay for landing on this space
	 */
	public double payment(){ //implement this later, remember that there are special cases
		return 1.0;
	}
	
	public Company getOwner(){
		return owner;
	}
}
