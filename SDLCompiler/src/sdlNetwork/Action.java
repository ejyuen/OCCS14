package sdlNetwork;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utilities.ActionPack;

public class Action implements Runnable{
	//private String name;
	private String name;
	private ArrayList<String> methodNames;
	private ArrayList<String[]> methodParameters;
	private static final Pattern methodPattern = Pattern.compile(";\n[A-Za-z0-9_]+");
	private static final Pattern parameterPattern = Pattern.compile("[(][^)]+");
	
	
	public Action(String name){
		this.name = name;
		methodNames = new ArrayList<String>();
		methodParameters = new ArrayList<String[]>();
		init();
	}
	
	private void init(){
		//set methodNames and Parameters here;
		Matcher methodMatcher = methodPattern.matcher(name);
		Matcher parameterMatcher = parameterPattern.matcher(name);		
		
		while(methodMatcher.find()){
			String rawMethod = methodMatcher.group();
			rawMethod = rawMethod.substring(1,rawMethod.length()).trim();
			methodNames.add(rawMethod);
		}
		
		while(parameterMatcher.find()){
			String rawParameter = parameterMatcher.group();
			rawParameter = rawParameter.substring(1, rawParameter.length());
			String[] parameterArray = rawParameter.split(",");
			for(int i=0; i < parameterArray.length; i++){
				parameterArray[i] = parameterArray[i].trim();
			}
			methodParameters.add(parameterArray);
		}
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<String> getMethodNames(){
		return methodNames;
	}
	
	public ArrayList<String[]> getMethodParameters(){
		return methodParameters;
	}
	
	public void run(){
		try {
			Class<utilities.ActionPack> c = ActionPack.class;
			
			for(int i = 0; i < getMethodNames().size(); i++){
				Method method = c.getDeclaredMethod(getMethodNames().get(i), String[].class);
				method.invoke(null, getMethodParameters().get(i));
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