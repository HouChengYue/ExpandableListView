package com.example.houch.expandablelistviewdome;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import com.example.houch.expandablelistviewdome.databinding.ActivityMainBinding;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ActivityMainBinding mViewDataBinding;
    private List<Listitem> mListitems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewDataBinding = DataBindingUtil.bind(LayoutInflater.from(this).inflate(R.layout.activity_main, null));
        setContentView(R.layout.activity_main);
        initData();

    }

    private void initData() {
        for (int lI = 0; lI < 3; lI++) {
            Listitem lListitem = new Listitem();
            lListitem.setTitle("P" + lI);
            lListitem.setCountent("count" + lI);
            List<childitem> lChilditems = new ArrayList<>();
            for (int lI1 = 0; lI1 < 5; lI1++) {
                childitem lChilditem = new childitem();
                lChilditem.setTitle("" + lI1);
                lChilditem.setCountent("child" + lI + lI1);
                lChilditems.add(lChilditem);
            }
            lListitem.setChilditems(lChilditems);
            mListitems.add(lListitem);
        }
        ExpandableListView listview = (ExpandableListView) findViewById(R.id.expandablelistview);
        listview.setGroupIndicator(null);//将控件默认的左边箭头去掉，
        ExAdpter lExAdpter=new ExAdpter(mListitems);
        listview.setAdapter(lExAdpter);
        mViewDataBinding.expandablelistview.setVisibility(View.GONE);
    }

    public class ExAdpter extends BaseExpandableListAdapter {
        private List<Listitem> mListitems;

        public ExAdpter(List<Listitem> pListitems) {
            if (pListitems != null) {
                mListitems = pListitems;
            }else {
                mListitems=new ArrayList<>();
            }
        }

        @Override
        public int getGroupCount() {
            Log.e(TAG, "getGroupCount: "+mListitems.size() );
            return mListitems.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            Log.e(TAG, "getChildrenCount: "+mListitems.get(groupPosition).getChilditems().size() );
            return mListitems.get(groupPosition).getChilditems().size();
        }

        @Override
        public Listitem getGroup(int groupPosition) {
            Log.e(TAG, "getGroup: "+groupPosition );
            return mListitems.get(groupPosition);
        }

        @Override
        public childitem getChild(int groupPosition, int childPosition) {
            Log.e(TAG, "getChild: ++++" );
            return mListitems.get(groupPosition).getChilditems().get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            Log.e(TAG, "getGroupId: "+groupPosition );
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            Log.e(TAG, "getChildId: "+childPosition );
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            Listitem lListitem = mListitems.get(groupPosition);
            View lView=LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_layout,null);
            ((TextView)lView.findViewById(R.id.tv_parant_ico)).setText(lListitem.getTitle());
            ((TextView)lView.findViewById(R.id.tv_parant_title)).setText(lListitem.getCountent());
            return lView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            childitem lChilditem = mListitems.get(groupPosition).getChilditems().get(childPosition);
            View lView=LayoutInflater.from(parent.getContext()).inflate(R.layout.chald_layout,null);
            ((TextView)lView.findViewById(R.id.tv_icon_chald)).setText(lChilditem.getTitle());
            ((TextView)lView.findViewById(R.id.tv_child_count)).setText(lChilditem.getCountent());
            return lView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }


    public static class Listitem {
        private String title;
        private String countent;
        private List<childitem> mChilditems;

        public String getTitle() {
            return title;
        }

        public void setTitle(String pTitle) {
            title = pTitle;
        }

        public String getCountent() {
            return countent;
        }

        public void setCountent(String pCountent) {
            countent = pCountent;
        }

        public List<childitem> getChilditems() {
            return mChilditems;
        }

        public void setChilditems(List<childitem> pChilditems) {
            mChilditems = pChilditems;
        }
    }

    public static class childitem {
        private String title;
        private String countent;

        public String getTitle() {
            return title;
        }

        public void setTitle(String pTitle) {
            title = pTitle;
        }

        public String getCountent() {
            return countent;
        }

        public void setCountent(String pCountent) {
            countent = pCountent;
        }
    }
}
