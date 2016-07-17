package com.flow.pub.util;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

/**
 * 口令加密解密类
 */
public class Encipher
{
  /**
   * 原来已有的加密算法，DES加密算法　
   * 进行密码验证时，对原有密码解密后再和输入进行比较
   * 不支持中文加密
   */
  public static class DES
  {
    public final static String m_strKey1 = "zxcvbnm,./asdfg";
    public final static String m_strKeya = "cjk;";
    public final static String m_strKey2 = "hjkl;'qwertyuiop";
    public final static String m_strKeyb = "cai2";
    public final static String m_strKey3 = "[]\\1234567890-";
    public final static String m_strKeyc = "%^@#";
    public final static String m_strKey4 = "=` ZXCVBNM<>?:LKJ";
    public final static String m_strKeyd = "*(N";
    public final static String m_strKey5 = "HGFDSAQWERTYUI";
    public final static String m_strKeye = "%^HJ";
    public final static String m_strKey6 = "OP{}|+_)(*&^%$#@!~";
    
    /**
     * DES加密函数，供外部调用时使用
     * @param strPasswd 要进行加密的字符串的形参
     * @return 加密后的字符串
     */
    public static String EncodePasswd ( String strPasswd )
    {
      String  strEncodePasswd = new String("");
      int     n = 0;
      char    code = ' ';
      String  des = new String();
      String  strKey = new String();
      try
      {
        if ( (strPasswd == null) | strPasswd.length() == 0 )
        {
          strEncodePasswd = "";
          return strEncodePasswd;
        }
        strKey = m_strKey1 + m_strKey2 + m_strKey3 + m_strKey4 + m_strKey5 + m_strKey6;
        
        while (strPasswd.length() < 8)
        {
          strPasswd = strPasswd + (char) 1;
        }
        des = "";
        for ( n = 0; n <= strPasswd.length() - 1; n++ )
        {
          while (true)
          {
            code = (char) Math.rint(Math.random() * 100);
            while ((code > 0) && (((code ^ strPasswd.charAt(n)) < 0) || ((code ^ strPasswd.charAt(n)) > 90)))
            {
              code = (char) (code - 1);
            }
            char mid = 0;
            int flag;
            flag = code ^ strPasswd.charAt(n);
            if ( flag > 93 )
            {
              mid = 0;
            }
            else
            {
              mid = strKey.charAt(flag); 
            }
            //如果为中文就返回
            if ( flag >= 128 ) break; 
            if ( (code > 35) & (code < 122) & (code != 124) & (code != 39)
                & (code != 44) & (mid != 124) & (mid != 39) & (mid != 44) )
            {
              break;
            }
          }
          char temp = 0;
          temp = strKey.charAt(code ^ strPasswd.charAt(n));
          des = des + code + temp;
        }
        strEncodePasswd = des;
      }
      catch ( Exception e )
      {
        strEncodePasswd = "";
      }
      return strEncodePasswd;
    }
    
    /**
     * DES解密函数，供外部调用时使用
     * @param varCode 要进行解密的字符串的形参
     * @return 解密后的字符串
     */
    public  static String DecodePasswd ( String varCode )
    {
      int    n      = 0;
      String des    = new String();
      String strKey = new String();
      if ( (varCode == null) || (varCode.length() == 0) )
      {
        return "";
      }
      strKey = m_strKey1 + m_strKey2 + m_strKey3 + m_strKey4 + m_strKey5 + m_strKey6;
      if ( varCode.length() % 2 == 1 )
      {
        varCode = varCode + "?";
      }
      des = "";
      for ( n = 0; n <= varCode.length() / 2 - 1; n++ )
      {
        char b;
        b = varCode.charAt(n * 2);
        int a;
        a = strKey.indexOf(varCode.charAt(n * 2 + 1));
        des = des + (char) (b ^ a);
      }
      n = des.indexOf(1);
      if ( n > 0 )
      {
        return des.substring(0, n);
      }
      else
      {
        return des;
      }
    }
  }
  
  /**
   * RSA算法　
   * 验证密码时，需将原来密码解密后与输入内容对比
   * 支持中文加密
   */
  public static class RSA {
    
    public static  int     p  =  211;
    public static  int     q  =  743;
    public static  long    n  =  p*q;
    public static  long    t  =  (p-1)*(q-1);
    public static  long    d  =  155819;
    public static  long    e  =  t-1;
    // 存放原码：
    public  String  M  =  new String("");       
    
    /**
     * RSA加密函数，供外部调用使用
     * @param str 要进行加密的字符串的形参
     * @return 加密后的字符串
     */
    public static String EncodePasswd(String str)
    {
      String[] result = new String[str.length()];
      String strRes = new String();
      for(int i=0;i<str.length();i++)
      {
        String temp = str.substring(i, i+1);
        BigInteger M = BigInteger.valueOf(temp.hashCode());
        BigInteger c = M.modPow(BigInteger.valueOf(d), BigInteger.valueOf(n));
        result[i] = c.toString();
        if (i==0)
        {
          strRes = result[i];
        }
        else 
        {
          strRes = strRes+ "\t"+result[i];
        }
      }
      return strRes;
    }
    
    /**
     * RSA解密算法，供外部调用
     * @param strr 要进行解密的字符串
     * @return 解密以后的字符串
     */
    public static String DecodePasswd(String strr)
    {
      String[] str =strr.split("\t");
      String[] result = new String[str.length];
      String retResult=new String();
      for(int i=0;i<str.length;i++)
      {
        BigInteger c = new BigInteger(str[i]);
        BigInteger M = c.modPow(BigInteger.valueOf(e), BigInteger.valueOf(n));
        
        result[i] = String.valueOf((char)Integer.parseInt(M.toString()));
        retResult = retResult + result[i];
      }
      return retResult;
    }
  }
  
  /**
   * 加密函数
   * @param inStr　要进行加密的字符串
   * @return　加密以后的字符串
   * @throws  
   */
  public static String EncodePasswd( String inStr ) throws NoSuchAlgorithmException
  {
    String   str      = MD5Util.EncodeString( inStr );
    return   str;
  }
  
  /**
   * 供调用的解密函数　MD5没有该函数
   * @param inStr　被加密的字符串
   * @return　解密以后的字符串
   */
  public static String sDecodePasswd( String inStr )
  {
    String   str      = DES.DecodePasswd( inStr );
    return   str;
  }
  
  public static void main(String[] args) throws NoSuchAlgorithmException {
	  System.out.println(EncodePasswd("123456"));
}
}