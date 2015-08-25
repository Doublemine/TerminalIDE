/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.terminal.ide.startup.tutorial;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockActivity;
import com.terminal.ide.R;
import com.terminal.ide.Services.CloseApplication;

/**
 * @author Spartacus Rex
 * @author changge wanghao
 */
public class vimlist extends SherlockActivity {//ListActivity {

    private ListView lv;

    @Override
    public void onCreate(Bundle zBundle) {
        super.onCreate(zBundle);
        CloseApplication.getInstance().addActivity(this);
        setContentView(R.layout.wh_help_listview);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);
        getSupportActionBar().setTitle(getResources().getString(R.string.wh_actionbar_title_tut));
        lv = (ListView) findViewById(R.id.wh_help_lv);
        lv.setAdapter(new vimadaptor(this));
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                tutlistitem tl = (tutlistitem) view;
                int layoutid = tl.getLayoutID();

                //Now create an intent and return..
                Intent res = new Intent(vimlist.this, tutview.class);
                res.putExtra("com.terminal.prodj.tutorial", layoutid);
                startActivity(res);

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(com.actionbarsherlock.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

        }
        return false;

    }
}
