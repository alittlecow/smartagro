package com.sywl.utils;

public class EncodedUtils {

    public static String asciiToString(String value)  
    {  
        StringBuffer sbu = new StringBuffer();  
        String[] chars = value.split(",");  
        for (int i = 0; i < chars.length; i++) {  
            sbu.append((char) Integer.parseInt(chars[i]));  
        }  
        return sbu.toString();  
    }  
	
}
