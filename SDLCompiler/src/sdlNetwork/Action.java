package sdlNetwork;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;


import utilities.ActionPack;

public class Action implements Runnable{
	private String name;
	private ArrayList<String> methodNames;
	private ArrayList<ArrayList<String>> methodParameters;
	private ArrayList<ArrayList<Object>> convertedMethodParameters = new ArrayList<ArrayList<Object>>();

	public Action(String name){
		this.name = name;
		methodNames = new ArrayList<String>();
		methodParameters = new ArrayList<ArrayList<String>>();
		init();
	}


	/**
	 * Sets methodNames and methodParameters based on name, then calls convertParameters
	 */
	private void init(){
		//TODO: make this stuff work with strings that have funny characters
		
		name = name.replaceAll("\\s", "");
		String[] methodArray = name.split(";");
		for(int i = 0; i < methodArray.length; i++) {
			String[] nameArray = methodArray[i].split("[(]");
			methodNames.add(nameArray[0]);

			nameArray[1] = nameArray[1].substring(0, nameArray[1].length()-1);
			String[] parameterArray = nameArray[1].split(",");
			
			ArrayList<String> temp = new ArrayList<String>();
			if(parameterArray.length != 1 || !parameterArray[0].equals("")){
				for(String s: parameterArray){
					temp.add(s);
				}
			}
			methodParameters.add(temp);
		}

		convertParameters();
	}

	/**
	 * converts methodParameters(which are all strings) into int, boolean, or double
	 * if it isn't one of those three, it keeps it as a string
	 */
	private void convertParameters(){		
		for (ArrayList<String> method: methodParameters){
			ArrayList<Object> convertedParameters = new ArrayList<Object>();
			for(String parameter: method){
				//integer
				try{
					convertedParameters.add(Integer.valueOf(parameter));
					continue;
				} catch(NumberFormatException e1){} //do nothing, not an int
				
				//double
				try{
					convertedParameters.add(Double.valueOf(parameter));
					continue;
				} catch(NumberFormatException e2){} //do nothing, not a double

				//boolean
				if (parameter.toLowerCase().equals("true") || parameter.toLowerCase().equals("false")){
					convertedParameters.add(Boolean.valueOf(parameter));
					continue;
				}
				
				//string
				convertedParameters.add(parameter);
			}
			convertedMethodParameters.add(convertedParameters);
		}
	}

	/**
	 * Functions just like object.getClass()
	 * However some primitive objects (Integer, Double, Boolean) use the .class of their primitive types 
	 * 
	 * @param obj an object
	 * @return the .class or getClass() of the parameter
	 */
	private Class primitiveGetClass(Object obj){
		if(obj instanceof Integer){
			return int.class;
		}
		if(obj instanceof Double){
			return double.class;
		}
		if(obj instanceof Boolean){
			return boolean.class;
		}
		return obj.getClass();
	} 
	
	public String toString(){
		return getName();
	}

	public String getName() {
		return name;
	}

	public ArrayList<String> getMethodNames(){
		return methodNames;
	}

	public ArrayList<ArrayList<String>> getMethodParameters(){
		return methodParameters;
	}

	public ArrayList<ArrayList<Object>> getConvertedMethodParameters(){
		return convertedMethodParameters;
	}

	public void run(){
		runMethods();
	}

	public ArrayList<Object> runMethods(){
		ArrayList<Object> ret = new ArrayList<Object>();
		try {
			Class<utilities.ActionPack> c = ActionPack.class;

			for(int i = 0; i < getMethodNames().size(); i++){
				//turns parameters into an array and creates an array of their getClass()
				Object[] methodParams = getConvertedMethodParameters().get(i).toArray();
				Class[] parameterTypes = new Class[methodParams.length];
				for(int j = 0; j < methodParams.length; j++){
					parameterTypes[j] = primitiveGetClass(methodParams[j]);
				}
				
				Method method = c.getDeclaredMethod(getMethodNames().get(i), parameterTypes);
				ret.add(method.invoke(null, methodParams));
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
		return ret;
	}

//	public static void main(String[] args){
//		new Action("test(1, true, 1.0);").run();
//	}
}