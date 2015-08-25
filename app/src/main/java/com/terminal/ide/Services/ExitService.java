package com.terminal.ide.Services;

import com.terminal.ide.TermService;

import android.app.IntentService;
import android.content.Intent;


/**
 * @author wanghao
 *         这是一个关闭程序所有Activity和Service的service
 *         2015.3.28
 */
public class ExitService extends IntentService {

    public ExitService() {
        super("Exit");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        exit();

    }

    private void exit() {
        stopService(new Intent(ExitService.this, TermService.class));
        CloseApplication.getInstance().exit();
    }

}
