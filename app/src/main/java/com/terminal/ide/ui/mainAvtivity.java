package com.terminal.ide.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.terminal.ide.R;
import com.terminal.ide.Services.CloseApplication;
import com.terminal.ide.Term;
import com.terminal.ide.TermService;
import com.terminal.ide.startup.TerminalIDEPrefs;
import com.terminal.ide.startup.installer;
import com.terminal.ide.tools.CountryInfo;

import java.lang.reflect.Field;

//import android.support.v7.app.ActionBarActivity;

public class mainAvtivity extends SherlockActivity {


    /**
     * @author wanghao
     */


    Dialog mConfirmDialog;
    Dialog mInstallDialog;
    Intent mTSIntent;

    //声明主界面布局需要的lv和适配器
    private ListView MainListView;
    private whAdapter adapter;


    /* listview标志位
      * 0.运行
      * 1.安装
      * 2.键盘
      * 3.设置
      * 4.帮助
      * 5.退出
      */
    private final int position_run = 0;
    private final int position_install = 1;
    private final int position_keyboard = 2;
    private final int position_setting = 3;
    private final int position_help = 4;
    private final int position_exit = 5;
    /* listview标志位
        * 0.运行
        * 1.安装
        * 2.键盘
        * 3.设置
        * 4.帮助
        * 5.退出
        */
    private final long id_run = 0;
    private final long id_install = 1;
    private final long id_keyboard = 2;
    private final long id_setting = 3;
    private final long id_help = 4;
    private final long id_exit = 5;

    /*
     * 用于判断gcc是否安装
     *
     */
    private boolean judege_install;//判断gcc是否安装
    private final int install_activity_requestCode = 412;//Activity返回码

    private Intent open_Git_tut;
    // private Intent open_about;
    //private final Typeface mfont=Typeface.createFromAsset(getAssets(), "font/huakangyuanti.TTF");

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        CloseApplication.getInstance().addActivity(this);

        setContentView(R.layout.wh_main_activity);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        CountryInfo.getInstance().updateCountry(this);
        //Log.e("测试", "运行结果为-->"+ CountryInfo.getInstance().getCountry(this)+"<---");

        //启动后台服务
        mTSIntent = new Intent(this, TermService.class);
        startService(mTSIntent);

        //构建功能界面
        adapter = new whAdapter(this);
        MainListView = (ListView) findViewById(R.id.wh_main_lv);
        MainListView.setAdapter(adapter);
        adapter.add(new MainItem(R.string.wh_main_run_title, R.string.wh_main_run_summary));
        adapter.add(new MainItem(R.string.wh_main_install_title, R.string.wh_main_install_summary));
        adapter.add(new MainItem(R.string.wh_main_keyboard_title, R.string.wh_main_keyboard_summary));
        adapter.add(new MainItem(R.string.wh_main_setting_title, R.string.wh_main_setting_summary));
        adapter.add(new MainItem(R.string.wh_main_help_title, R.string.wh_main_help_summary));
        adapter.add(new MainItem(R.string.wh_main_exit_title, R.string.wh_main_exit_summary));

        MainListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == position_run && id == id_run) {
                    startActivity(new Intent(mainAvtivity.this, Term.class));
                } else if (position == position_install && id == id_install) {
                    startActivityForResult(new Intent(mainAvtivity.this, installSystemActivity.class), install_activity_requestCode);
                } else if (position == position_keyboard && id == id_keyboard) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showInputMethodPicker();
                } else if (position == position_setting && id == id_setting) {
                    startActivity(new Intent(mainAvtivity.this, TerminalIDEPrefs.class));
                } else if (position == position_help && id == id_help) {
                    //startActivity(new Intent(mainAvtivity.this, tutlist.class));
                    startActivity(new Intent(mainAvtivity.this, testhelpactivity.class));
                } else if (position == position_exit && id == id_exit) {
                    mConfirmDialog.show();
                }


            }
        });


        AlertDialog.Builder build = new AlertDialog.Builder(this);
        build.setTitle(R.string.main_ui_java_stop_title);
        build.setMessage(R.string.main_ui_java_stop_content);
        build.setCancelable(true);
        build.setPositiveButton(R.string.main_ui_java_stop_surebutton, new android.content.DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                stopService(mTSIntent);
                finish();
                mConfirmDialog.dismiss();
            }
        });
        build.setNegativeButton(R.string.main_ui_java_stop_cancelbutton, new android.content.DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                mConfirmDialog.dismiss();
            }
        });
        mConfirmDialog = build.create();

        build = new AlertDialog.Builder(this);
        build.setTitle(R.string.main_ui_java_newsystem_dialog_title);
        build.setMessage(R.string.main_ui_java_newsystem_dialog_content);
        build.setCancelable(true);
        build.setPositiveButton(R.string.main_ui_java_newsystem_dialog_button_yes, new android.content.DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                //Install the system
                startActivity(new Intent(mainAvtivity.this, installer.class));

                mConfirmDialog.dismiss();
            }
        });
        build.setNegativeButton(R.string.main_ui_java_newsystem_dialog_button_no, new android.content.DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                //Start the Terminal
                startActivity(new Intent(mainAvtivity.this, Term.class));

                mConfirmDialog.dismiss();
            }
        });
        mInstallDialog = build.create();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.v("SpartacusRex", "IntroScreen onConfigurationChanged!!!!");
    }


    /*Item类
     * title显示功能
     * summary显示详细描述
     * 
     */
    class MainItem//自定义item对象
    {
        public final String title;
        public final String descrption;

        protected MainItem(int titleResId, int descrptionResId) {
            this.title = getString(titleResId);
            this.descrption = getString(descrptionResId);
        }

        public String toString() {
            return title;
        }
    }

    //自定义适配器
    class whAdapter extends ArrayAdapter<MainItem> {

        public whAdapter(Context context) {

            super(context, R.layout.wh_newmainitem, android.R.id.text1);
            // TODO Auto-generated constructor stub
        }


        //用于显示title和summary
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            // TODO Auto-generated method stub
            View view = super.getView(position, convertView, parent);
            MainItem item = getItem(position);
            TextView summary = (TextView) view.findViewById(android.R.id.text2);
            summary.setText(item.descrption);
            //summary.setTypeface(mfont);

            boolean checksysteminstall = true;
            if (position == position_run) {
                //判断是否安装gcc
                SharedPreferences check = PreferenceManager.getDefaultSharedPreferences(mainAvtivity.this);
                int cureentnum = check.getInt("CURRENT_SYSTEM_NUM", -1);
                if (cureentnum < installer.CURRENT_INSTALL_SYSTEM_NUM) {
                    checksysteminstall = false;
                    judege_install = false;

                } else if (cureentnum >= installer.CURRENT_INSTALL_SYSTEM_NUM) {
                    judege_install = true;
                    checksysteminstall = true;
                }
            }

            view.findViewById(R.id.main_system_notinstall_text).setVisibility(!checksysteminstall ? View.VISIBLE : View.GONE);
            return view;
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == install_activity_requestCode && judege_install == false) {

            SharedPreferences check = PreferenceManager.getDefaultSharedPreferences(mainAvtivity.this);
            int cureentnum = check.getInt("CURRENT_SYSTEM_NUM", -1);
            if (cureentnum >= installer.CURRENT_INSTALL_SYSTEM_NUM) {
                judege_install = true;
                adapter.notifyDataSetChanged();


            }

        }
    }


    @Override
    public boolean onPrepareOptionsMenu(com.actionbarsherlock.view.Menu menu) {
        ViewConfiguration config = ViewConfiguration.get(this);
        try {
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if (menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception e) {
            Log.e("onPrepareOptionsMenu", e.getMessage(), e);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    //创建菜单
    @Override
    public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {


        getSupportMenuInflater().inflate(R.menu.main_activity_action_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //选项菜单响应事件
    @Override
    public boolean onOptionsItemSelected(com.actionbarsherlock.view.MenuItem item) {
        switch (item.getItemId()) {

            case R.id.main_activity_action_about: {
                startActivity(new Intent(mainAvtivity.this, aboutActivity.class));
                break;
            }
            case R.id.main_activity_action_git: {
                open_Git_tut = new Intent();
                open_Git_tut.setAction("android.intent.action.VIEW");
                Uri CONTENT_URI_BROWSERS;
                if (CountryInfo.getInstance().getCountry(this).equals("CN")) {
                    CONTENT_URI_BROWSERS = Uri.parse("http://git.oschina.net/progit/");
                } else {
                    CONTENT_URI_BROWSERS = Uri.parse("http://git-scm.com/book/en/v2");
                }
                open_Git_tut.setData(CONTENT_URI_BROWSERS);
                open_Git_tut.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
                startActivity(open_Git_tut);
                break;
            }
            case R.id.main_activity_action_exit: {
                mConfirmDialog.show();
                break;
            }


        }
        return super.onOptionsItemSelected(item);
    }

}
