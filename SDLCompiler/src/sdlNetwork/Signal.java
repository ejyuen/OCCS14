package sdlNetwork;

import utilities.SignalPack;


public class Signal{
	private String name;
	private SignalPack sign = null;
	
	public Signal(String name){
		this.name = name;
		setSignal();
	}
	
	public String getName() {
		return name;
	}
		
	private void setSignal(){
		for(SignalPack s: SignalPack.values()){
			if(s.toString().equals(name)){
				sign = s;
			}
		}
	}
	
	public SignalPack getSignalPack(){
		return sign;
	}
	
	
	public String toString(){
		return getName();
	}
}