package host.property;

import java.util.ArrayList;

public class PropertyManager {
	private static ArrayList<Property> properties; //should be a list of all purchasable property
	
	public PropertyManager(){
		for(int i = 0; i<40; i++){
			if(PropertyList.Properties[i][0] == 0){
				properties.add(new Property(i)); //adds all ownable property 
			}
		}
	}
}
