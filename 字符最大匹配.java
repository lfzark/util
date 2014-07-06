package com.fuxu.backserver.utils;

public class Utils {

	
	public static String getMaxMatch(String a,String b) {   
        StringBuffer tmp = new StringBuffer();   
        String maxString = "";   
        int max = 0;   
        int len = 0;   
        char[] aArray = a.toCharArray();   
        char[] bArray = b.toCharArray();   
        int posA = 0;   
        int posB = 0;   
        while(posA<aArray.length-max) {   
            posB = 0;   
            while(posB<(bArray.length-max)) {                                   
                 if(aArray[posA]==bArray[posB]) {   
                      len = 1;   
                      tmp = new StringBuffer();   
                      tmp.append(aArray[posA]);                                           
                      while((posA+len<aArray.length)&&(posB+len<bArray.length)&&(aArray[posA+len]==bArray[posB+len])) {   
                           tmp.append(aArray[posA+len]);   
                           len++;   
                      }   
                      if(len>max) {   
                            max = len;   
                            maxString = tmp.toString();   
                      }   
                 }   
                      posB++;   
            }   
                      posA++;   
         }           
            return maxString;                       
    } 
	
	public static void main(String[] args) {
		System.out.println(getMaxMatch("ds2f3dsf","dsfwer"));
	}
}
