/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.terminal.ide.startup;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.R.color;
import com.actionbarsherlock.app.SherlockActivity;
//import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.terminal.ide.R;
import com.terminal.ide.TermService;
import com.terminal.ide.Services.CloseApplication;
import com.terminal.ide.startup.setup.filemanager;
import com.terminal.ide.startup.tutorial.tutlist;
import com.terminal.ide.startup.tutorial.tutview;
import com.terminal.ide.ui.aboutActivity;


import java.io.File;

/**
 * @author Spartacus Rex
 */

//@TargetApi(Build.VERSION_CODES.KITKAT)
public class installer extends SherlockActivity implements OnClickListener {


    public static int CURRENT_INSTALL_SYSTEM_NUM = 20;

    public static String CURRENT_INSTALL_SYSTEM = "System v2.0";
    public static String CURRENT_INSTALL_ASSETFILE = "system-2.0.tar.gz.mp3";

    private ProgressDialog mInstallProgress;

    boolean mOverwriteAll = false;
    public Handler mInstallHandler = new Handler() {
        @Override
        public void handleMessage(Message zMsg) {
            Bundle msg = zMsg.getData();
            //Is it over
            if (msg.containsKey("close_install")) {
                //Shut it down..
                mInstallProgress.dismiss();

                //Set the Text
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(installer.this);
                String current = prefs.getString("CURRENT_SYSTEM", getString(R.string.main_install_java_no_system_installed));
                TextView tv = (TextView) findViewById(R.id.install_sys);
                tv.setText(getString(R.string.main_install_java_textview_current) + current + "\n" + getString(R.string.main_install_java_textview_current) + CURRENT_INSTALL_SYSTEM);

                //Start the service
                Intent mTSIntent = new Intent(installer.this, TermService.class);
                startService(mTSIntent);

            } else if (msg.containsKey("error")) {
                String info = msg.getString("error");
                mInstallProgress.setMessage(info);

                Toast.makeText(installer.this, getString(R.string.main_install_java_install_show_error) + info, Toast.LENGTH_LONG).show();

            } else {
                String info = msg.getString("info");
                mInstallProgress.setMessage(info);
            }
        }
    };

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        CloseApplication.getInstance().addActivity(this);
        //Set the right Content
        setContentView(R.layout.install);


        //显示actionbat标题
        getSupportActionBar().setTitle(getResources().getString(R.string.wh_actionbar_title_install));


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Get the current system
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String current = prefs.getString("CURRENT_SYSTEM", getString(R.string.main_install_java_no_system_installed));
        //int currentnum   =  prefs.getInt("CURRENT_SYSTEM_NUM", 0);
        String avail = CURRENT_INSTALL_SYSTEM;

        TextView tv = (TextView) findViewById(R.id.install_sys);
        tv.setTypeface(Typeface.MONOSPACE);
        tv.setText(getString(R.string.main_install_java_textview_current) + current + "\n" + getString(R.string.main_install_java_textview_available) + avail);

        Button but = (Button) findViewById(R.id.install_changelog);
        but.setOnClickListener(this);
        but = (Button) findViewById(R.id.install_start);
        but.setOnClickListener(this);

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        mInstallProgress = new ProgressDialog(this);
        mInstallProgress.setTitle(getString(R.string.main_install_java_install_dialog_title));
        mInstallProgress.setMessage(getString(R.string.main_install_java_install_dialog_content));
        mInstallProgress.setCancelable(false);

        return mInstallProgress;
    }

    @SuppressWarnings("deprecation")
    public void onClick(View zButton) {
        if (zButton == findViewById(R.id.install_changelog)) {
            //Show the Change LOG
            Intent res = new Intent(this, tutview.class);
            res.putExtra("com.terminal.prodj.tutorial", R.layout.changelog);
            startActivity(res);

        } else if (zButton == findViewById(R.id.install_start)) {
            //Extract all the files..
            showDialog(0);

            //Shut down the service
            Intent mTSIntent = new Intent(this, TermService.class);
            stopService(mTSIntent);

            //Overwrite
            CheckBox over = (CheckBox) findViewById(R.id.install_overwrite);
            mOverwriteAll = over.isChecked();

            //Start the Installer
            Thread tt = new Thread() {
                public void run() {
                    //Set the Message
                    Message msg = new Message();
                    msg.getData().putString("info", getString(R.string.main_install_java_install_putstring_startingsysteminstall));
                    mInstallHandler.sendMessage(msg);

                    //Main HOME FOlder
                    File home = installer.this.getFilesDir();
                    Log.d("wanghao", "home目录:" + home.toString());


                    //Where to store the system number
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(installer.this);

                    try {
                        //Create a working Directory
                        //创建home目录并创建tmp缓存文件
                        Log.d("wanghao", "创建home目录并创建tmp缓存文件");
                        File tmp = new File(home, "tmp");
                        if (!tmp.exists()) {
                            tmp.mkdirs();
                        }
                        Log.d("wanghao", "tmp目录:" + tmp);

                        //Working directory
                        //创建tmp目录，并创建缓存文件
                        Log.d("wanghao", "创建tmp目录，并创建缓存文件");
                        File worker = new File(tmp, "WORK_" + System.currentTimeMillis());
                        if (!worker.exists()) {
                            worker.mkdirs();
                        }
                        Log.d("wanghao", "work目录:" + worker.toString());
                        Log.d("wanghao", "目录创建完成");
                        //Extract the assets..
                        //解压文件
                        msg = new Message();
                        msg.getData().putString("info", getString(R.string.mian_install_java_install_putstring_preparingtar));
                        mInstallHandler.sendMessage(msg);

                        File busytar = new File(worker, "busybox");
                        if (busytar.exists()) {
                            busytar.delete();
                        }
                        Log.d("wanghao", "busytar路径:" + busytar.toString());
                        //Extract BusyBox, need it just for ln and cp
                        //将busybox.mp3文件去除.mp3后缀然后复制到busytar对象所指定的路径之下
                        filemanager.extractAsset(installer.this, "busybox.mp3", busytar);

                        //Set up a simple environment
                        String[] env = new String[2];
                        env[0] = "PATH=/sbin:/vendor/bin:/system/sbin:/system/bin:/system/xbin";
                        env[1] = "LD_LIBRARY_PATH=/vendor/lib:/system/lib";

                        //Set executable - This *needs* chmod on the phone..
//                      Process pp = Runtime.getRuntime().exec("chmod 770 "+busytar.getPath());
                        Process pp = Runtime.getRuntime().exec("chmod 770 " + busytar.getPath(), env, home);
                        Log.d("wanghao", "执行exec,具体参数:" + "chmod 770 " + busytar.getPath().toString() + "envp:" + env.toString() + "home:" + home.toString());
                        pp.waitFor();

                        msg = new Message();
                        msg.getData().putString("info", getString(R.string.main_install_java_install_putstring_preparing) + CURRENT_INSTALL_SYSTEM + " ..");
                        mInstallHandler.sendMessage(msg);

                        File systar = new File(worker, "system.tar.gz");
                        Log.d("wanghao", "systar路径:" + systar.toString());
                        filemanager.extractAsset(installer.this, CURRENT_INSTALL_ASSETFILE, systar);

                        //Now start
                        msg = new Message();
                        msg.getData().putString("info", getString(R.string.main_install_java_install_putstring_removeoldsystem));
                        mInstallHandler.sendMessage(msg);

                        File system = new File(home, "system");
                        Log.d("wanghao", "system路径:" + system.toString());
                        filemanager.deleteFolder(system);

                        msg = new Message();
                        msg.getData().putString("info", getString(R.string.main_install_java_install_putstring_takeaminute));
                        mInstallHandler.sendMessage(msg);

                        //Now run the extract command
//                        pp = Runtime.getRuntime().exec(busytar.getPath()+" tar -C "+home.getPath()+" -xzf "+systar.getPath());
                        pp = Runtime.getRuntime().exec(busytar.getPath() + " tar -C " + home.getPath() + " -xzf " + systar.getPath(), env, home);
                        Log.d("wanghao", "执行exec,具体参数:" + busytar.getPath().toString() + " tar -C " + home.getPath().toString() + " -xzf " + systar.getPath().toString() + "ENV:" + env + "HOME:" + home.toString());
                        pp.waitFor();

                        msg = new Message();
                        msg.getData().putString("info", getString(R.string.main_install_java_install_putstring_installbusybox));
                        mInstallHandler.sendMessage(msg);

                        //Now run the extract command
                        File bindir = new File(system, "bin");
                        Log.d("wanghao", "bindir路径:" + bindir.toString());
                        File bbindir = new File(bindir, "bbdir");
                        Log.d("wanghao", "bbindir路径:" + bbindir.toString());
                        if (!bbindir.exists()) {
                            bbindir.mkdirs();
                        }

                        File busybox = new File(bindir, "busybox");
                        Log.d("wanghao", "busybox路径:" + busybox.toString());
//                        pp = Runtime.getRuntime().exec(busybox.getPath()+" --install -s "+bbindir.getPath());
                        Log.d("wanghao", "即将执行exec，参数--->:" + busybox.getPath().toString() + " --install -s " + bbindir.getPath().toString() + "ENV:" + env + "HOME:" + home.toString());
                        pp = Runtime.getRuntime().exec(busybox.getPath() + " --install -s " + bbindir.getPath(), env, home);
                        // pp = Runtime.getRuntime().exec("/data/data/com.terminal.ide/files/system/bin/busybox --install -s /data/data/com.terminal.ide/files/system/bin/bbdir",env,home);
                        Log.d("wanghao", "执行exec，具体参数:" + busybox.getPath().toString() + " --install -s " + bbindir.getPath().toString() + "ENV:" + env + "HOME:" + home.toString());
                        pp.waitFor();

                        //Now delete the SU link.. too much confusion..
                        File su = new File(bbindir, "su");
                        Log.d("wanghao", "su路径:" + su.toString());
                        su.delete();

                        //Now copy some initial files..
                        msg = new Message();
                        msg.getData().putString("info", getString(R.string.main_install_java_install_putstring_copystartipfile));
                        mInstallHandler.sendMessage(msg);

                        //bashrc
                        File bashrc = new File(system, "bashrc");
                        File bashrcu = new File(home, ".bashrc");
                        if (!bashrcu.exists() || mOverwriteAll) {
//                            pp = Runtime.getRuntime().exec(busytar.getPath()+" cp -f "+bashrc.getPath()+" "+bashrcu.getPath());
                            pp = Runtime.getRuntime().exec(busytar.getPath() + " cp -f " + bashrc.getPath() + " " + bashrcu.getPath(), env, home);
                            pp.waitFor();
                        }

                        //nanorc
                        File nanorc = new File(system, "nanorc");
                        File nanorcu = new File(home, ".nanorc");
                        if (!nanorcu.exists() || mOverwriteAll) {
//                            pp = Runtime.getRuntime().exec(busytar.getPath()+" cp -f "+nanorc.getPath()+" "+nanorcu.getPath());
                            pp = Runtime.getRuntime().exec(busytar.getPath() + " cp -f " + nanorc.getPath() + " " + nanorcu.getPath(), env, home);
                            pp.waitFor();
                        }

                        //TMUX
                        File tmuxrc = new File(system, "tmux.conf");
                        File tmuxrcu = new File(home, ".tmux.conf");
                        if (!tmuxrcu.exists() || mOverwriteAll) {
//                            pp = Runtime.getRuntime().exec(busytar.getPath()+" cp -f "+tmuxrc.getPath()+" "+tmuxrcu.getPath());
                            pp = Runtime.getRuntime().exec(busytar.getPath() + " cp -f " + tmuxrc.getPath() + " " + tmuxrcu.getPath(), env, home);
                            pp.waitFor();
                        }

                        //Midnight
                        File ini = new File(system, "mc.ini");
                        File conf = new File(home, ".config");
                        File confmc = new File(conf, "mc");
                        if (!confmc.exists()) {
                            confmc.mkdirs();
                        }
                        File mcini = new File(confmc, "ini");
                        if (!mcini.exists() || mOverwriteAll) {
//                            pp = Runtime.getRuntime().exec(busytar.getPath()+" cp -f "+ini.getPath()+" "+mcini.getPath());
                            pp = Runtime.getRuntime().exec(busytar.getPath() + " cp -f " + ini.getPath() + " " + mcini.getPath(), env, home);
                            pp.waitFor();
                        }

                        //The Inputrc is always over-ridden.. ?
                        File inputrc = new File(system, "inputrc");
                        File inputrcu = new File(home, ".inputrc");
//                        pp = Runtime.getRuntime().exec(busytar.getPath()+" cp -f "+inputrc.getPath()+" "+inputrcu.getPath());
                        pp = Runtime.getRuntime().exec(busytar.getPath() + " cp -f " + inputrc.getPath() + " " + inputrcu.getPath(), env, home);
                        pp.waitFor();

                        File vimrc = new File(system, "vimrc");
                        File vimrcu = new File(home, ".vimrc");
                        if (!vimrcu.exists() || mOverwriteAll) {
//                            pp = Runtime.getRuntime().exec(busytar.getPath()+" cp -f "+vimrc.getPath()+" "+vimrcu.getPath());
                            pp = Runtime.getRuntime().exec(busytar.getPath() + " cp -f " + vimrc.getPath() + " " + vimrcu.getPath(), env, home);
                            pp.waitFor();
                        }

                        //Check the home vim folder
                        File vimh = new File(system, "etc/default_vim");
                        File vimhu = new File(home, ".vim");
                        if (!vimhu.exists() || mOverwriteAll) {
//                            pp = Runtime.getRuntime().exec(busytar.getPath()+" cp -rf "+vimh.getPath()+" "+vimhu.getPath());
                            pp = Runtime.getRuntime().exec(busytar.getPath() + " cp -rf " + vimh.getPath() + " " + vimhu.getPath(), env, home);
                            pp.waitFor();
                        }

                        //Create a link to the sdcard
                        File sdcard = Environment.getExternalStorageDirectory();
                        File lnsdcard = new File(home, "sdcard");
                        String func = busytar.getPath() + " ln -s " + sdcard.getPath() + " " + lnsdcard.getPath();
                        Log.v("SpartacusRex", "SDCARD ln : " + func);
//                        pp = Runtime.getRuntime().exec(func);
                        pp = Runtime.getRuntime().exec(func, env, home);
                        pp.waitFor();

                        //Make a few initial folders
                        File local = new File(home, "local");
                        if (!local.exists()) {
                            local.mkdirs();
                        }

                        File bin = new File(local, "bin");
                        if (!bin.exists()) {
                            bin.mkdirs();
                        }

                        bin = new File(local, "lib");
                        if (!bin.exists()) {
                            bin.mkdirs();
                        }

                        bin = new File(local, "include");
                        if (!bin.exists()) {
                            bin.mkdirs();
                        }

                        bin = new File(home, "tmp");
                        if (!bin.exists()) {
                            bin.mkdirs();
                        }

                        bin = new File(home, "projects");
                        if (!bin.exists()) {
                            bin.mkdirs();
                        }

                        msg = new Message();
                        msg.getData().putString("info", getString(R.string.main_install_java_install_putstring_cleaningup));
                        mInstallHandler.sendMessage(msg);
                        filemanager.deleteFolder(worker);

                        //SYSTEM INSTALLED!!
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("CURRENT_SYSTEM", CURRENT_INSTALL_SYSTEM);
                        editor.putInt("CURRENT_SYSTEM_NUM", CURRENT_INSTALL_SYSTEM_NUM);
                        editor.commit();

                    } catch (Exception e) {
                        Log.v("Doublemine", "安装系统异常:" + e);
                        e.printStackTrace();
                        msg = new Message();
                        msg.getData().putString("error", e.toString());
                        mInstallHandler.sendMessage(msg);

                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("CURRENT_SYSTEM", getString(R.string.main_install_java_install_putstring_error_lastinstall));
                        editor.putInt("CURRENT_SYSTEM_NUM", -1);
                        editor.commit();
                    }

                    //Its done..
                    msg = new Message();
                    msg.getData().putString("info", getString(R.string.main_install_java_install_putstring_systeminstallcomplete));
                    mInstallHandler.sendMessage(msg);

                    msg = new Message();
                    msg.getData().putString("close_install", "1");
                    mInstallHandler.sendMessage(msg);

                    Log.v("SpartacusRex", "Finished Binary Install");
                }
            };
            tt.start();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.v("SpartacusRex", "Installer onConfigurationChanged!!!!");
    }

    //添加菜单
    @Override
    public boolean onOptionsItemSelected(com.actionbarsherlock.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case 0: {
                startActivity(new Intent(installer.this, aboutActivity.class));
                break;
            }


        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {

        menu.add(0, 0, 0, R.string.wh_menu_about);

        return true;
    }

}
