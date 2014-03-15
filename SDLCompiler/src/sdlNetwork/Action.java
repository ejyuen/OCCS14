package sdlNetwork;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utilities.ActionPack;

public class Action implements Runnable{
	private String name;
	private ArrayList<String> methodNames;
	private ArrayList<String[]> methodParameters;
	private static Pattern methodPattern = Pattern.compile("[/n][/w]+[(]");
	private static Pattern parameterPattern = Pattern.compile("[(][^)]+");
	
	
	public Action(String name){
		this.name = name;
		init();
	}
	
	private void init(){
		//set methodNames and Parameters here;
		Matcher methodMatcher = methodPattern.matcher(name);
		Matcher parameterMatcher = parameterPattern.matcher(name);		
		
		while(methodMatcher.find()){
			String rawMethod = methodMatcher.group();
			rawMethod = rawMethod.substring(1,rawMethod.length()-1);
			methodNames.add(rawMethod);
		}
		
		while(parameterMatcher.find()){
			String rawParameter = parameterMatcher.group();
			String[] parameterArray = rawParameter.split(",");
			for(int i=0; i < parameterArray.length -1; i++){
				parameterArray[i].trim();
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