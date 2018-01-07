package com.offcn.util;

public abstract class Assert {

	/**
	 * 
	 * @TODO: Assert that an object is {@code vaild} .
	 * @param object the object to check
	 * @return boolean 
	 */
	public static boolean isVaild(Object object){
		
		if(object == null || object.equals(""))
			return false;
		else
			return true;
	}
	                                
}
