package com.business.gateway.utils;
import java.security.MessageDigest;
import java.util.Random;
/**
 * @author Alex
 * date:2017-04-25
 * time:01:07
 * explain:密码生成,加密,解密工具类
 */
public class PasswordUtil {
	 /*** 
	    * MD5加码 生成32位md5码 
	    */  
	   public static String string2MD5(String inStr){  
	       MessageDigest md5 = null;  
	       try{  
	           md5 = MessageDigest.getInstance("MD5");  
	       }catch (Exception e){  
	           System.out.println(e.toString());  
	           e.printStackTrace();  
	           return "";  
	       }  
	       char[] charArray = inStr.toCharArray();  
	       byte[] byteArray = new byte[charArray.length];  
	 
	       for (int i = 0; i < charArray.length; i++)  
	           byteArray[i] = (byte) charArray[i];  
	       byte[] md5Bytes = md5.digest(byteArray);  
	       StringBuffer hexValue = new StringBuffer();  
	       for (int i = 0; i < md5Bytes.length; i++){  
	           int val = ((int) md5Bytes[i]) & 0xff;  
	           if (val < 16)  
	               hexValue.append("0");  
	           hexValue.append(Integer.toHexString(val));  
	       }  
	       return hexValue.toString();  
	   }  
	   /** 
	    * 加密解密算法 执行一次加密，两次解密 
	    * alex 解密
	    */   
	   public static String convertMD5(String inStr){  
		   String s =null;
		   for(int j=0;j<2;j++){
	    	  char[] a = inStr.toCharArray();  
		       for (int i = 0; i < a.length; i++){  
		           a[i] = (char) (a[i] ^ 't');  
		       }  
		       s = new String(a);  
		       inStr=s;
	      }
	       return s;  
	   }  
	   /**
	    * 生成随机数
	    * @param length  随机数长度
	    * @return
	    */
	   public static String getCharAndNumr(int length)     
		{     
		    String val = "";     
		    Random random = new Random();     
		    for(int i = 0; i < length; i++)     
		    {     
		        String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字     
		                 
		        if("char".equalsIgnoreCase(charOrNum)) // 字符串     
		        {     
		            int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; //取得大写字母还是小写字母     
		            val += (char) (choice + random.nextInt(26));     
		        }     
		        else if("num".equalsIgnoreCase(charOrNum)) // 数字     
		        {     
		            val += String.valueOf(random.nextInt(10));     
		        }     
		    }     
		    return val;     
		}
}
