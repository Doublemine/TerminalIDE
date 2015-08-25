/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.terminal.ide.startup.tutorial;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.terminal.ide.R;

/**
 * @author Spartacus Rex
 */
public class tutlistitem extends LinearLayout {

    int mLayoutID;

    public tutlistitem(Context zContext, int helpUiIntroduction, int helpUiIntroduction2, int zLayoutID, int zIconID) {
        super(zContext);

        //Inflate the View
        View.inflate(zContext, R.layout.tutorial_list, this);

        //ImageView im = (ImageView)findViewById(R.id.tutlist_icon);
        //im.setImageResource(zIconID);

        TextView tv = (TextView) findViewById(R.id.tutlist_chapter);
        //tv.setTextAppearance(zContext,android.R.style.TextAppearance_Large);
        tv.setText(helpUiIntroduction);


        tv = (TextView) findViewById(R.id.tutlist_verse);
        // tv.setTextAppearance(zContext, android.R.style.TextAppearance_Small);
        tv.setText(helpUiIntroduction2);

        mLayoutID = zLayoutID;
    }

    public int getLayoutID() {
        return mLayoutID;
    }
}
