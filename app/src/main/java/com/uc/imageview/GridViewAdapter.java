package com.uc.imageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import java.util.List;

/**
 * Created by guoho on 2017/5/8.
 */

public class GridViewAdapter extends BaseAdapter {
    private class Holder{
        ImageView imageView;
    }

    private List<ImageInfo> imageNames;
    private Context context;
    private LayoutInflater inflater;

    Bitmap loadBitmap(String file){
        Bitmap bitmap= BitmapFactory.decodeFile(file);
        Bitmap thumb=Bitmap.createScaledBitmap(bitmap, 100, 100, true);
        bitmap.recycle();
        return thumb;
    }

    public GridViewAdapter(
            @NonNull Context context,
            @NonNull List<ImageInfo> imageNames){
        this.imageNames=imageNames;
        this.context=context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return imageNames.size();
    }

    @Override
    public Object getItem(int position) {
        return imageNames.size()==0? null : imageNames.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(imageNames.size()==0) return null;
        Holder holder;
        if(convertView==null){
            holder=new Holder();
            convertView=inflater.inflate(R.layout.item_grid_view, null);
            ImageView imageView=(ImageView) convertView.findViewById(R.id.imageView);
            holder.imageView=imageView;
            convertView.setTag(holder);
        } else {
            holder=(Holder) convertView.getTag();
        }
        holder.imageView.setImageBitmap(imageNames.get(position).getImageThumb());
        return convertView;
    }
}
