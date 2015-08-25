package com.terminal.ide.ui;

import com.terminal.ide.R;
import com.terminal.ide.tools.UrlFileInfo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings.ZoomDensity;
import android.webkit.WebView;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HelpExplandableListView extends ExpandableListView {

    ExpandInfoAdapter madapter;
    private Context mContext;
    //String[] str_group_items={"这是英文版的安装说明","这是中文版的安装说明"};//组视图
    //String[][] str_child_items={{"file:///android_asset/html/en/installsystemActivity_Follow.html"},{"file:///android_asset/html/zh/installsystemActivity_Follow.html"}};//子视图
    /**
     * group_logo
     * 1.introuduction
     * 2.bash
     * 3.keyboard
     * 4.vim
     * 5.busybox
     * 6.java
     * 7.gcc
     * 8.git
     * 9.telnet
     * 10.ssh
     * 11.rsync
     * 12.mc
     * 13.tmux
     * 14.bitchx
     * 15.tut
     * 16.gplv2
     * <p/>
     * 此处使用泛型更加安全
     */
    int[] group_logo = new int[]{R.drawable.help_introuduction, R.drawable.help_bash,
            R.drawable.help_keyboard, R.drawable.help_vim, R.drawable.help_busybox, R.drawable.help_java,
            R.drawable.help_gcc, R.drawable.help_git, R.drawable.help_telnet, R.drawable.help_ssh, R.drawable.help_rsync,
            R.drawable.help_mc, R.drawable.help_tmux, R.drawable.help_bitchx, R.drawable.help_turoial, R.drawable.help_license};

    String[][] child_item = {{getHtmlUrl(R.string.help_html_introduction)}, {getHtmlUrl(R.string.help_html_bash)},
            {getHtmlUrl(R.string.help_html_keyboard)}, {getHtmlUrl(R.string.help_html_vim)},
            {getHtmlUrl(R.string.help_html_busybox)}, {getHtmlUrl(R.string.help_html_java)},
            {getHtmlUrl(R.string.help_html_gcc)}, {getHtmlUrl(R.string.help_html_git)}
            , {getHtmlUrl(R.string.help_html_telnet)}, {getHtmlUrl(R.string.help_html_ssh)},
            {getHtmlUrl(R.string.help_html_rsync)}, {getHtmlUrl(R.string.help_html_mc)},
            {getHtmlUrl(R.string.help_html_tmux)}, {getHtmlUrl(R.string.help_html_bitchx)},
            {getHtmlUrl(R.string.help_html_tutorial)}, {getHtmlUrl(R.string.help_html_gplv2)}
    };
    String[] group_summary = new String[]{getResources().getString(R.string.help_html_introduction_summary), getResources().getString(R.string.help_html_bash_summary),
            getResources().getString(R.string.help_html_keyboard_summary), getResources().getString(R.string.help_html_vim_summary),
            getResources().getString(R.string.help_html_busybox_summary), getResources().getString(R.string.help_html_java_summary),
            getResources().getString(R.string.help_html_gcc_summary), getResources().getString(R.string.help_html_git_summary),
            getResources().getString(R.string.help_html_telnet_summary), getResources().getString(R.string.help_html_ssh_summary),
            getResources().getString(R.string.help_html_rsync_summary), getResources().getString(R.string.help_html_mc_summary),
            getResources().getString(R.string.help_html_tmux_summary), getResources().getString(R.string.help_html_bitchx_summary),
            getResources().getString(R.string.help_html_tutorial_summary), getResources().getString(R.string.help_html_gplv2_summary)};

    String[] group_title = new String[]{getResources().getString(R.string.help_html_introduction_title), getResources().getString(R.string.help_html_bash_title),
            getResources().getString(R.string.help_html_keyboard_title), getResources().getString(R.string.help_html_vim_title),
            getResources().getString(R.string.help_html_busybox_title), getResources().getString(R.string.help_html_java_title),
            getResources().getString(R.string.help_html_gcc_title), getResources().getString(R.string.help_html_git_title),
            getResources().getString(R.string.help_html_telnet_title), getResources().getString(R.string.help_html_ssh_title),
            getResources().getString(R.string.help_html_rsync_title), getResources().getString(R.string.help_html_mc_title),
            getResources().getString(R.string.help_html_tmux_title), getResources().getString(R.string.help_html_bitchx_title),
            getResources().getString(R.string.help_html_tutorial_title), getResources().getString(R.string.help_html_gplv2_title)};

    public String getHtmlUrl(int rid) {
        String tempsString;
        tempsString = getResources().getString(R.string.html_file_url) + getResources().getString(rid);
        return tempsString;
    }

    public HelpExplandableListView(Context context) {
        super(context);
        this.mContext = context;
        /*隐藏默认箭头显示*/
        this.setGroupIndicator(null);
		/*隐藏垂直滚动条*/
        this.setVerticalScrollBarEnabled(false);

        setCacheColorHint(Color.TRANSPARENT);
        setDividerHeight(0);
        setChildrenDrawingCacheEnabled(false);
        setGroupIndicator(null);
		
		/*隐藏选择的黄色高亮*/
        ColorDrawable drawable_tranparent = new ColorDrawable(Color.TRANSPARENT);
        setSelector(drawable_tranparent);
		
		/*设置adapter*/
        madapter = new ExpandInfoAdapter();
        setAdapter(madapter);

    }


    public HelpExplandableListView(Context context, AttributeSet attrs) {
        this(context);
        // TODO Auto-generated constructor stub
    }

    public class ExpandInfoAdapter extends BaseExpandableListAdapter {


        LinearLayout mGroupLayout;

        @Override
        public int getGroupCount() {

            return group_title.length;
        }

        @Override
        public int getChildrenCount(int groupPosition) {

            return child_item[groupPosition].length;
        }

        @Override
        public Object getGroup(int groupPosition) {

            return group_title[groupPosition];
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return child_item[groupPosition][childPosition];

        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            TextView group_tView;
            TextView group_sView;

            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.helpactivity_group_item, null);

            }
			/*判断group是否展开来分别设置背景图片*/
            ImageView gouuplogoImageView = (ImageView) convertView.findViewById(R.id.group_logo);
            gouuplogoImageView.setBackgroundResource(group_logo[groupPosition]);
            ImageView groupstateImageView = (ImageView) convertView.findViewById(R.id.group_state);
            if (isExpanded) {

                groupstateImageView.setBackgroundResource(R.drawable.group_down);
            } else {

                groupstateImageView.setBackgroundResource(R.drawable.group_up);
            }
            group_tView = (TextView) convertView.findViewById(R.id.group_title);
            group_tView.setText(group_title[groupPosition]);
            group_sView = (TextView) convertView.findViewById(R.id.group_text);
            group_sView.setText(group_summary[groupPosition]);
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
            WebView child_wVi;

            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.helpactivity_child_item, null);
                child_wVi = (WebView) convertView.findViewById(R.id.help_activity_wv);
                child_wVi.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
                child_wVi.getSettings().setLoadWithOverviewMode(true);
            }

            child_wVi = (WebView) convertView.findViewById(R.id.help_activity_wv);

            child_wVi.getSettings().setLoadWithOverviewMode(true);//
            child_wVi.getSettings().setUseWideViewPort(true);
            child_wVi.getSettings().setBuiltInZoomControls(true);
            child_wVi.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            //child_wVi.reload();
            child_wVi.loadUrl(child_item[groupPosition][childPosition]);
            child_wVi.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
            child_wVi.getSettings().setLoadWithOverviewMode(true);

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            // TODO Auto-generated method stub
            return false;
        }

    }
}
