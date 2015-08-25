package com.terminal.ide.ui;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.actionbarsherlock.app.SherlockActivity;
import com.terminal.ide.R;
import com.terminal.ide.Services.CloseApplication;

public class testhelpactivity extends SherlockActivity {

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        CloseApplication.getInstance().addActivity(this);
        //setContentView(R.layout.wh_about);
        setContentView(R.layout.wanghao_helpactivity);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);
        //ImageView imageView=(ImageView) findViewById(R.id.aboutActivity_imageView0);
        // Object ob=imageView.getBackground();
        // AnimationDrawable anim=(AnimationDrawable) ob;
        // anim.start();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("测试");
//        HelpExplandableListView ee=(HelpExplandableListView) findViewById(R.id.id_expandablelist);
//        int width = getWindowManager().getDefaultDisplay().getWidth(); 
//        ee.setIndicatorBounds(width-40, width-10);


    }

    @Override
    public boolean onOptionsItemSelected(com.actionbarsherlock.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

}



