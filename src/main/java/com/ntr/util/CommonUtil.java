package com.ntr.util;

public class CommonUtil {

    public static String generateDateScript(Integer year,Integer month, String field){
        if(year == null && month == null){
            return null;
        }
        if(year != null && month == null){
            return "doc['"+field+"'].value.getYear() == "+year;
        }else if(year == null){
            return "doc['"+field+"'].value.getMonthValue() == "+month;
        }else{
            return "doc['"+field+"'].value.getYear() == "+year + " && " +
                    "doc['"+field+"'].value.getMonthValue() == "+month;
        }
    }

    public static String generateVerificationCode(){
        StringBuilder sb = new StringBuilder();
        for(int i = 1;i <= 6;i++){
            sb.append((int)(Math.random() * 10));
        }
        return sb.toString();
    }
}
