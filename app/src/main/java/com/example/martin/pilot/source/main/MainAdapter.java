package com.example.martin.pilot.source.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.martin.pilot.R;


public class MainAdapter extends BaseAdapter {
    private Context m_Context;

    public MainAdapter(Context context) {
        m_Context = context;
    }

    public static class ViewHolder {
        public ImageView itemImage;
        public TextView itemName;
    }

    public Integer[] m_ItemNames = {
            R.string.item_name_server,
            R.string.item_name_cursor,
            R.string.item_name_keyboard
    };

    public Integer[] m_ItemImages = {
            R.drawable.ic_server,
            R.drawable.ic_cursor,
            R.drawable.ic_keyboard
    };

    @Override
     public int getCount() {
        return m_ItemNames.length;
    }

    @Override
    public Object getItem(int position) {
        return m_ItemNames[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) m_Context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.main_grid_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.itemImage = (ImageView) view.findViewById(R.id.gridImageView);
            viewHolder.itemName = (TextView) view.findViewById(R.id.gridTextView);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.itemImage.setImageResource(m_ItemImages[position]);
        viewHolder.itemName.setText(m_ItemNames[position]);

        return view;
    }
}
