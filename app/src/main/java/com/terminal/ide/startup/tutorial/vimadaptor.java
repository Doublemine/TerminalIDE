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
public class vimadaptor extends gen_adaptor {

    public vimadaptor(Context zContext) {
        super();

        //Add the Tutorials
        Vector<tutlistitem> items = getItemList();

        items.add(new tutlistitem(zContext, R.string.help_tutorial_demo1, R.string.help_tutorial_demo1_summary, R.layout.tut_first_1, R.drawable.app_terminal));
        items.add(new tutlistitem(zContext, R.string.help_tutorial_demo2, R.string.help_tutorial_demo2_summary, R.layout.tut_first_2, R.drawable.app_terminal));
        items.add(new tutlistitem(zContext, R.string.help_tutorial_demo3, R.string.help_tutorial_demo3_summary, R.layout.tut_first_3, R.drawable.sym_keyboard_done));
        items.add(new tutlistitem(zContext, R.string.help_tutorial_demo4, R.string.help_tutorial_demo4_summary, R.layout.tut_first_4, R.drawable.sym_keyboard_done));
        items.add(new tutlistitem(zContext, R.string.help_tutorial_demo5, R.string.help_tutorial_demo5_summary, R.layout.tut_first_5, R.drawable.sym_keyboard_done));
        items.add(new tutlistitem(zContext, R.string.help_tutorial_demo6, R.string.help_tutorial_demo6_summary, R.layout.tut_first_6, R.drawable.sym_keyboard_done));
        items.add(new tutlistitem(zContext, R.string.help_tutorial_demo7, R.string.help_tutorial_demo7_summary, R.layout.tut_first_7, R.drawable.sym_keyboard_done));
        items.add(new tutlistitem(zContext, R.string.help_tutorial_demo8, R.string.help_tutorial_demo8_summary, R.layout.tut_first_8, R.drawable.sym_keyboard_done));
        items.add(new tutlistitem(zContext, R.string.help_tutorial_demo9, R.string.help_tutorial_demo9_summary, R.layout.tut_first_9, R.drawable.sym_keyboard_done));
    }

    @Override
    public int getItemViewType(int arg0) {
        return R.layout.tutorial_list;
    }

}
