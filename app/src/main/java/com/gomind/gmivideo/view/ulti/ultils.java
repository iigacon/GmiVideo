package com.gomind.gmivideo.view.ulti;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Duc on 9/17/16.
 */
public class ultils {
    public static String convertHour(int min){
        if(min>=60) {
            return min/60+"h "+min%60+"min";
        }else return min+" min";
    }
    public static String convertDate(String inputDate){
        SimpleDateFormat parserSDF=new SimpleDateFormat("dd-mm-yyyy", Locale.US);
        try {
            parserSDF.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parserSDF.toString();
    }
}
