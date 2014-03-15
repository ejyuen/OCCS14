package sdlNetwork;

import utilities.SignalPack;


public class Signal{
	private String name;
	private SignalPack sign = null;
	
	public Signal(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
		
	public void setSignal(){
		for(SignalPack s: SignalPack.values()){
			if(s.toString() == name){
				sign = s;
			}
		}
	}
	
	public SignalPack getSignalPack(){
		return sign;
	}
	
	public static void main(String[] args){
		for(SignalPack s: SignalPack.values()){
			System.out.println(s.toString());
		}
	}
}