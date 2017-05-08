package com.uc.imageview;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private List<ImageGroup> imageGroups=new ArrayList<>();
    private final String IMAGE_TYPE="image/*";
    private final int IMAGE_CODE=0;
    private String[] projection={MediaStore.Images.Media.DATE_TAKEN, MediaStore.Images.Media.BUCKET_DISPLAY_NAME, MediaStore.Images.Media._ID};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView=(ListView)findViewById(R.id.listView);
        initImages();
        ImageListViewAdapter adapter=new ImageListViewAdapter(this, imageGroups);

    }

    private void initImages(){
        Map<String, List<ImageInfo>> allMap=new HashMap<>();
        imageGroups.clear();
        ContentResolver resolver=getContentResolver();
        Uri uri= MediaStore.Images.Media.INTERNAL_CONTENT_URI;
        String path= uri.getPath();
        File file=new File(path);
        path= file.getAbsolutePath();
        Cursor cursor= resolver.query(MediaStore.Images.Media.INTERNAL_CONTENT_URI, projection, null,null,null);
        int dateIndex=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_TAKEN);
        int displayNameIndex=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        int id=cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-DD-mm");
        Date date=new Date();
        if(cursor.moveToFirst()) {
            do {
                ImageInfo imageInfo = new ImageInfo();
                long dateM = cursor.getLong(dateIndex);
                date.setTime(dateM);
                imageInfo.setDate(format.format(date));
                imageInfo.setDisplayName(cursor.getString(displayNameIndex));
                imageInfo.setImageThumb(MediaStore.Images.Thumbnails.getThumbnail(resolver, cursor.getLong(id), MediaStore.Images.Thumbnails.MICRO_KIND, null));
                if (allMap.containsKey(imageInfo.getDate())) {
                    allMap.get(imageInfo.getDate()).add(imageInfo);
                } else {
                    List<ImageInfo> list = new ArrayList<>();
                    list.add(imageInfo);
                    allMap.put(imageInfo.getDate(), list);
                }
            } while (cursor.moveToNext());
        }
        for(Map.Entry<String, List<ImageInfo>> entry:allMap.entrySet()){
            ImageGroup imageGroup=new ImageGroup();
            imageGroup.setDate(entry.getKey());
            imageGroup.setImages(entry.getValue());
            imageGroups.add(imageGroup);
            Collections.sort(imageGroups, new ImageFileComparator());
        }
    }

    private  class ImageFileComparator implements Comparator<ImageGroup> {

        @Override
        public int compare(ImageGroup o1, ImageGroup o2) {
            return o1.getDate().compareTo(o2.getDate());
        }
    }
}
