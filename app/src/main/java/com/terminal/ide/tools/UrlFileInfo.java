package com.terminal.ide.tools;

import com.terminal.ide.R;

import android.content.Context;

public class UrlFileInfo {

    /**
     * @param context
     * @param Rstring1 R.string文件，格式:R.string.xxxx
     * @param Rstring2 R.string文件，格式:R.string.xxxx
     * @return 返回两个string文件结合过后的字符串
     */
    public static String getHtmlFileName(Context context, int Rstring1, int Rstring2) {
        String htmlfileurl = context.getResources().getString(Rstring1) + context.getResources().getString(Rstring2);
        return htmlfileurl;
    }

    /**
     * @param context
     * @param Rstring R.string文件，格式:R.string.xxxx
     * @return 返回两个string文件结合过后的字符串，其中一个已经被定义为R.string.html_file_url
     */
    public static String getHtmlFileName(Context context, int Rstring) {
        String htmlfileurl = context.getResources().getString(R.string.aboutActivity_app_name) + context.getResources().getString(R.string.aboutActivity_app_name);
        return htmlfileurl;
    }
}
