package sdlNetwork;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import utilities.ActionPack;

public class Action implements Runnable{
	private String name;
	private ArrayList<String> methodNames;
	private ArrayList<ArrayList<String>> methodParameters;
	private ArrayList<ArrayList<Object>> convertedMethodParameters = new ArrayList<ArrayList<Object>>();
	//private static final Pattern methodPattern = Pattern.compile("[^;]+");
	//private static final Pattern parameterPattern = Pattern.compile("[(][^)]+");


	public Action(String name){
		this.name = name;
		methodNames = new ArrayList<String>();
		methodParameters = new ArrayList<ArrayList<String>>();
		init();
		convertParameters();
	}



	private void init(){
		//TODO: make this stuff work with strings
		//set methodNames and Parameters here;
		//Matcher methodMatcher = methodPattern.matcher(name);
		//Matcher parameterMatcher = parameterPattern.matcher(name);             

		name = name.replaceAll("\\s", "");
		String[] methodArray = name.split(";");
		for(int i = 0; i < methodArray.length; i++) {
			String[] nameArray = methodArray[i].split("[(]");
			methodNames.add(nameArray[0]);
			
			nameArray[1] = nameArray[1].substring(0, nameArray[1].length() - 1);
			String[] parameterArray = nameArray[1].split(",");
			ArrayList<String> temp = new ArrayList<String>();
			for(String s: parameterArray){
				temp.add(s);
			}
			methodParameters.add(temp);
		}
	}

	public void convertParameters(){
		
		ArrayList<Object> convertedParameters = new ArrayList<Object>();
		boolean identified= true;
		for (ArrayList<String> method: methodParameters){
			for (String parameter: method){
				try{
					convertedParameters.add(Integer.valueOf(parameter));    
				}catch(NumberFormatException e1){
					identified = false;
				}
				if(!identified){
					identified = true;
					try{
						convertedParameters.add(Double.valueOf(parameter));
					} catch(NumberFormatException e2){
						identified = false;
					}
					if (!identified && (parameter.toLowerCase().equals("true") || parameter.toLowerCase().equals("false")))
						convertedParameters.add(Boolean.valueOf(parameter));
					else{
						convertedParameters.add(parameter);
					}
				}
				identified = true;
			}
			convertedMethodParameters.add(convertedParameters);
			convertedParameters.clear();
		}
	
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
		System.out.println(getMethodNames());
		System.out.println(getMethodParameters());
		System.out.println(getConvertedMethodParameters());
		runMethods();
	}

	public ArrayList<Object> runMethods(){
		ArrayList<Object> ret = new ArrayList<Object>();
		try {
			Class<utilities.ActionPack> c = ActionPack.class;
			
			for(int i = 0; i < getMethodNames().size(); i++){
				Class[] parameterTypes = new Class[getConvertedMethodParameters().get(i).size()];
				for(int j = 0; j < getConvertedMethodParameters().get(i).size(); j++){
                	 parameterTypes[j] = getConvertedMethodParameters().get(i).get(j).getClass();
                }
				
				Method method = c.getDeclaredMethod(getMethodNames().get(i), parameterTypes);
				ret.add(method.invoke(null, getConvertedMethodParameters().get(i)));
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
	
	public static void main(String[] args){
		new Action("test(1, true, 1.0);").run();
	}
}