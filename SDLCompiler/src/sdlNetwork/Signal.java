package sdlNetwork;

import utilities.SignalList;


public class Signal{
	private String name;
	private SignalList sign = null;
	
	public Signal(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
		
	public void setSignal(){
		for(SignalList s: SignalList.values()){
			if(s.toString() == name){
				sign = s;
			}
		}
	}
	
	public SignalList getSignal(){
		return sign;
	}
	
	public static void main(String[] args){
		for(SignalList s: SignalList.values()){
			System.out.println(s.toString());
		}
	}
}