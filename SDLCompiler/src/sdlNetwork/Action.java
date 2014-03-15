package sdlNetwork;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import utilities.ActionList;

public class Action{
	private String name;
	private String[] methodNames;
	private String[][] methodParameters;
	
	public Action(String name){
		this.name = name;
		init();
	}
	
	private void init(){
		//set methodNames and Parameters here;
	}
	
	public String getName() {
		return name;
	}
	
	public String[] getMethodNames(){
		return methodNames;
	}
	
	public String[][] getMethodParameters(){
		return methodParameters;
	}
	
	public void run(){
		try {
			Class<utilities.ActionList> c = ActionList.class;
			
			for(int i = 0; i < getMethodNames().length; i++){
				Method method = c.getDeclaredMethod(getMethodNames()[i], String[].class);
				method.invoke(null, getMethodParameters()[i]);
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}