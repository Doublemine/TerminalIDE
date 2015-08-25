/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.terminal.ide.startup.tutorial;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockActivity;
//import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.terminal.ide.R;
import com.terminal.ide.Term;
import com.terminal.ide.Services.CloseApplication;
import com.terminal.ide.ui.aboutActivity;


/**
 * @author Spartacus Rex
 * @author wanghao
 */
public class tutlist extends SherlockActivity { //ListActivity{

    private ListView lv;

    @Override
    public void onCreate(Bundle zBundle) {
        super.onCreate(zBundle);
        CloseApplication.getInstance().addActivity(this);
        setContentView(R.layout.wh_help_listview);

        /**
         * 透明顶栏实现代码
         * android 4.4特性
         * @author wanghao
         * 暂时不考虑加入


        if(android.os.Build.VERSION.SDK_INT>18)
        {
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(this);  
        tintManager.setStatusBarTintEnabled(true);  
        tintManager.setStatusBarTintResource(R.color.wh_bar);
         */

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);
        getSupportActionBar().setTitle(getResources().getString(R.string.wh_actionbar_title_help));


        lv = (ListView) findViewById(R.id.wh_help_lv);
        lv.setAdapter(new tutadaptor(this));


        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                tutlistitem tl = (tutlistitem) view;
                int layoutid = tl.getLayoutID();

                //Is this VIM
                if (layoutid == R.layout.tut_first) {
                    //Create the specific VIM help list
                    startActivity(new Intent(tutlist.this, vimlist.class));

                } else if (layoutid == R.layout.tut_remote) {
                    //Create the specific VIM help list
                    startActivity(new Intent(tutlist.this, remotelist.class));

                } else {
                    //Now create an intent and return..
                    Intent res = new Intent(tutlist.this, tutview.class);
                    res.putExtra("com.terminal.prodj.tutorial", layoutid);
                    startActivity(res);
                }


            }
        });

    }

    //@Override
    protected void onItemClick(ListView l, View v, int zPosition, long zID) {
        tutlistitem tl = (tutlistitem) v;
        int layoutid = tl.getLayoutID();

        //Is this VIM
        if (layoutid == R.layout.tut_first) {
            //Create the specific VIM help list
            startActivity(new Intent(this, vimlist.class));

        } else if (layoutid == R.layout.tut_remote) {
            //Create the specific VIM help list
            startActivity(new Intent(this, remotelist.class));

        } else {
            //Now create an intent and return..
            Intent res = new Intent(this, tutview.class);
            res.putExtra("com.terminal.prodj.tutorial", layoutid);
            startActivity(res);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {

        //MenuInflater inflater=getMenuInflater();
        getSupportMenuInflater().inflate(R.menu.common_activity_action_menu, menu);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(com.actionbarsherlock.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;

            }
            case R.id.common_activity_action_about: {
                startActivity(new Intent(tutlist.this, aboutActivity.class));
                break;
            }
            case R.id.common_activity_action_git: {
                Intent open_Git_tut = new Intent();
                open_Git_tut.setAction("android.intent.action.VIEW");
                Uri CONTENT_URI_BROWSERS;
                if (getResources().getConfiguration().locale.getCountry().equals("CN")) {
                    CONTENT_URI_BROWSERS = Uri.parse("http://git.oschina.net/progit/");
                } else {
                    CONTENT_URI_BROWSERS = Uri.parse("http://git-scm.com/book/en/v2");
                }
                open_Git_tut.setData(CONTENT_URI_BROWSERS);
                open_Git_tut.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
                startActivity(open_Git_tut);
                break;
            }
            case R.id.common_activity_action_tryit: {
                startActivity(new Intent(tutlist.this, Term.class));
                break;
            }

        }
        return false;

    }
}
