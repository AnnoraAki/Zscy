package com.example.cynthia.zscy.Utils;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;
import android.widget.TextView;

public class TextUtil {
    /**
     * to make specific str make another color
     * @param str change color
     * @param ch1 start char
     * @param ch2 end char
     * @param color the color you want
     * @param tv EditText you make
     */
    public static void setTVColor(String str , char ch1 , char ch2 , int color , TextView tv){
        if (str.length() - str.replace("#", "").length() == 2){
            int a = str.indexOf(ch1); //从字符ch1的下标开始
            int b = str.indexOf(ch2,1)+2; //到字符ch2的下标+1结束,因为SpannableStringBuilder的setSpan方法中区间为[ a,b )左闭右开
            SpannableStringBuilder builder = new SpannableStringBuilder(str);
            builder.setSpan(new ForegroundColorSpan(color), a, b, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv.setText(builder);
        }
    }
}
