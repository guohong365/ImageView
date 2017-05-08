package com.uc.imageview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by guoho on 2017/5/8.
 */

public class ImageListViewAdapter extends BaseAdapter {
    private class Holer{
        TextView dateGroup;
        GridView imageGrid;
    }
    private List<ImageGroup> mImages;
    private Context mContext;
    private LayoutInflater mInflater;

    public ImageListViewAdapter(
            @NonNull Context context,
            @NonNull List<ImageGroup> images){
        this.mImages=images;
        this.mContext=context;
        this.mInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mImages.size();
    }

    @Override
    public Object getItem(int position) {
        return mImages==null||mImages.size()==0? null: mImages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(mImages.size()==0) return null;
        Holer holer;
        if(convertView==null){
            holer=new Holer();
            convertView=mInflater.inflate(R.layout.item_list_view, null);
            holer.dateGroup=(TextView) convertView.findViewById(R.id.date_group);
            holer.imageGrid=(GridView) convertView.findViewById(R.id.grid_view);
            holer.imageGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, position + " was clicked", Toast.LENGTH_LONG).show();
                }
            });
            convertView.setTag(holer);
        } else {
            holer=(Holer)convertView.getTag();
        }
        ImageGroup imageGroup=mImages.get(position);
        holer.dateGroup.setText(imageGroup.getDate());
        holer.imageGrid.setAdapter(new GridViewAdapter(mContext, imageGroup.getImages()));
        return  convertView;
    }
}
