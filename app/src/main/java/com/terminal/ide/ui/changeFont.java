package com.terminal.ide.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class changeFont {

    /**
     * @param group ViewGroup
     * @param font  字体实例
     */
    public static void setFont(ViewGroup group, Typeface font) {
        int count = group.getChildCount();
        View view;
        for (int i = 0; i < count; i++) {
            view = group.getChildAt(i);
            if (view instanceof TextView || view instanceof EditText || view instanceof Button) {
                ((TextView) view).setTypeface(font);
            } else if (view instanceof ViewGroup) {
                setFont((ViewGroup) view, font);
            }
        }
    }

    public Typeface getFont(Context context, String fontpath) {
        Typeface customFontTypeface;
        customFontTypeface = Typeface.createFromAsset(context.getAssets(), fontpath);

        return customFontTypeface;
    }

    public List<View> getAllChildViews() {
        View view = activity.getWindow().getDecorView();
        return getAllChildViews(view);
    }

    public List<View> getAllChildViews(View view) {
        List<View> allchildren = new ArrayList<View>();
        if (view instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) view;
            for (int i = 0; i < vg.getChildCount(); i++) {
                View viewchild = vg.getChildAt(i);
                allchildren.add(viewchild);
                allchildren.addAll(getAllChildViews(viewchild));
            }
        }
        return allchildren;
    }


    public void setTypeface_fir() {
        List<View> children = getAllChildViews();
        for (View child : children) {
            if (child instanceof TextView) {
                TextView tv = (TextView) child;
                tv.setTypeface(typeface);
            }
        }
    }

    public static changeFont exchangefont;
    private Activity activity;
    private Typeface typeface;

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Typeface getTypeface() {
        return typeface;
    }

    public void setTypeface(Typeface typeface) {
        this.typeface = typeface;
    }

}
