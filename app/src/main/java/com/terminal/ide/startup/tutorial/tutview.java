/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.terminal.ide.startup.tutorial;

import com.actionbarsherlock.app.SherlockActivity;
import com.terminal.ide.R;
import com.terminal.ide.Term;
import com.terminal.ide.Services.CloseApplication;
import com.terminal.ide.ui.aboutActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
//import android.support.v7.app.ActionBarActivity;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

/**
 * @author Spartacus Rex
 */
public class tutview extends SherlockActivity {

    @Override
    public void onCreate(Bundle zBundle) {
        super.onCreate(zBundle);
        CloseApplication.getInstance().addActivity(this);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Which tutorial is this..
        int layoutID = getIntent().getExtras().getInt("com.terminal.prodj.tutorial");
        //Set it..
        setContentView(layoutID);

        switch (layoutID) {
            case R.layout.changelog: {
                getSupportActionBar().setTitle(R.string.changlog_title);
                break;
            }

            case R.layout.tut_intro: {
                getSupportActionBar().setTitle(R.string.main_help_introduction);
                break;
            }

            case R.layout.tut_keyboard: {
                getSupportActionBar().setTitle(R.string.help_ui_keyboard);
                break;
            }

            case R.layout.tut_first: {
                getSupportActionBar().setTitle(R.string.help_ui_tutorial);
                break;
            }

            case R.layout.tut_first_1: {
                getSupportActionBar().setTitle(R.string.help_tutorial_demo1_summary);
                break;
            }

            case R.layout.tut_first_2: {
                getSupportActionBar().setTitle(R.string.help_tutorial_demo2_summary);
                break;
            }

            case R.layout.tut_first_3: {
                getSupportActionBar().setTitle(R.string.help_tutorial_demo3_summary);
                break;
            }

            case R.layout.tut_first_4: {
                getSupportActionBar().setTitle(R.string.help_tutorial_demo4_summary);
                break;
            }

            case R.layout.tut_first_5: {
                getSupportActionBar().setTitle(R.string.help_tutorial_demo5_summary);
                break;
            }

            case R.layout.tut_first_6: {
                getSupportActionBar().setTitle(R.string.help_tutorial_demo6_summary);
                break;
            }
            case R.layout.tut_first_7: {
                getSupportActionBar().setTitle(R.string.help_tutorial_demo7_summary);
                break;
            }

            case R.layout.tut_first_8: {
                getSupportActionBar().setTitle(R.string.help_tutorial_demo8_summary);
                break;
            }

            case R.layout.tut_first_9: {
                getSupportActionBar().setTitle(R.string.help_tutorial_demo9_summary);
                break;
            }

            case R.layout.tut_bash: {
                getSupportActionBar().setTitle(R.string.help_ui_bash);
                break;
            }

            case R.layout.tut_busybox: {
                getSupportActionBar().setTitle(R.string.help_ui_busybox);
                break;
            }

            case R.layout.tut_vim: {
                getSupportActionBar().setTitle(R.string.help_ui_vim);
                break;
            }

            case R.layout.tut_javac: {
                getSupportActionBar().setTitle(R.string.help_ui_javakits);
                break;
            }

            case R.layout.tut_gcc: {
                getSupportActionBar().setTitle(R.string.help_ui_gcc);
                break;
            }

            case R.layout.tut_remote: {
                getSupportActionBar().setTitle(R.string.help_ui_overconnect);
                break;
            }

            case R.layout.tut_telnet: {
                getSupportActionBar().setTitle(R.string.help_overconnect_telnet);
                break;
            }

            case R.layout.tut_ssh: {
                getSupportActionBar().setTitle(R.string.help_overconnect_ssh);
                break;
            }

            case R.layout.tut_rsync: {
                getSupportActionBar().setTitle(R.string.help_overconnect_rsync);
                break;
            }

            case R.layout.tut_git: {
                getSupportActionBar().setTitle(R.string.help_ui_git);
                TextView git_internet = (TextView) findViewById(R.id.mixer_deck1_text);
                git_internet.setMovementMethod(LinkMovementMethod.getInstance());//设置Git链接点击可打开
                break;
            }

            case R.layout.tut_mc: {
                getSupportActionBar().setTitle(R.string.help_ui_mc_summary);
                break;
            }

            case R.layout.tut_tmux: {
                getSupportActionBar().setTitle(R.string.help_ui_tmux_summary);
                break;
            }

            case R.layout.tut_bitchx: {
                getSupportActionBar().setTitle(R.string.help_ui_bitchx);
                break;
            }

            case R.layout.tut_expand: {
                getSupportActionBar().setTitle(R.string.help_tutorial_expand);
                break;
            }

            case R.layout.tut_trouble: {
                getSupportActionBar().setTitle(R.string.help_ui_trouble);
                break;
            }

            case R.layout.tut_thanks: {
                getSupportActionBar().setTitle(R.string.help_ui_thanks_summary);
                break;
            }

            case R.layout.tut_gpl: {
                getSupportActionBar().setTitle(R.string.help_ui_gplv2);
                break;
            }

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
                startActivity(new Intent(tutview.this, aboutActivity.class));
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
                startActivity(new Intent(tutview.this, Term.class));
                break;
            }

        }
        return false;

    }
}
