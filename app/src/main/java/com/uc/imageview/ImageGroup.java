package com.uc.imageview;

import java.util.List;

/**
 * Created by guoho on 2017/5/8.
 */

public class ImageGroup {
    private String date;
    private List<ImageInfo> images;

    public List<ImageInfo> getImages() {
        return images;
    }

    public void setImages(List<ImageInfo> images) {
        this.images = images;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
