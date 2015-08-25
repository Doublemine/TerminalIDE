package com.terminal.ide.tools;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author wanghao
 *         <p/>
 *         用于获取当前程序运行的语言环境
 *         <p/>
 *         使用方法：
 *         更新:CountryInfo.getInstance.updateCountry(Activity);
 *         获取:CountryInfo.getInstance.getCountry(Activity);
 *         <p/>
 *         date:2015-4-10
 */

public class CountryInfo {

    private SharedPreferences sharedPref;
    public static CountryInfo countryInfo;

    private CountryInfo() {
    }

    public synchronized static CountryInfo getInstance() {
        if (countryInfo == null) {
            countryInfo = new CountryInfo();
        }
        return countryInfo;
    }


    /**
     * @param activity 传入当前调用该函数的Activity
     *                 用于更新设置当前的语言
     */
    public void updateCountry(Activity activity) {

        sharedPref = activity.getSharedPreferences("local_country", Context.MODE_PRIVATE);
        SharedPreferences.Editor changecountryEditor = sharedPref.edit();
        changecountryEditor.putString("COUNTRY", activity.getResources().getConfiguration().locale.getCountry());
        changecountryEditor.apply();
    }


    /**
     * @param activity 传入当前调用该函数的Activity
     * @return 返回国家代码的缩写。如：中国 CN
     */
    public String getCountry(Activity activity) {
        String countryString;
        sharedPref = activity.getSharedPreferences("local_country", Context.MODE_PRIVATE);
        countryString = sharedPref.getString("COUNTRY", "CN");
        return countryString;

    }
}
