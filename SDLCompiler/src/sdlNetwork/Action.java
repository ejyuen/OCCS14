package sdlNetwork;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import utilities.ActionList;

public class Action{
	private String name;
	
	public Action(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public String[] getMethodName(){
		return new String[0];
	}
	
	public String[][] getMethodParameters(){
		return new String[0][0];
	}
	
	public void run(){
		try {
			Class<utilities.ActionList> c = ActionList.class;
			
			for(int i = 0; i < getMethodName().length; i++){
				Method method = c.getDeclaredMethod(getMethodName()[i], String[].class);
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