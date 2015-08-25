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
public class tutadaptor extends gen_adaptor {

    public tutadaptor(Context zContext) {
        super();

        //Add the Tutorials
        Vector<tutlistitem> items = getItemList();

        items.add(new tutlistitem(zContext, R.string.help_ui_introduction, R.string.help_ui_introduction_summary, R.layout.tut_intro, R.drawable.app_terminal));
        items.add(new tutlistitem(zContext, R.string.help_ui_keyboard, R.string.help_ui_keyboard_summary, R.layout.tut_keyboard, R.drawable.app_terminal));
        items.add(new tutlistitem(zContext, R.string.help_ui_tutorial, R.string.help_ui_tutorial_summary, R.layout.tut_first, R.drawable.app_terminal));
        items.add(new tutlistitem(zContext, R.string.help_ui_bash, R.string.help_ui_bash_summary, R.layout.tut_bash, R.drawable.sym_keyboard_done));
        items.add(new tutlistitem(zContext, R.string.help_ui_busybox, R.string.help_ui_busybox_summary, R.layout.tut_busybox, R.drawable.sym_keyboard_done));
        items.add(new tutlistitem(zContext, R.string.help_ui_vim, R.string.help_ui_vim_summary, R.layout.tut_vim, R.drawable.sym_keyboard_done));
        items.add(new tutlistitem(zContext, R.string.help_ui_javakits, R.string.help_ui_javakits_summary, R.layout.tut_javac, R.drawable.sym_keyboard_done));
        items.add(new tutlistitem(zContext, R.string.help_ui_gcc, R.string.help_ui_gcc_summary, R.layout.tut_gcc, R.drawable.sym_keyboard_done));
        items.add(new tutlistitem(zContext, R.string.help_ui_overconnect, R.string.help_ui_overconnect_summary, R.layout.tut_remote, R.drawable.sym_keyboard_done));
        items.add(new tutlistitem(zContext, R.string.help_ui_git, R.string.help_ui_git_summary, R.layout.tut_git, R.drawable.sym_keyboard_done));
        items.add(new tutlistitem(zContext, R.string.help_ui_mc, R.string.help_ui_mc_summary, R.layout.tut_mc, R.drawable.sym_keyboard_done));
        items.add(new tutlistitem(zContext, R.string.help_ui_tmux, R.string.help_ui_tmux_summary, R.layout.tut_tmux, R.drawable.sym_keyboard_done));
        items.add(new tutlistitem(zContext, R.string.help_ui_bitchx, R.string.help_ui_bitchx_summary, R.layout.tut_bitchx, R.drawable.sym_keyboard_done));
        items.add(new tutlistitem(zContext, R.string.help_ui_additionalapps, R.string.help_ui_additionalapps_summary, R.layout.tut_expand, R.drawable.sym_keyboard_done));
        items.add(new tutlistitem(zContext, R.string.help_ui_trouble, R.string.help_ui_trouble_summary, R.layout.tut_trouble, R.drawable.sym_keyboard_done));
        items.add(new tutlistitem(zContext, R.string.help_ui_thanks, R.string.help_ui_thanks_summary, R.layout.tut_thanks, R.drawable.sym_keyboard_done));
        items.add(new tutlistitem(zContext, R.string.help_ui_gplv2, R.string.help_ui_gplv2_summary, R.layout.tut_gpl, R.drawable.sym_keyboard_done));

    }

    @Override
    public int getItemViewType(int arg0) {
        return R.layout.tutorial_list;
    }

}
