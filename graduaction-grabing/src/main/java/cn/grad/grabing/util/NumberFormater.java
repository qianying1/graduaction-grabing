package cn.grad.grabing.util;

public class NumberFormater {

    public static String pareseNumberStr(String str){
        str=str.trim();
        StringBuffer str1=new StringBuffer("");
        if(str!=null&&!"".equals(str)){
            for(int i=0;i<str.length();i++){
                if(str.charAt(i)>=48&&str.charAt(i)<=57){
                    str1.append(str.charAt(i));
                }
            }
        }
        return str1.toString();
    }
}
