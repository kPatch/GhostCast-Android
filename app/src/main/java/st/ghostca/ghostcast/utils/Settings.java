package st.ghostca.ghostcast.utils;

/**
 * Created by irvin on 10/26/2014.
 */

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import st.ghostca.ghostcast.R;
import st.ghostca.ghostcast.custom.CustomActivity;

/**
 * The Class Settings shows the content related to Application settings such as
 * Themes, Camera settings etc.
 */
public class Settings extends CustomActivity
{

    /** The list of Setting items. */
    private ArrayList<Data> sList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        initViewComponents();
    }

    /**
     * This method initialize the view components, assign OnClick, OnTouch and
     * other required listeners on views.
     */
    private void initViewComponents()
    {
        ListView list = (ListView) findViewById(R.id.list);

        View header = getLayoutInflater()
                .inflate(R.layout.setting_header, null);
        list.addHeaderView(header);

        getSettings();
        final SettingAdapter adp = new SettingAdapter();
        list.setAdapter(adp);
        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3)
            {
                String s = sList.get(arg2 - 1).getDesc();
                sList.get(arg2 - 1).setDesc(s == null ? "" : null);
                adp.notifyDataSetChanged();
            }
        });

        View b = header.findViewById(R.id.btnTheme);
        b.setOnClickListener(this);
        b.setOnTouchListener(TOUCH);

        getActionBar().setTitle("Settings");
    }

    @Override
    public void onClick(View v)
    {
        super.onClick(v);

        if (v.getId() == R.id.btnTheme)
            startActivity(new Intent(this, Themes.class));
    }

    /* (non-Javadoc)
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.setting, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == R.id.menu_friends)
        {
            startActivity(new Intent(this, Friends.class));
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Gets the setting item. The current implementation create a dummy list of
     * Setting items. A Non-null value for description means that the
     * corresponding setting item will be marked as selected. You can write your
     * code to load the actual Setting items.
     *
     */
    private void getSettings()
    {
        sList = new ArrayList<Data>();
        sList.add(new Data("Google Cloud Sync", "", 0));
        sList.add(new Data("Gift", null, 0));
        sList.add(new Data("Rate", null, 0));
        sList.add(new Data("Help", null, 0));
        sList.add(new Data("Info", null, 0));
    }

    /**
     * The Class SettingAdapter is the Adapter class for List of Setting items.
     * You can write your own logic related to List item display.
     */
    private class SettingAdapter extends BaseAdapter
    {

        /* (non-Javadoc)
         * @see android.widget.Adapter#getCount()
         */
        @Override
        public int getCount()
        {
            return sList.size();
        }

        /* (non-Javadoc)
         * @see android.widget.Adapter#getItem(int)
         */
        @Override
        public Data getItem(int arg0)
        {
            return sList.get(arg0);
        }

        /* (non-Javadoc)
         * @see android.widget.Adapter#getItemId(int)
         */
        @Override
        public long getItemId(int arg0)
        {
            return 0;
        }

        /* (non-Javadoc)
         * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            if (convertView == null)
                convertView = getLayoutInflater().inflate(
                        R.layout.setting_item, null);
            TextView lbl = (TextView) convertView;
            lbl.setText(getItem(position).getTitle());
            if (getItem(position).getDesc() == null)
                lbl.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            else
                lbl.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                        R.drawable.arrow_blue, 0);
            return lbl;
        }

    }
}
