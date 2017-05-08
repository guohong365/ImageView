package com.uc.imageview;

import android.graphics.Bitmap;

/**
 * Created by guoho on 2017/5/8.
 */

public class ImageInfo {
    private String date;
    private String url;
    private String displayName;
    private int thumbMagic;
    private Bitmap imageThumb;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getThumbMagic() {
        return thumbMagic;
    }

    public void setThumbMagic(int thumbMagic) {
        this.thumbMagic = thumbMagic;
    }

    public Bitmap getImageThumb() {
        return imageThumb;
    }

    public void setImageThumb(Bitmap imageThumb) {
        this.imageThumb = imageThumb;
    }
}
