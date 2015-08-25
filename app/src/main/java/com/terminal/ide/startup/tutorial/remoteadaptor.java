/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.terminal.ide.startup.tutorial;

import android.content.Context;

import com.terminal.ide.R;

import java.util.Vector;

/**
 * @author Spartacus Rex
 */
public class remoteadaptor extends gen_adaptor {

    public remoteadaptor(Context zContext) {
        super();

        //Add the Tutorials
        Vector<tutlistitem> items = getItemList();

        items.add(new tutlistitem(zContext, R.string.help_overconnect_telnet, R.string.help_overconnect_telnet_summary, R.layout.tut_telnet, R.drawable.app_terminal));
        items.add(new tutlistitem(zContext, R.string.help_overconnect_ssh, R.string.help_overconnect_ssh, R.layout.tut_ssh, R.drawable.app_terminal));
        items.add(new tutlistitem(zContext, R.string.help_overconnect_rsync, R.string.help_overconnect_rsync_summary, R.layout.tut_rsync, R.drawable.sym_keyboard_done));
    }

    @Override
    public int getItemViewType(int arg0) {
        return R.layout.tutorial_list;
    }

}
